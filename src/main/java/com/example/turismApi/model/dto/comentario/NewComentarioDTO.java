package com.example.turismApi.model.dto.comentario;

import jakarta.validation.constraints.NotBlank;

public record NewComentarioDTO(
        @NotBlank
        Integer id_poi,
        @NotBlank
        String content
) {
}
