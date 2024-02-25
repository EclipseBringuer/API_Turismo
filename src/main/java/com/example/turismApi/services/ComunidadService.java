package com.example.turismApi.services;

import com.example.turismApi.model.dto.comunidad.InfoComunidadDTO;
import com.example.turismApi.model.dto.comunidad.InfoProvinciaEnComunidadDTO;
import com.example.turismApi.model.entity.Comunidad;
import com.example.turismApi.model.entity.Provincia;
import com.example.turismApi.repositories.ComunidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComunidadService {
    private final ComunidadRepository comunidadRepository;

    @Autowired
    public ComunidadService(ComunidadRepository comunidadRepository) {
        this.comunidadRepository = comunidadRepository;
    }

    public List<InfoComunidadDTO> getAllComunidades() {
        var output = comunidadRepository.findAll();
        return output.stream()
                .map(c -> new InfoComunidadDTO(c.getId(), c.getName(), convertProvinciaToInfoDTO(c.getProvincias())))
                .collect(Collectors.toList());
    }

    public InfoComunidadDTO getById(Integer id) {
        InfoComunidadDTO output = null;
        if (comunidadRepository.existsById(id)) {
            output = convertComunidadToInfoDTO(comunidadRepository.findById(id).get());
        }
        return output;
    }

    private InfoComunidadDTO convertComunidadToInfoDTO(Comunidad comunidad) {
        return new InfoComunidadDTO(comunidad.getId(), comunidad.getName(), convertProvinciaToInfoDTO(comunidad.getProvincias()));
    }

    private List<InfoProvinciaEnComunidadDTO> convertProvinciaToInfoDTO(List<Provincia> list) {
        List<InfoProvinciaEnComunidadDTO> output = new ArrayList<>();
        for (Provincia p : list) {
            output.add(new InfoProvinciaEnComunidadDTO(p.getId(), p.getName(), p.getPuntosInteres().size()));
        }
        return output;
    }
}
