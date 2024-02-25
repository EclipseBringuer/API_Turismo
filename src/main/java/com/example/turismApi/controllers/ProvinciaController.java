package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.provincia.InfoProvinciaDTO;
import com.example.turismApi.model.entity.Provincia;
import com.example.turismApi.services.ProvinciaService;
import com.example.turismApi.services.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar operaciones relacionadas con provincias.
 */
@RestController
@RequestMapping("/provincias")
public class ProvinciaController {
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private SecurityService securityService;

    /**
     * Obtiene todas las provincias en base al ID de una comunidad.
     *
     * @param token Token de autenticación del usuario.
     * @param id    ID de la comunidad.
     * @return ResponseEntity con una lista de InfoProvinciaDTO en caso de éxito, NOT_FOUND si no hay provincias para la comunidad, o UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener todas las provincias en base al ID de una comunidad")
    @GetMapping("/getByComunidad/{id}")
    public ResponseEntity<List<InfoProvinciaDTO>> getAllByComunidadId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            var output = provinciaService.getAllProvinciasByComunidadId(id);
            if (output.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(output, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Obtiene una provincia por su ID.
     *
     * @param token Token de autenticación del usuario.
     * @param id    ID de la provincia.
     * @return ResponseEntity con InfoProvinciaDTO en caso de éxito, NOT_FOUND si la provincia no se encuentra, o UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener provincia por ID")
    @GetMapping("/getById/{id}")
    public ResponseEntity<InfoProvinciaDTO> getById(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            var output = provinciaService.getById(id);
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