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
import com.umg.simulador_mundial.repository.*;

@Controller
@RequestMapping("/simulacion")
public class SimulacionController {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EncuentroRepository encuentroRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EventoEncuentroRepository eventoRepository;

    @GetMapping
    public String cargarPanel(Model model) {
        List<Equipo> equipos = equipoRepository.findAll();
        List<Encuentro> partidos = encuentroRepository.findAll();
        
        boolean sorteoRealizado = equipos.stream().anyMatch(e -> e.getGrupo() != null);
        boolean faseGruposTerminada = !partidos.isEmpty() && partidos.stream().allMatch(p -> p.getEstado().equals("FINALIZADO"));

        Map<String, List<PosicionGrupo>> tablasPosiciones = new TreeMap<>(); 

        if (sorteoRealizado) {
            Map<Long, PosicionGrupo> calculoTemp = new HashMap<>();
            for (Equipo e : equipos) {
                if (e.getGrupo() != null) calculoTemp.put(e.getId(), new PosicionGrupo(e));
            }

            for (Encuentro p : partidos) {
                if (p.getEstado().equals("FINALIZADO")) {
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
        encuentroRepository.deleteAll();
        eventoRepository.deleteAll();
        
        List<Equipo> equipos = equipoRepository.findAll();
        Collections.shuffle(equipos);
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        
        for (int i = 0; i < equipos.size(); i++) {
            Equipo eq = equipos.get(i);
            eq.setGrupo(letras[i / 4]);
            equipoRepository.save(eq);
        }

        Map<String, List<Equipo>> grupos = equipoRepository.findAll().stream()
                .filter(e -> e.getGrupo() != null)
                .collect(Collectors.groupingBy(Equipo::getGrupo));

        for (List<Equipo> grupoEquipos : grupos.values()) {
            if (grupoEquipos.size() == 4) {
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(1));
                crearPartido(grupoEquipos.get(2), grupoEquipos.get(3));
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(2));
                crearPartido(grupoEquipos.get(1), grupoEquipos.get(3));
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(3));
                crearPartido(grupoEquipos.get(1), grupoEquipos.get(2));
            }
        }
        return "redirect:/simulacion";
    }

    @PostMapping("/jugar-grupos")
    public String simularFaseGrupos() {
        List<Encuentro> partidos = encuentroRepository.findAll();
        Random rand = new Random();

        for (Encuentro p : partidos) {
            if (p.getEstado().equals("PENDIENTE")) {
                int gl = rand.nextInt(5);
                int gv = rand.nextInt(5);
                p.setGolesLocal(gl);
                p.setGolesVisitante(gv);
                p.setEstado("FINALIZADO");
                encuentroRepository.save(p);

                asignarGolesAJugadores(p, p.getEquipoLocal(), gl, gv);
                asignarGolesAJugadores(p, p.getEquipoVisitante(), gv, gl);
            }
        }
        return "redirect:/simulacion";
    }

    private void asignarGolesAJugadores(Encuentro encuentro, Equipo equipo, int golesAnotados, int golesRecibidos) {
        List<Jugador> plantilla = jugadorRepository.findByEquipo(equipo);
        if (plantilla.isEmpty()) return;

        Random rand = new Random();
        for (int i = 0; i < golesAnotados; i++) {
            Jugador goleador = plantilla.get(rand.nextInt(plantilla.size()));
            if (goleador.getPosicion().equalsIgnoreCase("Portero") && plantilla.size() > 1) {
                i--; continue;
            }
            goleador.setGolesAnotados(goleador.getGolesAnotados() + 1);
            jugadorRepository.save(goleador);

            eventoRepository.save(new EventoEncuentro("GOL", rand.nextInt(90) + 1, goleador, encuentro));
        }

        for (Jugador j : plantilla) {
            if (j.getPosicion().equalsIgnoreCase("Portero")) {
                j.setGolesRecibidos(j.getGolesRecibidos() + golesRecibidos);
                jugadorRepository.save(j);
                break;
            }
        }
    }

    @PostMapping("/generar-octavos")
    public String generarOctavos(RedirectAttributes flash) {
        try {
            List<Equipo> equipos = equipoRepository.findAll();
            List<Encuentro> partidos = encuentroRepository.findAll();
            Map<Long, PosicionGrupo> calculoTemp = new HashMap<>();
            for (Equipo e : equipos) if (e.getGrupo() != null) calculoTemp.put(e.getId(), new PosicionGrupo(e));

            for (Encuentro p : partidos) {
                if (p.getEstado().equals("FINALIZADO")) {
                    calculoTemp.get(p.getEquipoLocal().getId()).registrarPartido(p.getGolesLocal(), p.getGolesVisitante());
                    calculoTemp.get(p.getEquipoVisitante().getId()).registrarPartido(p.getGolesVisitante(), p.getGolesLocal());
                }
            }

            Map<String, List<PosicionGrupo>> grupos = new HashMap<>();
            for (PosicionGrupo pos : calculoTemp.values()) grupos.computeIfAbsent(pos.getEquipo().getGrupo(), k -> new ArrayList<>()).add(pos);

            List<PosicionGrupo> primeros = new ArrayList<>();
            List<PosicionGrupo> segundos = new ArrayList<>();
            for (List<PosicionGrupo> lista : grupos.values()) {
                Collections.sort(lista);
                if (lista.size() >= 1) primeros.add(lista.get(0));
                if (lista.size() >= 2) segundos.add(lista.get(1));
            }

            Collections.sort(segundos);
            List<Equipo> clasificados = new ArrayList<>();
            for (PosicionGrupo p : primeros) clasificados.add(p.getEquipo());
            for (int i = 0; i < 4; i++) clasificados.add(segundos.get(i).getEquipo());

            Collections.shuffle(clasificados);
            for (int i = 0; i < 16; i += 2) {
                Encuentro oct = new Encuentro();
                oct.setEquipoLocal(clasificados.get(i));
                oct.setEquipoVisitante(clasificados.get(i+1));
                oct.setEstado("OCTAVOS_PENDIENTE");
                oct.setFechaHora(LocalDateTime.now());
                encuentroRepository.save(oct);
            }
            return "redirect:/simulacion/fase-final";
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/simulacion";
        }
    }

