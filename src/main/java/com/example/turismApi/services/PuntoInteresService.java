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

@Service
public class PuntoInteresService {
    private final PuntoInteresRepository puntoInteresRepository;

    @Autowired
    public PuntoInteresService(PuntoInteresRepository puntoInteresRepository) {
        this.puntoInteresRepository = puntoInteresRepository;
    }

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

    private List<ComentarioToPuntoInteresDTO> convertComentarioToInfoDTO(List<Comentario> list) {
        List<ComentarioToPuntoInteresDTO> output = new ArrayList<>();
        for (Comentario c : list) {
            output.add(new ComentarioToPuntoInteresDTO(c.getUsuario().getName(), c.getContenido()));
        }
        return output;
    }

    private List<ValoracionToPuntoInteresDTO> convertValoracionToInfoDTO(List<Valoracion> list) {
        List<ValoracionToPuntoInteresDTO> output = new ArrayList<>();
        for (Valoracion v : list) {
            output.add(new ValoracionToPuntoInteresDTO(v.getUsuario().getName(), v.getPuntuacion()));
        }
        return output;
    }

    public InfoPuntoInteresDTO getById(Integer id) {
        InfoPuntoInteresDTO output = null;
        if (puntoInteresRepository.existsById(id)) {
            output = convertPuntoInteresToInfoDTO(puntoInteresRepository.findById(id).get());
        }
        return output;
    }

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