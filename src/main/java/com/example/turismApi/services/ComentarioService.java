package com.example.turismApi.services;

import com.example.turismApi.model.entity.Comentario;
import com.example.turismApi.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<Comentario> getAll() {
        return comentarioRepository.findAll();
    }
}
