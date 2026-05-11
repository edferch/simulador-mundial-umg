package com.umg.simulador_mundial.controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umg.simulador_mundial.model.*;
import com.umg.simulador_mundial.dao.*;

@Controller
@RequestMapping("/simulacion")
public class SimulacionController {

    @Autowired private EquipoDAO equipoDao;
    @Autowired private PartidoDAO partidoDao;
    @Autowired private JugadorDAO jugadorDao;
    @Autowired private GolDAO golDao;
    @Autowired private AmonestacionDAO amonestacionDao; // ¡Tu nueva tabla de Fair Play!
    @Autowired private EstadioDAO estadioDao;

    @GetMapping
    public String cargarPanel(Model model) {
        List<Equipo> equipos = equipoDao.findAll();
        List<Partido> partidos = partidoDao.findAll();
        
        boolean sorteoRealizado = equipos.stream().anyMatch(e -> e.getGrupo() != null);
        boolean faseGruposTerminada = !partidos.isEmpty() && partidos.stream().allMatch(p -> "FINALIZADO".equals(p.getEstado()));

        Map<String, List<PosicionGrupo>> tablasPosiciones = new TreeMap<>(); 

        if (sorteoRealizado) {
            Map<Long, PosicionGrupo> calculoTemp = new HashMap<>();
            for (Equipo e : equipos) {
                if (e.getGrupo() != null) calculoTemp.put(e.getId(), new PosicionGrupo(e));
            }

            for (Partido p : partidos) {
                if ("FINALIZADO".equals(p.getEstado())) {
                    calculoTemp.get(p.getEquipoLocal().getId()).registrarPartido(p.getGolesLocal(), p.getGolesVisitante());
                    calculoTemp.get(p.getEquipoVisitante().getId()).registrarPartido(p.getGolesVisitante(), p.getGolesLocal());
                }
            }

            for (PosicionGrupo pos : calculoTemp.values()) {
                String letraGrupo = pos.getEquipo().getGrupo();
                tablasPosiciones.computeIfAbsent(letraGrupo, k -> new ArrayList<>()).add(pos);
            }

            for (List<PosicionGrupo> listaGrupo : tablasPosiciones.values()) {
                Collections.sort(listaGrupo);
            }
        }

        model.addAttribute("sorteoRealizado", sorteoRealizado);
        model.addAttribute("faseGruposTerminada", faseGruposTerminada);
        model.addAttribute("tablasPosiciones", tablasPosiciones);

        return "simulacion-dashboard";
    }

