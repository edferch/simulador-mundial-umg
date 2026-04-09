package com.umg.simulador_mundial.controller;

import org.springframework.web.bind.annotation.PostMapping;
import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;

    // Esta ruta hará que cuando escribas "localhost:8080/equipos" en el navegador, cargue la vista
    @GetMapping("/equipos")
    public String listarEquipos(Model model) {
        // Obtenemos todos los equipos de PostgreSQL y los mandamos a la vista
        model.addAttribute("equipos", equipoRepository.findAll());
        return "lista-equipos"; // Este será el nombre de nuestro archivo HTML
    }

    //ruta para ver el formulario
    @GetMapping("/equipos/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("equipo", new Equipo());
        return "formulario-equipo"; // Este será el nombre de nuestro archivo HTML para el formulario
    }

    //ruta para guardar los datos
    @PostMapping("/equipos/guardar")
    public String guardarEquipo(Equipo equipo) {
        equipoRepository.save(equipo);
        return "redirect:/equipos"; // Nos regresa a la lista automáticamente
    }

    @GetMapping("/equipos/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id, RedirectAttributes redirectAttrs){
        try {
            equipoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Si PostgreSQL bloquea la eliminación, atrapamos el error y mandamos un mensaje a la vista
            redirectAttrs.addFlashAttribute("error", "No se puede eliminar la selección porque ya tiene encuentros o jugadores registrados.");
        }
        return "redirect:/equipos";
    }

    @GetMapping("/equipos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model){
        //buscamos el equipo por su ID. si no existe mandamos null
        Equipo equipo = equipoRepository.findById(id).orElse(null);
        model.addAttribute("equipo", equipo);
        return "formulario-equipo"; // Reutilizamos el mismo formulario para editar
    }

    @GetMapping("/sorteo")
    public String realizarSorteoAleatorio(Model model){
        List<Equipo> todosLosEquipos = equipoRepository.findAll();

        //Orden aleatorio
        Collections.shuffle(todosLosEquipos);

        //Letras de los grupos
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

        //Repartir los equipos 4 por grupo
        for (int i = 0; i < todosLosEquipos.size(); i++){
            String grupoAsignado = letras[i / 4]; // Cada 4 equipos, cambia la letra del grupo
            Equipo equipos = todosLosEquipos.get(i);
            equipos.setGrupo(grupoAsignado);
            equipoRepository.save(equipos); // Guardamos el cambio en la base de datos
        }

        //agrupar la lista para mandarla ordenada a el html
        Map<String, List<Equipo>> gruposFormados = todosLosEquipos.stream().collect(Collectors.groupingBy(Equipo::getGrupo));

        model.addAttribute("grupos", gruposFormados);
        return "fase-grupos";
    }

}