package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.dao.EncuentroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encuentros")
public class EncuentroController {

    @Autowired
    private EncuentroDAO encuentroDao;

    @GetMapping
    public String listarEncuentros(Model model) {
        model.addAttribute("encuentros", encuentroDao.findAll());
        return "lista-encuentros";
    }
}