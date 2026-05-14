package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.dao.PartidoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoDAO partidoDao;

    @GetMapping
    public String listarPartidos(Model model) {
        model.addAttribute("partidos", partidoDao.findAll());
        return "lista-encuentros"; // Puedes seguir usando el mismo HTML
    }
}