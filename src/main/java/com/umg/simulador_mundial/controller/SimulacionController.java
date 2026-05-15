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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
                    if (p.getEquipoLocal() != null && p.getEquipoVisitante() != null) {
                        PosicionGrupo local = calculoTemp.get(p.getEquipoLocal().getId());
                        PosicionGrupo visita = calculoTemp.get(p.getEquipoVisitante().getId());
                        
                        if (local != null && visita != null) {
                            int gl = p.getGolesLocal() != null ? p.getGolesLocal() : 0;
                            int gv = p.getGolesVisitante() != null ? p.getGolesVisitante() : 0;
                            local.registrarPartido(gl, gv);
                            visita.registrarPartido(gv, gl);
                        }
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

        for (List<Equipo> grupoEquipos : grupos.values()) {
            if (grupoEquipos.size() == 4) {
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(1), obtenerEstadioAleatorio());
                crearPartido(grupoEquipos.get(2), grupoEquipos.get(3), obtenerEstadioAleatorio());
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(2), obtenerEstadioAleatorio());
                crearPartido(grupoEquipos.get(1), grupoEquipos.get(3), obtenerEstadioAleatorio());
                crearPartido(grupoEquipos.get(0), grupoEquipos.get(3), obtenerEstadioAleatorio());
                crearPartido(grupoEquipos.get(1), grupoEquipos.get(2), obtenerEstadioAleatorio());
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

        List<Jugador> jugadoresDisponibles = plantilla.stream()
                .filter(j -> j.getPosicion() == null || !j.getPosicion().getDescripcion().toLowerCase().contains("portero"))
                .collect(Collectors.toList());

        if (jugadoresDisponibles.isEmpty()) {
            jugadoresDisponibles = new ArrayList<>(plantilla);
        }
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

    @GetMapping("/fase-final")
    public String verFaseFinal(Model model) {
        List<Partido> todosLosPartidos = partidoDao.findAll();
        
        // Filtramos los partidos por fase (ID 2 = Octavos)
        List<Partido> octavos = todosLosPartidos.stream()
                .filter(p -> p.getFase() != null && p.getFase().getId() == 2)
                .collect(Collectors.toList());

        // Contamos cuántos partidos hay de fase de grupos
        long countGrupos = todosLosPartidos.stream()
                .filter(p -> p.getFase() != null && p.getFase().getId() == 1)
                .count();

        // Verificamos si la fase de grupos ya terminó
        boolean faseGruposTerminada = countGrupos > 0 && 
            todosLosPartidos.stream()
                .filter(p -> p.getFase() != null && p.getFase().getId() == 1)
                .allMatch(p -> "FINALIZADO".equals(p.getEstado()));

        // ¡AQUÍ ESTÁ LA MAGIA! Si no hay octavos pero el grupo terminó, ejecuta el método que ya tiene la matemática real.
        if (octavos.isEmpty() && faseGruposTerminada) {
            octavos = generarOctavosDesdeGrupos();
        }

        model.addAttribute("octavos", octavos);
        
        // Filtramos y agregamos Cuartos, Semifinales y Finales al modelo
        List<Partido> cuartos = todosLosPartidos.stream()
                .filter(p -> p.getFase() != null && p.getFase().getId() == 3)
                .collect(Collectors.toList());
        model.addAttribute("cuartos", cuartos);

        List<Partido> semis = todosLosPartidos.stream()
                .filter(p -> p.getFase() != null && p.getFase().getId() == 4)
                .collect(Collectors.toList());
        model.addAttribute("semis", semis);

        List<Partido> finales = todosLosPartidos.stream()
                .filter(p -> p.getFase() != null && p.getFase().getId() == 5)
                .collect(Collectors.toList());
        model.addAttribute("finales", finales);
        
        // Verificamos si ya hay campeón (Final finalizada, ID 5)
        boolean torneoTerminado = todosLosPartidos.stream()
                .anyMatch(p -> p.getFase() != null && p.getFase().getId() == 5 && "FINALIZADO".equals(p.getEstado()));
        
        model.addAttribute("torneoTerminado", torneoTerminado);
        
        if (torneoTerminado) {
            Partido granFinal = todosLosPartidos.stream()
                    .filter(p -> p.getFase() != null && p.getFase().getId() == 5)
                    .findFirst().orElse(null);
            if (granFinal != null) {
                int gl = granFinal.getGolesLocal() != null ? granFinal.getGolesLocal() : 0;
                int gv = granFinal.getGolesVisitante() != null ? granFinal.getGolesVisitante() : 0;
                model.addAttribute("campeon", gl > gv ? 
                                   granFinal.getEquipoLocal() : granFinal.getEquipoVisitante());
            }
            
            // Mandamos las estadísticas de goleadores a la vista (para cuando termine el torneo)
            model.addAttribute("goleadores", jugadorDao.findTop10ByOrderByGolesAnotadosDesc());
            model.addAttribute("porteros", jugadorDao.findTop5Porteros());
        }

        return "fase-final-logica";
    }

    @PostMapping("/simular-llaves")
    @Transactional
    public String simularLlaves() {
        List<Partido> partidos = partidoDao.findAll();
        Random rand = new Random();

        // Buscamos la fase actual que está "PENDIENTE"
        List<Partido> partidosPendientes = partidos.stream()
                .filter(p -> p.getFase() != null && p.getFase().getId() > 1 && "PENDIENTE".equals(p.getEstado()))
                .collect(Collectors.toList());

        if (partidosPendientes.isEmpty()) return "redirect:/simulacion/fase-final";

        long faseActualId = partidosPendientes.get(0).getFase().getId();

        for (Partido p : partidosPendientes) {
            // Simulación de llaves directas (Sin empates)
            int gl, gv;
            do {
                gl = rand.nextInt(5);
                gv = rand.nextInt(5);
            } while (gl == gv); 

            p.setGolesLocal(gl);
            p.setGolesVisitante(gv);
            p.setEstado("FINALIZADO");
            partidoDao.save(p);
            
            asignarGolesAJugadores(p, p.getEquipoLocal(), gl);
            asignarGolesAJugadores(p, p.getEquipoVisitante(), gv);
        }

        // Generar la SIGUIENTE ronda 
        generarSiguienteRonda(partidosPendientes, faseActualId);

        return "redirect:/simulacion/fase-final";
    }

    // ESTA ES LA FUNCIÓN NUEVA CON LA MATEMÁTICA PARA LLENAR EL BRACKET
    private List<Partido> generarOctavosDesdeGrupos() {
        List<Equipo> equipos = equipoDao.findAll();
        List<Partido> partidos = partidoDao.findAll();

        Map<Long, PosicionGrupo> calculoTemp = new HashMap<>();
        for (Equipo e : equipos) {
            if (e.getGrupo() != null && !e.getGrupo().trim().isEmpty()) {
                calculoTemp.put(e.getId(), new PosicionGrupo(e));
            }
        }

        for (Partido p : partidos) {
            if (p.getFase() != null && p.getFase().getId() == 1 && "FINALIZADO".equals(p.getEstado())) {
                if (p.getEquipoLocal() != null && p.getEquipoVisitante() != null) {
                    PosicionGrupo local = calculoTemp.get(p.getEquipoLocal().getId());
                    PosicionGrupo visita = calculoTemp.get(p.getEquipoVisitante().getId());
                    if (local != null && visita != null) {
                        int gl = p.getGolesLocal() != null ? p.getGolesLocal() : 0;
                        int gv = p.getGolesVisitante() != null ? p.getGolesVisitante() : 0;
                        local.registrarPartido(gl, gv);
                        visita.registrarPartido(gv, gl);
                    }
                }
            }
        }

        // Agrupamos por letra de grupo
        Map<String, List<PosicionGrupo>> tablasPorGrupo = new TreeMap<>();
        for (PosicionGrupo pos : calculoTemp.values()) {
            tablasPorGrupo.computeIfAbsent(pos.getEquipo().getGrupo(), k -> new ArrayList<>()).add(pos);
        }

        List<Equipo> primeros = new ArrayList<>();
        List<PosicionGrupo> segundos = new ArrayList<>();

        // Identificamos a los 12 líderes y guardamos a los 12 segundos lugares
        for (List<PosicionGrupo> lista : tablasPorGrupo.values()) {
            Collections.sort(lista);
            if (!lista.isEmpty()) {
                primeros.add(lista.get(0).getEquipo()); 
            }
            if (lista.size() > 1) {
                segundos.add(lista.get(1)); 
            }
        }

        // Ordenamos a los 12 segundos lugares y elegimos a los 4 mejores
        Collections.sort(segundos);
        List<Equipo> mejoresSegundos = segundos.stream()
                .limit(4)
                .map(PosicionGrupo::getEquipo)
                .collect(Collectors.toList());

        // Juntamos a los 16 equipos (12 líderes + 4 mejores segundos)
        List<Equipo> clasificados = new ArrayList<>(primeros);
        clasificados.addAll(mejoresSegundos);
        
        // Emparejamientos aleatorios para Octavos
        Collections.shuffle(clasificados);

        Fase faseOctavos = new Fase();
        faseOctavos.setId(2L); // ID 2 = Octavos

        // Generamos los 8 partidos y los guardamos
        for (int i = 0; i < clasificados.size(); i += 2) {
            if (i + 1 < clasificados.size()) {
                crearPartido(clasificados.get(i), clasificados.get(i + 1), obtenerEstadioAleatorio(), faseOctavos);
            }
        }

        // Devolvemos los partidos recién creados para que Thymeleaf los dibuje
        return partidoDao.findAll().stream()
                .filter(p -> p.getFase() != null && p.getFase().getId() == 2)
                .collect(Collectors.toList());
    }

    private void generarSiguienteRonda(List<Partido> partidosFinalizados, long faseActualId) {
        if (faseActualId >= 5) return; // Si es 5, es la Final, no hay siguiente ronda

        List<Equipo> ganadores = new ArrayList<>();
        for (Partido p : partidosFinalizados) {
            ganadores.add(p.getGolesLocal() > p.getGolesVisitante() ? p.getEquipoLocal() : p.getEquipoVisitante());
        }

        Fase siguienteFase = new Fase();
        siguienteFase.setId(faseActualId + 1);

        for (int i = 0; i < ganadores.size(); i += 2) {
            if (i + 1 < ganadores.size()) {
                crearPartido(ganadores.get(i), ganadores.get(i + 1), obtenerEstadioAleatorio(), siguienteFase);
            }
        }
    }

    private void crearPartido(Equipo local, Equipo visitante, Estadio estadio, Fase fase) {
        Partido p = new Partido();
        p.setEquipoLocal(local);
        p.setEquipoVisitante(visitante);
        p.setEstado("PENDIENTE");
        p.setFechaHora(LocalDateTime.now());
        p.setEstadio(estadio); 
        p.setFase(fase);
        partidoDao.save(p);
    }

    private Estadio obtenerEstadioAleatorio() {
        List<Estadio> estadios = estadioDao.findAll();
        if (estadios.isEmpty()) {
            Estadio e = new Estadio();
            e.setNombre("Estadio Nacional");
            e.setCiudad("Sede Principal");
            estadioDao.save(e);
            estadios = estadioDao.findAll();
        }
        return estadios.isEmpty() ? null : estadios.get(new Random().nextInt(estadios.size()));
    }

    // RUTA FALTANTE PARA VER LA SIMULACIÓN INDIVIDUAL DE CADA PARTIDO
    @GetMapping("/partido/{id}")
    public String verPartido(@PathVariable Long id, Model model) {
        Partido partido = partidoDao.findById(id);
        if (partido == null) {
            return "redirect:/simulacion/fase-final";
        }

        model.addAttribute("encuentro", partido);
        
        // Cargamos las plantillas
        List<Jugador> plantillaLocal = jugadorDao.findByEquipo(partido.getEquipoLocal());
        List<Jugador> plantillaVisitante = jugadorDao.findByEquipo(partido.getEquipoVisitante());
        model.addAttribute("plantillaLocal", plantillaLocal);
        model.addAttribute("plantillaVisitante", plantillaVisitante);

        // Simulamos un "Rating / Valoración" aleatorio de FIFA para la vista (75 a 95)
        Map<Long, Double> ratings = new HashMap<>();
        Random rand = new Random();
        for (Jugador j : plantillaLocal) ratings.put(j.getId(), 75.0 + rand.nextDouble() * 20.0);
        for (Jugador j : plantillaVisitante) ratings.put(j.getId(), 75.0 + rand.nextDouble() * 20.0);
        model.addAttribute("ratings", ratings);

        // Preparamos los eventos (Goles y Tarjetas) de manera progresiva para que Javascript dibuje la simulación
        List<Map<String, Object>> eventos = new ArrayList<>();
        int gl = partido.getGolesLocal() != null ? partido.getGolesLocal() : 0;
        int gv = partido.getGolesVisitante() != null ? partido.getGolesVisitante() : 0;

        List<Jugador> campoLocal = plantillaLocal.stream().filter(j -> j.getPosicion() == null || !j.getPosicion().getDescripcion().toLowerCase().contains("portero")).collect(Collectors.toList());
        if (campoLocal.isEmpty()) campoLocal = plantillaLocal;
        
        List<Jugador> campoVisitante = plantillaVisitante.stream().filter(j -> j.getPosicion() == null || !j.getPosicion().getDescripcion().toLowerCase().contains("portero")).collect(Collectors.toList());
        if (campoVisitante.isEmpty()) campoVisitante = plantillaVisitante;

        for (int i = 0; i < gl; i++) {
            Map<String, Object> evt = new HashMap<>();
            evt.put("minuto", rand.nextInt(90) + 1);
            Jugador j = campoLocal.isEmpty() ? new Jugador() : campoLocal.get(rand.nextInt(campoLocal.size()));
            if (j.getEquipo() == null) j.setEquipo(partido.getEquipoLocal());
            if (j.getNombreCompleto() == null) j.setNombreCompleto("Delantero Local");
            evt.put("jugador", j);
            evt.put("tipo", "GOL");
            eventos.add(evt);
        }
        for (int i = 0; i < gv; i++) {
            Map<String, Object> evt = new HashMap<>();
            evt.put("minuto", rand.nextInt(90) + 1);
            Jugador j = campoVisitante.isEmpty() ? new Jugador() : campoVisitante.get(rand.nextInt(campoVisitante.size()));
            if (j.getEquipo() == null) j.setEquipo(partido.getEquipoVisitante());
            if (j.getNombreCompleto() == null) j.setNombreCompleto("Delantero Visitante");
            evt.put("jugador", j);
            evt.put("tipo", "GOL");
            eventos.add(evt);
        }
        
        int totalTarjetas = rand.nextInt(5);
        for (int i = 0; i < totalTarjetas; i++) {
            Map<String, Object> evt = new HashMap<>();
            evt.put("minuto", rand.nextInt(90) + 1);
            boolean isLocal = rand.nextBoolean();
            List<Jugador> team = isLocal ? plantillaLocal : plantillaVisitante;
            Jugador j = team.isEmpty() ? new Jugador() : team.get(rand.nextInt(team.size()));
            if (j.getEquipo() == null) j.setEquipo(isLocal ? partido.getEquipoLocal() : partido.getEquipoVisitante());
            if (j.getNombreCompleto() == null) j.setNombreCompleto("Jugador");
            evt.put("jugador", j);
            evt.put("tipo", rand.nextInt(100) < 80 ? "AMARILLA" : "ROJA");
            eventos.add(evt);
        }
        
        eventos.sort((e1, e2) -> ((Integer) e1.get("minuto")).compareTo((Integer) e2.get("minuto")));
        model.addAttribute("eventos", eventos);

        return "partido-simulacion";
    }

    // RUTA EXTRA PARA ASIGNAR ESTADIOS A PARTIDOS YA CREADOS
    @PostMapping("/asignar-estadios")
    @Transactional
    public String forzarAsignacionEstadios() {
        List<Partido> partidos = partidoDao.findAll();
        // Filtramos para no asignar el 'Estadio Nacional' de emergencia si ya tienes estadios reales
        List<Estadio> estadiosReales = estadioDao.findAll().stream()
                .filter(e -> e.getNombre() != null && !e.getNombre().toLowerCase().contains("nacional"))
                .collect(Collectors.toList());

        if (estadiosReales.isEmpty()) {
            estadiosReales = estadioDao.findAll(); // Respaldo seguro por si acaso
        }

        if (!estadiosReales.isEmpty()) {
            Random rand = new Random();
            for (Partido p : partidos) {
                p.setEstadio(estadiosReales.get(rand.nextInt(estadiosReales.size())));
                partidoDao.save(p);
            }
        }
        return "redirect:/encuentros";
    }
}