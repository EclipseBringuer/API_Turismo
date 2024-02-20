package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.InfoUsuarioDTO;
import com.example.turismApi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getAll")
    public ResponseEntity<List<InfoUsuarioDTO>> getALl() {
        var output = usuarioService.getAllUsuarioDTO();
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
