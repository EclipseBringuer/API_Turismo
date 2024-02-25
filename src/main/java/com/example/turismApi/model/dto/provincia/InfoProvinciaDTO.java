package com.example.turismApi.model.dto.provincia;

import java.util.List;

public record InfoProvinciaDTO(Integer id, String name, Double latitud, Double longitud, List<InfoPuntoInteresEnProvinciaDTO> poi) {
}
