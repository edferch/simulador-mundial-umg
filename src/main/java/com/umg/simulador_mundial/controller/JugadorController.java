package com.umg.simulador_mundial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umg.simulador_mundial.model.Jugador;
import com.umg.simulador_mundial.repository.EquipoRepository;
import com.umg.simulador_mundial.repository.JugadorRepository;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @GetMapping
    public String listarJugadores(@RequestParam(name = "buscar", required = false) String buscar, Model model) {
        if (buscar != null && !buscar.isEmpty()) {
            // Si el usuario buscó algo, usamos el algoritmo de búsqueda
            model.addAttribute("jugadores", jugadorRepository.findByNombreContainingIgnoreCase(buscar));
            model.addAttribute("terminoBusqueda", buscar); // Guardamos la palabra para dejarla escrita en pantalla
        } else {
            // Si no buscó nada, mostramos todos
            model.addAttribute("jugadores", jugadorRepository.findAll());
        }
        return "lista-jugadores";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("equipos", equipoRepository.findAll()); // Para el desplegable
        return "formulario-jugador";
    }

    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorRepository.save(jugador);
        return "redirect:/jugadores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            jugadorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Atrapamos el error en caso de que este jugador esté asignado a otras tablas en el futuro
            redirectAttrs.addFlashAttribute("error", "No se puede eliminar este jugador en este momento.");
        }
        return "redirect:/jugadores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepository.findById(id).orElse(null);
        model.addAttribute("jugador", jugador);

        // También necesitamos la lista de equipos para el desplegable
        model.addAttribute("equipos", equipoRepository.findAll());
        return "formulario-jugador";
    }

}