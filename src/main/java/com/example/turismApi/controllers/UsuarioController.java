package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.user.InfoUsuarioDTO;
import com.example.turismApi.model.dto.user.LogInUserDTO;
import com.example.turismApi.model.dto.user.SignUpUserDTO;
import com.example.turismApi.model.entity.Usuario;
import com.example.turismApi.services.SecurityService;
import com.example.turismApi.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<Usuario> signUp(@Valid @RequestBody SignUpUserDTO u) {
        return new ResponseEntity<>(usuarioService.signUp(u, securityService.generateToken()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LogInUserDTO logInUserDTO) {
        var token = usuarioService.logIn(logInUserDTO);
        if (!Objects.equals(token, "Error al iniciar sesión")) {
            return new ResponseEntity<>("Cuenta comprobada, tu token de acceso es: " + token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(token, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<InfoUsuarioDTO> getById(@PathVariable Integer id, @RequestParam("token") String token){
        if(securityService.validateToken(token)){
            var output = usuarioService.getById(id);
            if (output!=null){
                return new ResponseEntity<>(output, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("token") String token){
        if (securityService.validateToken(token)) {
            usuarioService.deleteByToken(token);
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
