package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.model.Estadio;
import com.umg.simulador_mundial.dao.EstadioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estadios")
public class EstadioController {

    @Autowired
    private EstadioDAO estadioDao;

    @GetMapping
    public String listarEstadios(Model model) {
        model.addAttribute("estadios", estadioDao.findAll());
        return "lista-estadios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estadio", new Estadio());
        return "formulario-estadio";
    }

    @PostMapping("/guardar")
    public String guardarEstadio(Estadio estadio) {
        estadioDao.save(estadio);
        return "redirect:/estadios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstadio(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            estadioDao.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("error", "No se puede eliminar este estadio porque está en uso.");
        }
        return "redirect:/estadios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Estadio estadio = estadioDao.findById(id);
        if (estadio == null) return "redirect:/estadios";
        
        model.addAttribute("estadio", estadio);
        return "formulario-estadio";
    }
}