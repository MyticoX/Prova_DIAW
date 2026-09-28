package com.example.CandidatosTSE.controller;

import com.example.CandidatosTSE.model.Candidato;
import com.example.CandidatosTSE.service.CandidatosTseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CandidatosTseController {

    private final CandidatosTseService service;

    public CandidatosTseController(CandidatosTseService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String escolaridade,
            @RequestParam(required = false) Integer idadeMin,
            @RequestParam(required = false) Integer idadeMax,
            Model model) {

        List<Candidato> candidatos = service.filtrarPerfil(
                genero, escolaridade, idadeMin, idadeMax);

        System.out.println(">>> CANDIDATOS ENCONTRADOS: " + candidatos.size());

        model.addAttribute("candidatos", candidatos);
        model.addAttribute("totalEncontrado", candidatos.size());
        model.addAttribute("generos", service.listarGeneros());
        model.addAttribute("escolaridades", service.listarEscolaridades());
        model.addAttribute("generoSelecionado", genero == null ? "" : genero);
        model.addAttribute("escolaridadeSelecionada", escolaridade == null ? "" : escolaridade);
        model.addAttribute("idadeMin", idadeMin);
        model.addAttribute("idadeMax", idadeMax);

        return "home";
    }
}
