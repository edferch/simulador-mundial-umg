package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.dao.PartidoDAO;
import com.umg.simulador_mundial.model.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/encuentros")
public class CalendarioController {

    @Autowired private PartidoDAO partidoDao;

    @GetMapping
    public String verCalendario(Model model) {
        List<Partido> partidos = partidoDao.findAll();
        model.addAttribute("partidos", partidos);
        return "lista-encuentros";
    }
}