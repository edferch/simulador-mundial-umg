package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.dao.EstadioDAO;
import com.umg.simulador_mundial.model.Estadio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/estadios")
public class EstadioController {

    @Autowired private EstadioDAO estadioDao;

    @GetMapping
    public String listarEstadios(Model model) {
        List<Estadio> estadios = estadioDao.findAll();
        model.addAttribute("estadios", estadios);
        return "estadios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estadio", new Estadio());
        return "formulario-estadio";
    }

    @PostMapping("/guardar")
    public String guardarEstadio(@ModelAttribute Estadio estadio) {
        estadioDao.save(estadio);
        return "redirect:/estadios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        Estadio estadio = estadioDao.findById(id);
        if (estadio == null) {
            redirectAttrs.addFlashAttribute("error", "Error: El estadio no existe o hubo un problema de conexión.");
            return "redirect:/estadios";
        }
        model.addAttribute("estadio", estadio);
        return "formulario-estadio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstadio(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            estadioDao.deleteById(id);
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("error", "No se puede eliminar el estadio en este momento porque está en uso.");
        }
        return "redirect:/estadios";
    }
}