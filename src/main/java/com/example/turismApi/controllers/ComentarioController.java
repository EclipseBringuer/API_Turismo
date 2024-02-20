package com.example.turismApi.controllers;

import com.example.turismApi.model.entity.Comentario;
import com.example.turismApi.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/comentarios/getAll")
    public List<Comentario> getAll() {
        return comentarioService.getAll();
    }
}
