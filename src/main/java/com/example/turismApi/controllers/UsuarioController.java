package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.InfoUsuarioDTO;
import com.example.turismApi.model.dto.SignUpUserDTO;
import com.example.turismApi.model.entity.Usuario;
import com.example.turismApi.services.SecurityService;
import com.example.turismApi.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/getAll")
    public ResponseEntity<List<InfoUsuarioDTO>> getALl(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = usuarioService.getAllUsuarioDTO();
            return new ResponseEntity<>(output, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<Usuario> signUp(@Valid @RequestBody SignUpUserDTO u){
        return new ResponseEntity<>(usuarioService.signUp(u,securityService.generateToken()),HttpStatus.OK);
    }
}
