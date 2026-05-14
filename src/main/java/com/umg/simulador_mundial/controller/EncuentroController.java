package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.repository.EncuentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encuentros")
public class EncuentroController {

    @Autowired
    private EncuentroRepository encuentroRepository;

    @GetMapping
    public String listarEncuentros(Model model) {
        model.addAttribute("encuentros", encuentroRepository.findAll());
        return "lista-encuentros";
    }
}