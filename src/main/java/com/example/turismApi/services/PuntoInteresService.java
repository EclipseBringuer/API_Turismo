package com.example.turismApi.services;

import com.example.turismApi.model.dto.puntoInteres.ComentarioToPuntoInteresDTO;
import com.example.turismApi.model.dto.puntoInteres.InfoPuntoInteresDTO;
import com.example.turismApi.model.dto.puntoInteres.ValoracionToPuntoInteresDTO;
import com.example.turismApi.model.entity.Comentario;
import com.example.turismApi.model.entity.PuntoInteres;
import com.example.turismApi.model.entity.Valoracion;
import com.example.turismApi.repositories.PuntoInteresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que proporciona operaciones relacionadas con puntos de interés turístico.
 */
@Service
public class PuntoInteresService {
    private final PuntoInteresRepository puntoInteresRepository;

    /**
     * Constructor del servicio PuntoInteresService.
     *
     * @param puntoInteresRepository Repositorio de puntos de interés.
     */
    @Autowired
    public PuntoInteresService(PuntoInteresRepository puntoInteresRepository) {
        this.puntoInteresRepository = puntoInteresRepository;
    }

    /**
     * Obtiene información detallada sobre todos los puntos de interés.
     *
     * @return Lista de InfoPuntoInteresDTO.
     */
    public List<InfoPuntoInteresDTO> getAll(){
        var output = puntoInteresRepository.findAll();
        return output.stream()
                .map(poi -> new InfoPuntoInteresDTO(poi.getName(),
                        poi.getTipoTurismo(),
                        poi.getDescripcion(),
                        poi.getPrecio(),
                        poi.getLatitud(),
                        poi.getLongitud(),
                        convertComentarioToInfoDTO(poi.getComentarios()),
                        convertValoracionToInfoDTO(poi.getValoraciones())
                ))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene información detallada sobre los puntos de interés en una provincia específica por su ID.
     *
     * @param id ID de la provincia.
     * @return Lista de InfoPuntoInteresDTO.
     */
    public List<InfoPuntoInteresDTO> getAllByProvinciaId(Integer id) {
        var output = puntoInteresRepository.getAllById_provincia(id);
        return output.stream()
                .map(poi -> new InfoPuntoInteresDTO(poi.getName(),
                        poi.getTipoTurismo(),
                        poi.getDescripcion(),
                        poi.getPrecio(),
                        poi.getLatitud(),
                        poi.getLongitud(),
                        convertComentarioToInfoDTO(poi.getComentarios()),
                        convertValoracionToInfoDTO(poi.getValoraciones())
                ))
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de entidades Comentario a su representación DTO.
     *
     * @param list Lista de comentarios a convertir.
     * @return Lista de ComentarioToPuntoInteresDTO resultante.
     */
    private List<ComentarioToPuntoInteresDTO> convertComentarioToInfoDTO(List<Comentario> list) {
        List<ComentarioToPuntoInteresDTO> output = new ArrayList<>();
        for (Comentario c : list) {
            output.add(new ComentarioToPuntoInteresDTO(c.getUsuario().getName(), c.getContenido()));
        }
        return output;
    }
    /**
     * Convierte una lista de entidades Valoracion a su representación DTO.
     *
     * @param list Lista de valoraciones a convertir.
     * @return Lista de ValoracionToPuntoInteresDTO resultante.
     */

    private List<ValoracionToPuntoInteresDTO> convertValoracionToInfoDTO(List<Valoracion> list) {
        List<ValoracionToPuntoInteresDTO> output = new ArrayList<>();
        for (Valoracion v : list) {
            output.add(new ValoracionToPuntoInteresDTO(v.getUsuario().getName(), v.getPuntuacion()));
        }
        return output;
    }

    /**
     * Obtiene información detallada sobre un punto de interés específico por su ID.
     *
     * @param id ID del punto de interés.
     * @return InfoPuntoInteresDTO del punto de interés encontrado.
     */
    public InfoPuntoInteresDTO getById(Integer id) {
        InfoPuntoInteresDTO output = null;
        if (puntoInteresRepository.existsById(id)) {
            output = convertPuntoInteresToInfoDTO(puntoInteresRepository.findById(id).get());
        }
        return output;
    }

    /**
     * Obtiene un punto de interés por su ID sin convertirlo a DTO.
     *
     * @param id ID del punto de interés.
     * @return Punto de interés encontrado.
     */
    public PuntoInteres getByIdNotDTO(Integer id){
        PuntoInteres output = null;
        if(puntoInteresRepository.existsById(id)){
            output = puntoInteresRepository.findById(id).get();
        }
        return output;
    }

    /**
     * Convierte una entidad PuntoInteres a su representación DTO.
     *
     * @param poi Punto de interés a convertir.
     * @return InfoPuntoInteresDTO resultante.
     */
    private InfoPuntoInteresDTO convertPuntoInteresToInfoDTO(PuntoInteres poi) {
        return new InfoPuntoInteresDTO(poi.getName(),
                poi.getTipoTurismo(),
                poi.getDescripcion(),
                poi.getPrecio(),
                poi.getLatitud(),
                poi.getLongitud(),
                convertComentarioToInfoDTO(poi.getComentarios()),
                convertValoracionToInfoDTO(poi.getValoraciones()));
    }
}