package com.umg.simulador_mundial.controller;

import org.springframework.web.bind.annotation.PostMapping;
import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}