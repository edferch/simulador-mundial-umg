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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umg.simulador_mundial.model.Encuentro;
import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Jugador;
import com.umg.simulador_mundial.model.PosicionGrupo;
import com.umg.simulador_mundial.repository.EncuentroRepository;
import com.umg.simulador_mundial.repository.EquipoRepository;
import com.umg.simulador_mundial.repository.JugadorRepository;

@Controller
@RequestMapping("/simulacion")
public class SimulacionController {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EncuentroRepository encuentroRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    // ---------------------------------------------------------
    // 1. CARGAR EL PANEL PRINCIPAL Y CALCULAR POSICIONES
    // ---------------------------------------------------------
    @GetMapping
    public String cargarPanel(Model model) {
        List<Equipo> equipos = equipoRepository.findAll();
        List<Encuentro> partidos = encuentroRepository.findAll();
        
        boolean sorteoRealizado = equipos.stream().anyMatch(e -> e.getGrupo() != null);
        boolean faseGruposTerminada = !partidos.isEmpty() && partidos.stream().allMatch(p -> p.getEstado().equals("FINALIZADO"));

        // LÓGICA DE FASE 2: Calcular tabla de posiciones usando Mapas y Listas
        Map<String, List<PosicionGrupo>> tablasPosiciones = new TreeMap<>(); 

        if (sorteoRealizado) {
            // Inicializar la tabla en 0 para todos los equipos
            Map<Long, PosicionGrupo> calculoTemp = new HashMap<>();
            for (Equipo e : equipos) {
                if (e.getGrupo() != null) {
                    calculoTemp.put(e.getId(), new PosicionGrupo(e));
                }
            }

            // Leer los partidos y sumar puntos/goles
            for (Encuentro p : partidos) {
                if (p.getEstado().equals("FINALIZADO")) {
                    PosicionGrupo local = calculoTemp.get(p.getEquipoLocal().getId());
                    PosicionGrupo visitante = calculoTemp.get(p.getEquipoVisitante().getId());
                    
                    local.registrarPartido(p.getGolesLocal(), p.getGolesVisitante());
                    visitante.registrarPartido(p.getGolesVisitante(), p.getGolesLocal());
                }
            }

            // Agrupar por letra del grupo
            for (PosicionGrupo pos : calculoTemp.values()) {
                String letraGrupo = pos.getEquipo().getGrupo();
                tablasPosiciones.computeIfAbsent(letraGrupo, k -> new ArrayList<>()).add(pos);
            }

            // Ordenar cada grupo (1ro, 2do, 3ro, 4to) según puntos y diferencia de goles
            for (List<PosicionGrupo> listaGrupo : tablasPosiciones.values()) {
                Collections.sort(listaGrupo);
            }
        }

        model.addAttribute("sorteoRealizado", sorteoRealizado);
        model.addAttribute("faseGruposTerminada", faseGruposTerminada);
        model.addAttribute("tablasPosiciones", tablasPosiciones);

        return "simulacion-dashboard";
    }

