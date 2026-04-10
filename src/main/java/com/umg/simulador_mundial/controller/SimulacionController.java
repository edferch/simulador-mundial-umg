package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.model.Encuentro;
import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Jugador;
import com.umg.simulador_mundial.model.PosicionGrupo;
import com.umg.simulador_mundial.repository.EncuentroRepository;
import com.umg.simulador_mundial.repository.EquipoRepository;
import com.umg.simulador_mundial.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
}