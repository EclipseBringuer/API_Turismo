package com.example.turismApi.model.dto.comunidad;

import java.util.List;

public record InfoComunidadDTO(Integer id, String name, List<InfoProvinciaEnComunidadDTO> provinvias) {
}
