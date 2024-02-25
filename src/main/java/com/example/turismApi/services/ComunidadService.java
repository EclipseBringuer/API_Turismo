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

/**
 * Servicio que proporciona operaciones relacionadas con comunidades.
 */
@Service
public class ComunidadService {
    private final ComunidadRepository comunidadRepository;

    /**
     * Constructor del servicio ComunidadService.
     *
     * @param comunidadRepository Repositorio de comunidades.
     */
    @Autowired
    public ComunidadService(ComunidadRepository comunidadRepository) {
        this.comunidadRepository = comunidadRepository;
    }

    /**
     * Obtiene todas las comunidades con información detallada sobre sus provincias.
     *
     * @return Lista de InfoComunidadDTO.
     */
    public List<InfoComunidadDTO> getAllComunidades() {
        var output = comunidadRepository.findAll();
        return output.stream()
                .map(c -> new InfoComunidadDTO(c.getId(), c.getName(), convertProvinciaToInfoDTO(c.getProvincias())))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene información detallada sobre una comunidad específica.
     *
     * @param id ID de la comunidad.
     * @return InfoComunidadDTO de la comunidad encontrada.
     */
    public InfoComunidadDTO getById(Integer id) {
        InfoComunidadDTO output = null;
        if (comunidadRepository.existsById(id)) {
            output = convertComunidadToInfoDTO(comunidadRepository.findById(id).get());
        }
        return output;
    }

    /**
     * Convierte una entidad Comunidad a su representación DTO.
     *
     * @param comunidad Comunidad a convertir.
     * @return InfoComunidadDTO resultante.
     */
    private InfoComunidadDTO convertComunidadToInfoDTO(Comunidad comunidad) {
        return new InfoComunidadDTO(comunidad.getId(), comunidad.getName(), convertProvinciaToInfoDTO(comunidad.getProvincias()));
    }

    /**
     * Convierte una lista de entidades Provincia a su representación DTO.
     *
     * @param list Lista de provincias a convertir.
     * @return Lista de InfoProvinciaEnComunidadDTO resultante.
     */
    private List<InfoProvinciaEnComunidadDTO> convertProvinciaToInfoDTO(List<Provincia> list) {
        List<InfoProvinciaEnComunidadDTO> output = new ArrayList<>();
        for (Provincia p : list) {
            output.add(new InfoProvinciaEnComunidadDTO(p.getId(), p.getName(), p.getPuntosInteres().size()));
        }
        return output;
    }
}
