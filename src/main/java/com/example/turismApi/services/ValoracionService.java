package com.example.turismApi.services;

import com.example.turismApi.model.entity.Valoracion;
import com.example.turismApi.repositories.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionService {
    private final ValoracionRepository valoracionRepository;

    @Autowired
    public ValoracionService(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    public List<Valoracion> getAll() {
        return valoracionRepository.findAll();
    }
}
