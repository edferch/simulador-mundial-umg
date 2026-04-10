package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.model.Encuentro;
import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.repository.EncuentroRepository;
import com.umg.simulador_mundial.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/simulacion")
public class SimulacionController {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EncuentroRepository encuentroRepository;

    // 1. Cargar el Panel Dashboard de Simulación
    @GetMapping
    public String cargarPanel(Model model) {
        List<Equipo> todosLosEquipos = equipoRepository.findAll();
        
        // Agrupamos los que ya tienen grupo
        Map<String, List<Equipo>> gruposFormados = todosLosEquipos.stream()
                .filter(e -> e.getGrupo() != null && !e.getGrupo().isEmpty())
                .collect(Collectors.groupingBy(Equipo::getGrupo));

        // ¿Ya se hizo el sorteo?
        model.addAttribute("sorteoRealizado", !gruposFormados.isEmpty());
        model.addAttribute("grupos", gruposFormados);

        return "simulacion-dashboard"; // Pronto crearemos esta vista
    }

    // 2. Ejecutar Sorteo y GENERAR PARTIDOS
    @PostMapping("/sortear")
    public String ejecutarSorteo() {
        // Limpiamos encuentros anteriores para no duplicar si se sortea de nuevo
        encuentroRepository.deleteAll(); 

        List<Equipo> equipos = equipoRepository.findAll();
        Collections.shuffle(equipos); // Barajar
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        
        // 1. Asignar Grupos
        for (int i = 0; i < equipos.size(); i++) {
            Equipo eq = equipos.get(i);
            eq.setGrupo(letras[i / 4]);
            equipoRepository.save(eq);
        }

        // 2. Crear los Encuentros (Todos contra Todos por grupo)
        Map<String, List<Equipo>> grupos = equipoRepository.findAll().stream()
                .filter(e -> e.getGrupo() != null)
                .collect(Collectors.groupingBy(Equipo::getGrupo));

        for (List<Equipo> grupoEquipos : grupos.values()) {
            if (grupoEquipos.size() == 4) {
                // Combinaciones de 4 equipos (0 vs 1, 2 vs 3, 0 vs 2, 1 vs 3, 0 vs 3, 1 vs 2)
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

    // Método auxiliar para no repetir código al guardar partidos
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
}