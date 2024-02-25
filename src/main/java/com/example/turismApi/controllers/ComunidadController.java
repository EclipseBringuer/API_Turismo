package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.comunidad.InfoComunidadDTO;
import com.example.turismApi.services.ComunidadService;
import com.example.turismApi.services.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar operaciones relacionadas con comunidades.
 */
@RestController
@RequestMapping("/comunidades")
public class ComunidadController {
    @Autowired
    private ComunidadService comunidadService;
    @Autowired
    private SecurityService securityService;

    /**
     * Obtiene todas las comunidades.
     *
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con una lista de InfoComunidadDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener todas las comunidades")
    @GetMapping("")
    public ResponseEntity<List<InfoComunidadDTO>> getAllComunidades(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(comunidadService.getAllComunidades(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Obtiene una comunidad por su ID.
     *
     * @param id    ID de la comunidad.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con InfoComunidadDTO en caso de éxito, NOT_FOUND si la comunidad no se encuentra, o UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener comunidad por ID")
    @GetMapping("/{id}")
    public ResponseEntity<InfoComunidadDTO> getById(@PathVariable Integer id, @RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = comunidadService.getById(id);
            if (output != null) {
                return new ResponseEntity<>(output, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
