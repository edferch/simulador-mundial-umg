package com.umg.simulador_mundial.controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.umg.simulador_mundial.model.*;
import com.umg.simulador_mundial.dao.*;

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
        
        boolean sorteoRealizado = !partidos.isEmpty();
        boolean faseGruposTerminada = sorteoRealizado && partidos.stream().allMatch(p -> "FINALIZADO".equals(p.getEstado()));

        Map<String, List<PosicionGrupo>> tablasPosiciones = new TreeMap<>(); 

        if (sorteoRealizado) {
            Map<Long, PosicionGrupo> calculoTemp = new HashMap<>();
            
            for (Equipo e : equipos) {
                if (e.getGrupo() != null && !e.getGrupo().trim().isEmpty()) {
                    calculoTemp.put(e.getId(), new PosicionGrupo(e));
                }
            }

            for (Partido p : partidos) {
                if ("FINALIZADO".equals(p.getEstado())) {
                    PosicionGrupo local = calculoTemp.get(p.getEquipoLocal().getId());
                    PosicionGrupo visita = calculoTemp.get(p.getEquipoVisitante().getId());
                    
                    if (local != null && visita != null) {
                        // Protección anti-errores nulos
                        int gl = p.getGolesLocal() != null ? p.getGolesLocal() : 0;
                        int gv = p.getGolesVisitante() != null ? p.getGolesVisitante() : 0;
                        local.registrarPartido(gl, gv);
                        visita.registrarPartido(gv, gl);
                    }
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
    @Transactional
    public String ejecutarSorteo() {
        golDao.deleteAll(); 
        amonestacionDao.deleteAll();
        partidoDao.deleteAll();
        
        List<Equipo> equipos = equipoDao.findAll();
        if(equipos.isEmpty()) return "redirect:/simulacion";
        
        Collections.shuffle(equipos);
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        
        for (int i = 0; i < equipos.size(); i++) {
            if (i / 4 < letras.length) {
                Equipo eq = equipos.get(i);
                eq.setGrupo(letras[i / 4]);
                equipoDao.save(eq);
            }
        }

        Map<String, List<Equipo>> grupos = equipoDao.findAll().stream()
                .filter(e -> e.getGrupo() != null && !e.getGrupo().trim().isEmpty())
                .collect(Collectors.groupingBy(Equipo::getGrupo));

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
    @Transactional
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

        // TRUCO MAESTRO: Mezclamos para asegurar goleadores DIFERENTES y no violar reglas de BD
        List<Jugador> jugadoresDisponibles = new ArrayList<>(plantilla);
        Collections.shuffle(jugadoresDisponibles);
        
        Random rand = new Random();
        String[] tiposDeGol = {"Jugada", "Cabeza", "Tiro Libre", "Penal"};

        for (int i = 0; i < Math.min(golesAnotados, jugadoresDisponibles.size()); i++) {
            Gol gol = new Gol();
            gol.setPartido(partido);
            gol.setJugador(jugadoresDisponibles.get(i)); 
            gol.setMinuto(rand.nextInt(90) + 1);
            gol.setTipoGol(tiposDeGol[rand.nextInt(tiposDeGol.length)]);
            golDao.save(gol);
        }
    }

    private void asignarEventosAleatorios(Partido partido, Equipo equipo) {
        List<Jugador> plantilla = jugadorDao.findByEquipo(equipo);
        if (plantilla.isEmpty()) return;
        
        Random rand = new Random();
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
        
        Fase faseGrupos = new Fase();
        faseGrupos.setId(1L); 
        p.setFase(faseGrupos);
        
        partidoDao.save(p);
    }
}