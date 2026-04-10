package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.repository.EquipoRepository;
import com.umg.simulador_mundial.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    // RUTA PRINCIPAL: Muestra el selector de equipos y su plantilla (Imagen 1 y 2)
    @GetMapping("/equipos")
    public String verSeleccion(@RequestParam(name = "id", required = false) Long id, Model model) {
        List<Equipo> todos = equipoRepository.findAll();
        
        // Si no hay equipos, regresamos al inicio para evitar errores
        if (todos.isEmpty()) return "redirect:/";

        // Buscamos el equipo actual por ID. Si no viene ID, cargamos el primero de la lista
        Equipo equipoActual = (id == null) ? todos.get(0) : 
            equipoRepository.findById(id).orElse(todos.get(0));

        // LÓGICA DE NAVEGACIÓN (FLECHAS): Calculamos el ID anterior y siguiente en la lista
        int indexActual = todos.indexOf(equipoActual);
        Long idPrev = todos.get(indexActual > 0 ? indexActual - 1 : todos.size() - 1).getId();
        Long idNext = todos.get(indexActual < todos.size() - 1 ? indexActual + 1 : 0).getId();

        // Mandamos los datos a la vista
        model.addAttribute("equipo", equipoActual);
        model.addAttribute("jugadores", jugadorRepository.findByEquipo(equipoActual));
        model.addAttribute("idPrev", idPrev);
        model.addAttribute("idNext", idNext);
        
        return "lista-equipos"; // Este HTML ahora centraliza selección y jugadores
    }

    // Ruta para mostrar el formulario de registro
    @GetMapping("/equipos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "formulario-equipo";
    }

    // Ruta para guardar o actualizar datos
    @PostMapping("/equipos/guardar")
    public String guardarEquipo(Equipo equipo) {
        equipoRepository.save(equipo);
        return "redirect:/equipos?id=" + equipo.getId(); // Redirige directamente al equipo guardado
    }

    // Ruta para eliminar una selección con manejo de excepciones
    @GetMapping("/equipos/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            equipoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // CUMPLE RÚBRICA: Manejo de excepciones para evitar cierres inesperados
            redirectAttrs.addFlashAttribute("error", "No se puede eliminar la selección porque ya tiene encuentros o jugadores registrados.");
        }
        return "redirect:/equipos";
    }

    // Ruta para editar (reutiliza el formulario de registro)
    @GetMapping("/equipos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Equipo equipo = equipoRepository.findById(id).orElse(null);
        model.addAttribute("equipo", equipo);
        return "formulario-equipo";
    }
}