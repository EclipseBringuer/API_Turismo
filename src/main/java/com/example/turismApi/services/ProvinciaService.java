package com.example.turismApi.services;

import com.example.turismApi.model.entity.Provincia;
import com.example.turismApi.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService {
    private final ProvinciaRepository provinciaRepository;

    @Autowired
    public ProvinciaService(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    public List<Provincia> getAllProvinciasByComunidadId(Integer id){
        return provinciaRepository.findAllByComunidadId(id);
    }
}
