package com.example.turismApi.model.dto.user;

import jakarta.validation.constraints.NotBlank;

public record SignUpUserDTO(
        @NotBlank(message = "Introduce un nombre válido")
        String name,
        @NotBlank(message = "Introduce un correo válido")
        String gmail,
        @NotBlank(message = "La cuenta debe tener una contraseña")
        String pass) {
}
