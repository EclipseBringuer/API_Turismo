package com.example.turismApi.services;

import com.example.turismApi.model.dto.provincia.InfoProvinciaDTO;
import com.example.turismApi.model.dto.provincia.InfoPuntoInteresEnProvinciaDTO;
import com.example.turismApi.model.entity.Provincia;
import com.example.turismApi.model.entity.PuntoInteres;
import com.example.turismApi.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinciaService {
    private final ProvinciaRepository provinciaRepository;

    @Autowired
    public ProvinciaService(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    public List<InfoProvinciaDTO> getAllProvinciasByComunidadId(Integer id) {
        var output = provinciaRepository.findAllByComunidadId(id);
        return output.stream()
                .map(p -> convertProvinciaToInfoDTO(p))
                .collect(Collectors.toList());
    }

    public InfoProvinciaDTO getById(Integer id){
        InfoProvinciaDTO output = null;
        if(provinciaRepository.existsById(id)){
            output = convertProvinciaToInfoDTO(provinciaRepository.findById(id).get());
        }
        return output;
    }

    private List<InfoPuntoInteresEnProvinciaDTO> convertPuntoInteresToInfoDTO(List<PuntoInteres> list) {
        List<InfoPuntoInteresEnProvinciaDTO> output = new ArrayList<>();
        for (PuntoInteres poi : list) {
            output.add(new InfoPuntoInteresEnProvinciaDTO(poi.getId(), poi.getName(), poi.getTipoTurismo(), poi.getDescripcion()));
        }
        return output;
    }

    private InfoProvinciaDTO convertProvinciaToInfoDTO(Provincia p){
        return new InfoProvinciaDTO(p.getId(), p.getName(), p.getLatitud(), p.getLongitud(), convertPuntoInteresToInfoDTO(p.getPuntosInteres()));
    }
}
