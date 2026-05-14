package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.dao.EstadioDAO;
import com.umg.simulador_mundial.model.Estadio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}