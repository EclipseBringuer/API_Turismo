package com.example.turismApi.model.dto.valoracion;

import jakarta.validation.constraints.NotBlank;

public record NewValoracionDTO(
        @NotBlank
        Integer id_poi,
        @NotBlank
        Integer valoracion
) {
}