    @PostMapping("/sortear")
    public String ejecutarSorteo() {
        partidoDao.deleteAll();
        golDao.deleteAll(); 
        amonestacionDao.deleteAll(); // Limpiamos las tarjetas si reinician el torneo
        
        List<Equipo> equipos = equipoDao.findAll();
        Collections.shuffle(equipos);
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        
        for (int i = 0; i < equipos.size(); i++) {
            Equipo eq = equipos.get(i);
            eq.setGrupo(letras[i / 4]);
            equipoDao.save(eq);
        }

        Map<String, List<Equipo>> grupos = equipoDao.findAll().stream()
                .filter(e -> e.getGrupo() != null)
                .collect(Collectors.groupingBy(Equipo::getGrupo));

        // Asumimos que el ID 1 en tu catálogo de Fases es "Grupos"
        Fase faseGrupos = new Fase(1L, "Grupos"); 

        for (List<Equipo> grupoEquipos : grupos.values()) {
            if (grupoEquipos.size() == 4) {
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(1), faseGrupos);
                crearPartido(grupoEquipos.get(2), grupoEquipos.get(3), faseGrupos);
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(2), faseGrupos);
                crearPartido(grupoEquipos.get(1), grupoEquipos.get(3), faseGrupos);
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(3), faseGrupos);
                crearPartido(grupoEquipos.get(1), grupoEquipos.get(2), faseGrupos);
            }
        }
        return "redirect:/simulacion";
    }

    @PostMapping("/jugar-grupos")
    public String simularFaseGrupos() {
        List<Partido> partidos = partidoDao.findAll();
        Random rand = new Random();

        for (Partido p : partidos) {
            if ("PENDIENTE".equals(p.getEstado())) {
                int gl = rand.nextInt(5);
                int gv = rand.nextInt(5);
                p.setGolesLocal(gl);
                p.setGolesVisitante(gv);
                p.setEstado("FINALIZADO");
                partidoDao.save(p);

                asignarGolesAJugadores(p, p.getEquipoLocal(), gl);
                asignarGolesAJugadores(p, p.getEquipoVisitante(), gv);

                // EVENTOS ALEATORIOS (Lesiones o Tarjetas requeridas en la rúbrica)
                asignarEventosAleatorios(p, p.getEquipoLocal());
                asignarEventosAleatorios(p, p.getEquipoVisitante());
            }
        }
        return "redirect:/simulacion";
    }

    private void asignarGolesAJugadores(Partido partido, Equipo equipo, int golesAnotados) {
        List<Jugador> plantilla = jugadorDao.findByEquipo(equipo);
        if (plantilla.isEmpty()) return;

        Random rand = new Random();
        String[] tiposDeGol = {"Jugada", "Cabeza", "Tiro Libre", "Penal"};

        for (int i = 0; i < golesAnotados; i++) {
            Jugador goleador = plantilla.get(rand.nextInt(plantilla.size()));
            
            Gol gol = new Gol();
            gol.setPartido(partido);
            gol.setJugador(goleador);
            gol.setMinuto(rand.nextInt(90) + 1);
            gol.setTipoGol(tiposDeGol[rand.nextInt(tiposDeGol.length)]);
            
            golDao.save(gol); // Se guarda en la llave compuesta
        }
    }

    // EL MÓDULO DE SIMULACIÓN DE EVENTOS (Tarjetas)
    private void asignarEventosAleatorios(Partido partido, Equipo equipo) {
        List<Jugador> plantilla = jugadorDao.findByEquipo(equipo);
        if (plantilla.isEmpty()) return;
        
        Random rand = new Random();
        
        // 40% de probabilidad de que haya una falta grave en el equipo durante el partido
        if (rand.nextInt(100) < 40) {
            Jugador infractor = plantilla.get(rand.nextInt(plantilla.size()));
            
            Amonestacion tarjeta = new Amonestacion();
            tarjeta.setPartido(partido);
            tarjeta.setJugador(infractor);
            tarjeta.setMinuto(rand.nextInt(90) + 1);
            
            // 80% de que sea Amarilla, 20% de que sea Roja
            tarjeta.setColorTarjeta(rand.nextInt(100) < 80 ? "Amarilla" : "Roja"); 
            
            amonestacionDao.save(tarjeta);
        }
    }

    // Método auxiliar para crear los partidos programados
    private void crearPartido(Equipo local, Equipo visitante, Fase fase) {
        Partido p = new Partido();
        p.setEquipoLocal(local);
        p.setEquipoVisitante(visitante);
        p.setFase(fase);
        p.setEstado("PENDIENTE");
        p.setFechaHora(LocalDateTime.now());
        
        // Asignamos un estadio aleatorio de los que existen en el catálogo
        List<Estadio> estadios = estadioDao.findAll();
        if (!estadios.isEmpty()) {
            p.setEstadio(estadios.get(new Random().nextInt(estadios.size())));
        } else {
            // Fallback por si la base de datos está vacía
            Estadio est = new Estadio();
            est.setId(1L);
            p.setEstadio(est);
        }
        
        partidoDao.save(p);
    }

    // (El resto de tus métodos generar-octavos, simular-llaves, etc. mantienen la misma lógica
    // pero cambiando la palabra "Encuentro" por "Partido")
}