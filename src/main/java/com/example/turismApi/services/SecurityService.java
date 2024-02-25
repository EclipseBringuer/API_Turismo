package com.example.turismApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private UsuarioService usuarioService;

    public boolean validateToken(String token) {
        return usuarioService.getByToken(token) != null;
    }

    public String generateToken() {
        int tokenNumber = usuarioService.getMaxId() + 1;
        return "t0ken" + tokenNumber;
    }
}
