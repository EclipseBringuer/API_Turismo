package com.example.turismApi.services;

import com.example.turismApi.model.entity.PuntoInteres;
import com.example.turismApi.repositories.PuntoInteresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PuntoInteresService {
    private final PuntoInteresRepository puntoInteresRepository;

    @Autowired
    public PuntoInteresService(PuntoInteresRepository puntoInteresRepository){
        this.puntoInteresRepository = puntoInteresRepository;
    }

    public List<PuntoInteres> getAllByProvinciaId(Integer id){
        return puntoInteresRepository.getAllById_provincia(id);
    }

    public PuntoInteres getById(Integer id){
        return puntoInteresRepository.getPuntoInteresBy(id);
    }
}
