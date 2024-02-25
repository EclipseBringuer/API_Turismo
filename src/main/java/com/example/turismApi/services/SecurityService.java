package com.example.turismApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de la gestión de seguridad, validación y generación de tokens.
 */
@Service
public class SecurityService {
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Valida si un token dado es válido.
     *
     * @param token Token a validar.
     * @return true si el token es válido, false de lo contrario.
     */
    public boolean validateToken(String token) {
        return usuarioService.getByToken(token) != null;
    }

    /**
     * Genera un nuevo token único.
     *
     * @return Nuevo token generado.
     */
    public String generateToken() {
        int tokenNumber = usuarioService.getMaxId() + 1;
        return "t0ken" + tokenNumber;
    }
}
