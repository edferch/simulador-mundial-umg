package com.umg.simulador_mundial.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.umg.simulador_mundial.dao.AmonestacionDAO;
import com.umg.simulador_mundial.dao.EquipoDAO;
import com.umg.simulador_mundial.dao.EstadioDAO;
import com.umg.simulador_mundial.dao.GolDAO;
import com.umg.simulador_mundial.dao.JugadorDAO;
import com.umg.simulador_mundial.dao.PartidoDAO;
import com.umg.simulador_mundial.model.Amonestacion;
import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Estadio;
import com.umg.simulador_mundial.model.Fase;
import com.umg.simulador_mundial.model.Gol;
import com.umg.simulador_mundial.model.Jugador;
import com.umg.simulador_mundial.model.Partido;
import com.umg.simulador_mundial.model.PosicionGrupo;

@Controller
@RequestMapping("/simulacion")
public class SimulacionController {

    @Autowired private EquipoDAO equipoDao;
    @Autowired private PartidoDAO partidoDao;
    @Autowired private JugadorDAO jugadorDao;
    @Autowired private GolDAO golDao;
    @Autowired private AmonestacionDAO amonestacionDao;
    @Autowired private EstadioDAO estadioDao;

    @GetMapping
    public String cargarPanel(Model model) {
        List<Equipo> equipos = equipoDao.findAll();
        List<Partido> partidos = partidoDao.findAll();
        
        // LA CONDICIÓN CLAVE: El sorteo solo es válido si existen partidos creados.
        boolean sorteoRealizado = !partidos.isEmpty();
        boolean faseGruposTerminada = sorteoRealizado && partidos.stream().allMatch(p -> "FINALIZADO".equals(p.getEstado()));

        Map<String, List<PosicionGrupo>> tablasPosiciones = new TreeMap<>(); 

        if (sorteoRealizado) {
            Map<Long, PosicionGrupo> calculoTemp = new HashMap<>();
            
            // Inicializar a todos los equipos que tengan grupo asignado
            for (Equipo e : equipos) {
                if (e.getGrupo() != null && !e.getGrupo().trim().isEmpty()) {
                    calculoTemp.put(e.getId(), new PosicionGrupo(e));
                }
            }

            // Calcular puntos y goles de los partidos ya finalizados
            for (Partido p : partidos) {
                if ("FINALIZADO".equals(p.getEstado())) {
                    PosicionGrupo local = calculoTemp.get(p.getEquipoLocal().getId());
                    PosicionGrupo visita = calculoTemp.get(p.getEquipoVisitante().getId());
                    
                    if (local != null && visita != null) {
                        local.registrarPartido(p.getGolesLocal(), p.getGolesVisitante());
                        visita.registrarPartido(p.getGolesVisitante(), p.getGolesLocal());
                    }
                }
            }

            // Agrupar por letra (A, B, C...)
            for (PosicionGrupo pos : calculoTemp.values()) {
                String letraGrupo = pos.getEquipo().getGrupo();
                tablasPosiciones.computeIfAbsent(letraGrupo, k -> new ArrayList<>()).add(pos);
            }

            // Ordenar cada grupo por puntos y diferencia de goles
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
        // 1. EL ORDEN IMPORTA: Primero borramos lo que depende de los partidos
        golDao.deleteAll(); 
        amonestacionDao.deleteAll();
        partidoDao.deleteAll();
        
        // 2. Revolver a los 48 equipos
        List<Equipo> equipos = equipoDao.findAll();
        if(equipos.isEmpty()) {
            System.out.println("ERROR: La base de datos está vacía, revisa el data.sql");
            return "redirect:/simulacion";
        }
        Collections.shuffle(equipos);
        
        // 12 Grupos (A hasta L)
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        
        // 3. Asignar nuevo grupo y guardar en BD
        for (int i = 0; i < equipos.size(); i++) {
            Equipo eq = equipos.get(i);
            eq.setGrupo(letras[i / 4]); // Grupos de 4 equipos
            equipoDao.save(eq);
        }

        // 4. Programar los partidos (Generación de Calendario)
        Map<String, List<Equipo>> grupos = equipoDao.findAll().stream()
                .filter(e -> e.getGrupo() != null && !e.getGrupo().trim().isEmpty())
                .collect(Collectors.groupingBy(Equipo::getGrupo));

        // Obtenemos un estadio real de la base de datos para no causar errores
        List<Estadio> estadios = estadioDao.findAll();
        Estadio estadioRandom = estadios.isEmpty() ? null : estadios.get(0);

        for (List<Equipo> grupoEquipos : grupos.values()) {
            if (grupoEquipos.size() == 4) {
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(1), estadioRandom);
                crearPartido(grupoEquipos.get(2), grupoEquipos.get(3), estadioRandom);
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(2), estadioRandom);
                crearPartido(grupoEquipos.get(1), grupoEquipos.get(3), estadioRandom);
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(3), estadioRandom);
                crearPartido(grupoEquipos.get(1), grupoEquipos.get(2), estadioRandom);
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
                // Generar resultados aleatorios
                int gl = rand.nextInt(5); 
                int gv = rand.nextInt(5);
                p.setGolesLocal(gl);
                p.setGolesVisitante(gv);
                p.setEstado("FINALIZADO");
                partidoDao.save(p);

                // Asignar goles a jugadores reales
                asignarGolesAJugadores(p, p.getEquipoLocal(), gl);
                asignarGolesAJugadores(p, p.getEquipoVisitante(), gv);

                // Eventos Aleatorios (Tarjetas del módulo de Simulación)
                asignarEventosAleatorios(p, p.getEquipoLocal());
                asignarEventosAleatorios(p, p.getEquipoVisitante());
            }
        }
        return "redirect:/simulacion";
    }

    private void asignarGolesAJugadores(Partido partido, Equipo equipo, int golesAnotados) {
        if (golesAnotados == 0) return;
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
            golDao.save(gol); 
        }
    }

    private void asignarEventosAleatorios(Partido partido, Equipo equipo) {
        List<Jugador> plantilla = jugadorDao.findByEquipo(equipo);
        if (plantilla.isEmpty()) return;
        
        Random rand = new Random();
        
        // 40% de probabilidad de tarjeta en el partido para el equipo
        if (rand.nextInt(100) < 40) {
            Jugador infractor = plantilla.get(rand.nextInt(plantilla.size()));
            Amonestacion tarjeta = new Amonestacion();
            tarjeta.setPartido(partido);
            tarjeta.setJugador(infractor);
            tarjeta.setMinuto(rand.nextInt(90) + 1);
            tarjeta.setColorTarjeta(rand.nextInt(100) < 80 ? "Amarilla" : "Roja"); 
            amonestacionDao.save(tarjeta);
        }
    }

    private void crearPartido(Equipo local, Equipo visitante, Estadio estadio) {
        Partido p = new Partido();
        p.setEquipoLocal(local);
        p.setEquipoVisitante(visitante);
        p.setEstado("PENDIENTE");
        p.setFechaHora(LocalDateTime.now());
        p.setEstadio(estadio); 
        
        // SOLUCIÓN: Creamos una referencia a la fase de Grupos (id = 1) 
        // para que Hibernate sepa exactamente qué llave foránea usar sin dar error.
        Fase faseGrupos = new Fase();
        faseGrupos.setId(1L); 
        p.setFase(faseGrupos);
        
        partidoDao.save(p);
    }
}