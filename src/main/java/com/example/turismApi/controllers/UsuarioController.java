package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.user.InfoUsuarioDTO;
import com.example.turismApi.model.dto.user.LogInUserDTO;
import com.example.turismApi.model.dto.user.SignUpUserDTO;
import com.example.turismApi.model.entity.Usuario;
import com.example.turismApi.services.SecurityService;
import com.example.turismApi.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Controlador para manejar operaciones relacionadas con usuarios.
 */
@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SecurityService securityService;

    /**
     * Obtiene todos los usuarios.
     *
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con una lista de InfoUsuarioDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtiene todos los usuarios")
    @GetMapping("/getAll")
    public ResponseEntity<List<InfoUsuarioDTO>> getALl(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = usuarioService.getAllUsuarioDTO();
            return new ResponseEntity<>(output, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Registra un nuevo usuario.
     *
     * @param u Objeto SignUpUserDTO que contiene los detalles del usuario a registrar.
     * @return ResponseEntity con el usuario registrado en caso de éxito, o estado CREATED si el usuario se registró correctamente.
     */
    @Operation(summary = "Registra un nuevo usuario")
    @PostMapping("/signUp")
    public ResponseEntity<Usuario> signUp(@Valid @RequestBody SignUpUserDTO u) {
        return new ResponseEntity<>(usuarioService.signUp(u, securityService.generateToken()), HttpStatus.CREATED);
    }

    /**
     * Inicia sesión de un usuario.
     *
     * @param logInUserDTO Objeto LogInUserDTO que contiene las credenciales para iniciar sesión.
     * @return ResponseEntity con un mensaje de éxito y el token de acceso en caso de éxito, o estado UNAUTHORIZED si las credenciales son incorrectas.
     */
    @Operation(summary = "Inicia sesión de un usuario.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LogInUserDTO logInUserDTO) {
        var token = usuarioService.logIn(logInUserDTO);
        if (!Objects.equals(token, "Error al iniciar sesión")) {
            return new ResponseEntity<>("Cuenta comprobada, tu token de acceso es: " + token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(token, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id    ID del usuario.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con InfoUsuarioDTO en caso de éxito, NOT_FOUND si el usuario no se encuentra, o UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtiene un usuario por su ID.")
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

    /**
     * Elimina un usuario por su token de autenticación.
     *
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con un mensaje de éxito en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Elimina un usuario por su token de autenticación.")
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
