package com.example.turismApi.services;

import com.example.turismApi.model.dto.InfoUsuarioDTO;
import com.example.turismApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<InfoUsuarioDTO> getAllUsuarioDTO() {
        var output = usuarioRepository.findAll();
        return output.stream()
                .map(u -> new InfoUsuarioDTO(u.getName(), u.getGmail()))
                .collect(Collectors.toList());
    }
}
