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

/**
 * Servicio que proporciona operaciones relacionadas con provincias.
 */
@Service
public class ProvinciaService {
    private final ProvinciaRepository provinciaRepository;

    /**
     * Constructor del servicio ProvinciaService.
     *
     * @param provinciaRepository Repositorio de provincias.
     */
    @Autowired
    public ProvinciaService(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    /**
     * Obtiene todas las provincias de una comunidad por su ID.
     *
     * @param id ID de la comunidad.
     * @return Lista de InfoProvinciaDTO.
     */
    public List<InfoProvinciaDTO> getAllProvinciasByComunidadId(Integer id) {
        var output = provinciaRepository.findAllByComunidadId(id);
        return output.stream()
                .map(p -> convertProvinciaToInfoDTO(p))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene información detallada sobre una provincia específica por su ID.
     *
     * @param id ID de la provincia.
     * @return InfoProvinciaDTO de la provincia encontrada.
     */
    public InfoProvinciaDTO getById(Integer id){
        InfoProvinciaDTO output = null;
        if(provinciaRepository.existsById(id)){
            output = convertProvinciaToInfoDTO(provinciaRepository.findById(id).get());
        }
        return output;
    }

    /**
     * Convierte una entidad Provincia a su representación DTO.
     *
     * @param p Provincia a convertir.
     * @return InfoProvinciaDTO resultante.
     */
    private List<InfoPuntoInteresEnProvinciaDTO> convertPuntoInteresToInfoDTO(List<PuntoInteres> list) {
        List<InfoPuntoInteresEnProvinciaDTO> output = new ArrayList<>();
        for (PuntoInteres poi : list) {
            output.add(new InfoPuntoInteresEnProvinciaDTO(poi.getId(), poi.getName(), poi.getTipoTurismo(), poi.getDescripcion()));
        }
        return output;
    }

    /**
     * Convierte una lista de entidades PuntoInteres a su representación DTO.
     *
     * @param list Lista de puntos de interés a convertir.
     * @return Lista de InfoPuntoInteresEnProvinciaDTO resultante.
     */
    private InfoProvinciaDTO convertProvinciaToInfoDTO(Provincia p){
        return new InfoProvinciaDTO(p.getId(), p.getName(), p.getLatitud(), p.getLongitud(), convertPuntoInteresToInfoDTO(p.getPuntosInteres()));
    }
}
