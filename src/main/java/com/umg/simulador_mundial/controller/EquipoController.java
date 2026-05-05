package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.dao.EquipoDAO;
import com.umg.simulador_mundial.dao.JugadorDAO;
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
    private EquipoDAO equipoDao;

    @Autowired
    private JugadorDAO jugadorDao;

    @GetMapping("/equipos")
    public String verSeleccion(@RequestParam(name = "id", required = false) Long id, Model model) {
        List<Equipo> todos = equipoDao.findAll();
        
        if (todos.isEmpty()) return "redirect:/";

        // Buscamos el índice correcto comparando los IDs explícitamente
        int indexActual = 0; // Por defecto mostramos el primero
        if (id != null) {
            for (int i = 0; i < todos.size(); i++) {
                if (todos.get(i).getId().equals(id)) {
                    indexActual = i;
                    break;
                }
            }
        }

        Equipo equipoActual = todos.get(indexActual);

        // Calculamos quién es el anterior y el siguiente
        Long idPrev = todos.get(indexActual > 0 ? indexActual - 1 : todos.size() - 1).getId();
        Long idNext = todos.get(indexActual < todos.size() - 1 ? indexActual + 1 : 0).getId();

        model.addAttribute("equipo", equipoActual);
        model.addAttribute("jugadores", jugadorDao.findByEquipo(equipoActual));
        model.addAttribute("idPrev", idPrev);
        model.addAttribute("idNext", idNext);
        
        return "lista-equipos";
    }

    @GetMapping("/equipos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "formulario-equipo";
    }

    @PostMapping("/equipos/guardar")
    public String guardarEquipo(Equipo equipo) {
        equipoDao.save(equipo);
        return "redirect:/equipos"; 
    }

    @GetMapping("/equipos/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            equipoDao.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "No se puede eliminar la selección porque ya tiene encuentros o jugadores registrados.");
        }
        return "redirect:/equipos";
    }

    @GetMapping("/equipos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Equipo equipo = equipoDao.findById(id);
        model.addAttribute("equipo", equipo);
        return "formulario-equipo";
    }
}