package com.example.turismApi.services;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private UsuarioService usuarioService;
    public boolean validateToken(String token) {
        return true;
    }

    public String generateToken() {
        return "";
    }
}