    @GetMapping("/fase-final")
    public String verFaseFinal(Model model) {
        List<Encuentro> todos = encuentroRepository.findAll();
        model.addAttribute("octavos", todos.stream().filter(p -> p.getEstado().contains("OCTAVOS")).collect(Collectors.toList()));
        model.addAttribute("cuartos", todos.stream().filter(p -> p.getEstado().contains("CUARTOS")).collect(Collectors.toList()));
        model.addAttribute("semis", todos.stream().filter(p -> p.getEstado().contains("SEMI")).collect(Collectors.toList()));
        model.addAttribute("finales", todos.stream().filter(p -> p.getEstado().contains("GRAN_FINAL")).collect(Collectors.toList()));

        boolean terminado = todos.stream().anyMatch(p -> p.getEstado().equals("GRAN_FINAL_FINALIZADO"));
        model.addAttribute("torneoTerminado", terminado);

        if (terminado) {
            Encuentro fin = todos.stream().filter(p -> p.getEstado().contains("GRAN_FINAL")).findFirst().get();
            model.addAttribute("campeon", fin.getGolesLocal() > fin.getGolesVisitante() ? fin.getEquipoLocal() : fin.getEquipoVisitante());
            model.addAttribute("goleadores", jugadorRepository.findTop10ByOrderByGolesAnotadosDesc());
            model.addAttribute("porteros", jugadorRepository.findTop10ByPosicionOrderByGolesRecibidosAsc("Portero"));
        }
        return "fase-final-logica";
    }

    @PostMapping("/simular-llaves")
    public String simularLlaves(RedirectAttributes flash) {
        try {
            List<Encuentro> pendientes = encuentroRepository.findAll().stream()
                    .filter(p -> p.getEstado().contains("PENDIENTE") && !p.getEstado().equals("PENDIENTE"))
                    .collect(Collectors.toList());

            if (pendientes.isEmpty()) return "redirect:/simulacion/fase-final";

            String faseActual = pendientes.get(0).getEstado();
            List<Equipo> ganadores = new ArrayList<>();
            Random rand = new Random();

            for (Encuentro p : pendientes) {
                int gl = rand.nextInt(5);
                int gv = rand.nextInt(5);
                if (gl == gv) { if (rand.nextBoolean()) gl++; else gv++; }

                p.setGolesLocal(gl);
                p.setGolesVisitante(gv);
                p.setEstado(faseActual.replace("PENDIENTE", "FINALIZADO"));
                encuentroRepository.save(p);

                asignarGolesAJugadores(p, p.getEquipoLocal(), gl, gv);
                asignarGolesAJugadores(p, p.getEquipoVisitante(), gv, gl);

                ganadores.add(gl > gv ? p.getEquipoLocal() : p.getEquipoVisitante());
            }

            String sig = "";
            if (faseActual.contains("OCTAVOS")) sig = "CUARTOS_PENDIENTE";
            else if (faseActual.contains("CUARTOS")) sig = "SEMI_PENDIENTE";
            else if (faseActual.contains("SEMI")) sig = "GRAN_FINAL_PENDIENTE";

            if (!sig.isEmpty()) {
                for (int i = 0; i < ganadores.size(); i += 2) {
                    Encuentro n = new Encuentro();
                    n.setEquipoLocal(ganadores.get(i));
                    n.setEquipoVisitante(ganadores.get(i + 1));
                    n.setEstado(sig);
                    n.setFechaHora(LocalDateTime.now());
                    encuentroRepository.save(n);
                }
            }
            return "redirect:/simulacion/fase-final";
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/simulacion/fase-final";
        }
    }

    @GetMapping("/partido/{id}")
    public String verSimulacionPartido(@PathVariable Long id, Model model) {
        Encuentro encuentro = encuentroRepository.findById(id).orElseThrow();
        Map<Long, Double> ratings = new HashMap<>();
        List<Jugador> todos = new ArrayList<>();
        todos.addAll(jugadorRepository.findByEquipo(encuentro.getEquipoLocal()));
        todos.addAll(jugadorRepository.findByEquipo(encuentro.getEquipoVisitante()));

        Random rand = new Random();
        for(Jugador j : todos) ratings.put(j.getId(), 5.0 + (5.0 * rand.nextDouble()));

        model.addAttribute("encuentro", encuentro);
        model.addAttribute("ratings", ratings);
        model.addAttribute("eventos", eventoRepository.findByEncuentroOrderByMinutoAsc(encuentro));
        model.addAttribute("plantillaLocal", jugadorRepository.findByEquipo(encuentro.getEquipoLocal()));
        model.addAttribute("plantillaVisitante", jugadorRepository.findByEquipo(encuentro.getEquipoVisitante()));
        return "partido-simulacion";
    }

    private void crearPartido(Equipo local, Equipo visitante) {
        Encuentro e = new Encuentro();
        e.setEquipoLocal(local);
        e.setEquipoVisitante(visitante);
        e.setEstado("PENDIENTE");
        e.setFechaHora(LocalDateTime.now());
        encuentroRepository.save(e);
    }
}