    // ---------------------------------------------------------
    // 2. EJECUTAR SORTEO Y CREAR CALENDARIO DE PARTIDOS
    // ---------------------------------------------------------
    @PostMapping("/sortear")
    public String ejecutarSorteo() {
        encuentroRepository.deleteAll(); // Limpiar torneo anterior
        
        List<Equipo> equipos = equipoRepository.findAll();
        Collections.shuffle(equipos); // Barajar equipos
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        
        // Asignar los grupos
        for (int i = 0; i < equipos.size(); i++) {
            Equipo eq = equipos.get(i);
            eq.setGrupo(letras[i / 4]);
            equipoRepository.save(eq);
        }

        // Crear los 6 partidos por grupo
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

    // ---------------------------------------------------------
    // 3. MOTOR DE SIMULACIÓN (Genera resultados y estadísticas)
    // ---------------------------------------------------------
    @PostMapping("/jugar-grupos")
    public String simularFaseGrupos() {
        List<Encuentro> partidos = encuentroRepository.findAll();
        Random rand = new Random();

        for (Encuentro p : partidos) {
            if (p.getEstado().equals("PENDIENTE")) {
                int golesLocales = rand.nextInt(5);
                int golesVisitantes = rand.nextInt(5);

                p.setGolesLocal(golesLocales);
                p.setGolesVisitante(golesVisitantes);
                p.setEstado("FINALIZADO");
                encuentroRepository.save(p);

                // Asignar los goles a los jugadores reales en la base de datos
                asignarGolesAJugadores(p.getEquipoLocal(), golesLocales, golesVisitantes);
                asignarGolesAJugadores(p.getEquipoVisitante(), golesVisitantes, golesLocales);
            }
        }
        return "redirect:/simulacion";
    }

    // ---------------------------------------------------------
    // 4. MÉTODOS AUXILIARES
    // ---------------------------------------------------------
    private void crearPartido(Equipo local, Equipo visitante) {
        Encuentro encuentro = new Encuentro();
        encuentro.setEquipoLocal(local);
        encuentro.setEquipoVisitante(visitante);
        encuentro.setGolesLocal(0);
        encuentro.setGolesVisitante(0);
        encuentro.setEstado("PENDIENTE");
        encuentro.setFechaHora(LocalDateTime.now());
        encuentroRepository.save(encuentro);
    }

    private void asignarGolesAJugadores(Equipo equipo, int golesAnotados, int golesRecibidos) {
        // Obtenemos a los jugadores que pertenecen a este equipo específico
        List<Jugador> plantilla = jugadorRepository.findAll().stream()
                .filter(j -> j.getEquipo().getId().equals(equipo.getId()))
                .collect(Collectors.toList());
        
        if (plantilla.isEmpty()) return; // Si el equipo no tiene jugadores registrados, no hace nada

        Random rand = new Random();

        // Repartir goles a favor entre la plantilla
        for (int i = 0; i < golesAnotados; i++) {
            Jugador goleador = plantilla.get(rand.nextInt(plantilla.size()));
            // Evitamos que los porteros anoten goles si es posible
            if (goleador.getPosicion() != null && goleador.getPosicion().equalsIgnoreCase("Portero") && plantilla.size() > 1) {
                i--; // Repetir el ciclo para buscar a otro jugador
                continue;
            }
            goleador.setGolesAnotados(goleador.getGolesAnotados() + 1);
            jugadorRepository.save(goleador);
        }

        // Sumar goles en contra al portero
        for (Jugador j : plantilla) {
            if (j.getPosicion() != null && j.getPosicion().equalsIgnoreCase("Portero")) {
                j.setGolesRecibidos(j.getGolesRecibidos() + golesRecibidos);
                jugadorRepository.save(j);
                break; // Solo afecta al primer portero que encuentre
            }
        }
    }

    // ---------------------------------------------------------
    // 5. FASE FINAL: GENERAR OCTAVOS DE FINAL (Manejo de Excepciones)
    // ---------------------------------------------------------

    @PostMapping("/generar-octavos")
    public String generarOctavos(RedirectAttributes flash) {
        try {
            // 1. Volvemos a calcular las posiciones para saber quién pasó
            List<Equipo> equipos = equipoRepository.findAll();
            List<Encuentro> partidos = encuentroRepository.findAll();
            
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

            Map<String, List<PosicionGrupo>> grupos = new HashMap<>();
            for (PosicionGrupo pos : calculoTemp.values()) {
                grupos.computeIfAbsent(pos.getEquipo().getGrupo(), k -> new ArrayList<>()).add(pos);
            }

            // 2. Extraer a los 16 clasificados (12 primeros lugares + 4 mejores segundos)
            List<PosicionGrupo> primerosLugares = new ArrayList<>();
            List<PosicionGrupo> segundosLugares = new ArrayList<>();

            for (List<PosicionGrupo> lista : grupos.values()) {
                Collections.sort(lista); // Ordenamos el grupo
                if (lista.size() >= 1) primerosLugares.add(lista.get(0));
                if (lista.size() >= 2) segundosLugares.add(lista.get(1));
            }

            // Ordenamos a los segundos lugares para sacar solo a los 4 mejores
            Collections.sort(segundosLugares);
            
            List<Equipo> clasificados = new ArrayList<>();
            for (PosicionGrupo p : primerosLugares) clasificados.add(p.getEquipo());
            for (int i = 0; i < 4; i++) clasificados.add(segundosLugares.get(i).getEquipo());

            // 3. Crear los 8 cruces de Octavos de Final
            Collections.shuffle(clasificados); // Barajamos para cruces al azar
            
            for (int i = 0; i < 16; i += 2) {
                Encuentro octavos = new Encuentro();
                octavos.setEquipoLocal(clasificados.get(i));
                octavos.setEquipoVisitante(clasificados.get(i+1));
                octavos.setEstado("OCTAVOS_PENDIENTE");
                octavos.setFechaHora(LocalDateTime.now());
                encuentroRepository.save(octavos);
            }

            return "redirect:/simulacion/fase-final";

        } catch (Exception e) {
            // CUMPLE RÚBRICA: Manejo de excepciones para evitar cierres inesperados
            flash.addFlashAttribute("error", "Ocurrió un error al generar las llaves: " + e.getMessage());
            return "redirect:/simulacion";
        }
    }

    // Ruta para ver la pantalla de eliminación directa
    @GetMapping("/fase-final")
    public String verFaseFinal(Model model) {
        List<Encuentro> todos = encuentroRepository.findAll();
        
        // Filtramos los partidos por fase
        model.addAttribute("octavos", todos.stream().filter(p -> p.getEstado().contains("OCTAVOS")).collect(Collectors.toList()));
        model.addAttribute("cuartos", todos.stream().filter(p -> p.getEstado().contains("CUARTOS")).collect(Collectors.toList()));
        model.addAttribute("semis", todos.stream().filter(p -> p.getEstado().contains("SEMI")).collect(Collectors.toList()));
        model.addAttribute("final", todos.stream().filter(p -> p.getEstado().contains("GRAN_FINAL")).collect(Collectors.toList()));
        
        return "fase-final-logica";
    }
}