package com.example.turismApi.services;

import com.example.turismApi.model.entity.Comunidad;
import com.example.turismApi.repositories.ComunidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComunidadService {
    private final ComunidadRepository comunidadRepository;

    @Autowired
    public ComunidadService(ComunidadRepository comunidadRepository) {
        this.comunidadRepository = comunidadRepository;
    }

    public List<Comunidad> getAllComunidades(){
        return comunidadRepository.findAll();
    }
}
