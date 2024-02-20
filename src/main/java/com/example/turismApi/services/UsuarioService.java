package com.example.turismApi.services;

import com.example.turismApi.model.dto.UsuarioDTO;
import com.example.turismApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> getAllUsuarioDTO() {
        List<UsuarioDTO> output = new ArrayList<>();
        var users = usuarioRepository.findAll();
        for (var user : users) {
            output.add(new UsuarioDTO(user.getName(), user.getGmail()));
        }
        return output;
    }
}
