package com.example.turismApi.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInUserDTO(@NotBlank(message = "Este campo no puede estar vacio")
                           @Email(message = "Introduce una direccion de correo válida")
                           String gmail,
                           @NotBlank(message = "Este campo no puede estar vacio")
                           String pass) {
}
