package com.example.turismApi.controllers;

import com.example.turismApi.model.entity.Comunidad;
import com.example.turismApi.services.ComunidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/comunidades")
public class ComunidadController {
    @Autowired
    private ComunidadService comunidadService;

    @GetMapping("")
    public List<Comunidad> getAllComunidades(){
        return comunidadService.getAllComunidades();
    }
}
