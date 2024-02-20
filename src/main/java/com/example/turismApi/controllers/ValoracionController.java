package com.example.turismApi.controllers;

import com.example.turismApi.model.entity.Valoracion;
import com.example.turismApi.services.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValoracionController {
    @Autowired
    private ValoracionService valoracionService;

    @GetMapping("/valoraciones/getAll")
    public List<Valoracion> getAll(){
        return valoracionService.getAll();
    }
}
