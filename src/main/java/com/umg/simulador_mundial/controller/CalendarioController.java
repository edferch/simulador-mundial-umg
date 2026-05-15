package com.umg.simulador_mundial.controller;

import com.umg.simulador_mundial.dao.PartidoDAO;
import com.umg.simulador_mundial.model.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.stream.Collectors;

import java.util.List;

@Controller
@RequestMapping("/encuentros")
public class CalendarioController {

    @Autowired private PartidoDAO partidoDao;

    @GetMapping
    public String verCalendario(@RequestParam(name = "fase", required = false) String faseFiltro, Model model) {
        List<Partido> partidos = partidoDao.findAll();
        
        if (faseFiltro != null && !faseFiltro.isEmpty() && !faseFiltro.equals("todas")) {
            partidos = partidos.stream().filter(p -> {
                if (p.getFase() == null) return false;
                long faseId = p.getFase().getId();
                switch (faseFiltro) {
                    case "grupos": return faseId == 1L;
                    case "octavos": return faseId == 2L;
                    case "cuartos": return faseId == 3L;
                    case "semis-final": return faseId == 4L || faseId == 5L;
                    default: return true;
                }
            }).collect(Collectors.toList());
        }

        model.addAttribute("partidos", partidos);
        model.addAttribute("encuentros", partidos); // Alias para que el HTML lo encuentre
        model.addAttribute("faseActual", faseFiltro);
        return "lista-encuentros";
    }
}