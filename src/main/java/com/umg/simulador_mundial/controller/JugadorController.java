package com.umg.simulador_mundial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umg.simulador_mundial.dao.EquipoDAO;
import com.umg.simulador_mundial.dao.JugadorDAO;
import com.umg.simulador_mundial.dao.PosicionDAO;
import com.umg.simulador_mundial.model.Jugador;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired private JugadorDAO jugadorDao;
    @Autowired private EquipoDAO equipoDao;
    @Autowired private PosicionDAO posicionDao; // Inyectamos el catálogo de posiciones

    @GetMapping
    public String listarJugadores(@RequestParam(name = "buscar", required = false) String buscar, Model model) {
        if (buscar != null && !buscar.isEmpty()) {
            model.addAttribute("jugadores", jugadorDao.findByNombreContainingIgnoreCase(buscar));
        } else {
            model.addAttribute("jugadores", jugadorDao.findAll());
        }
        return "lista-jugadores";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("equipos", equipoDao.findAll());
        model.addAttribute("posiciones", posicionDao.findAll()); // Mandamos la lista de posiciones
        return "formulario-jugador";
    }

    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorDao.save(jugador);
        return "redirect:/equipos?id=" + jugador.getEquipo().getId();
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        Jugador j = jugadorDao.findById(id);
        Long equipoId = (j != null) ? j.getEquipo().getId() : null;
        try {
            jugadorDao.deleteById(id);
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("error", "No se puede eliminar este jugador en este momento.");
        }
        return equipoId != null ? "redirect:/equipos?id=" + equipoId : "redirect:/jugadores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorDao.findById(id);
        model.addAttribute("jugador", jugador);
        model.addAttribute("equipos", equipoDao.findAll());
        model.addAttribute("posiciones", posicionDao.findAll()); // Mandamos la lista de posiciones
        return "formulario-jugador";
    }
}