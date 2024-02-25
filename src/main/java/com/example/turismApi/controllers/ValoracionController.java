package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.valoracion.InfoValoracionDTO;
import com.example.turismApi.model.dto.valoracion.NewValoracionDTO;
import com.example.turismApi.services.SecurityService;
import com.example.turismApi.services.ValoracionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar operaciones relacionadas con valoraciones.
 */
@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {
    @Autowired
    private ValoracionService valoracionService;
    @Autowired
    private SecurityService securityService;

    /**
     * Obtiene todas las valoraciones.
     *
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con una lista de InfoValoracionDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener todas las valoraciones")
    @GetMapping("/getAll")
    public ResponseEntity<List<InfoValoracionDTO>> getAll(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(valoracionService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Obtiene todas las valoraciones de un usuario.
     *
     * @param token Token de autenticación del usuario.
     * @param id    ID del usuario.
     * @return ResponseEntity con una lista de InfoValoracionDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener valoraciones por ID de usuario")
    @GetMapping("/getByUser/{id}")
    public ResponseEntity<List<InfoValoracionDTO>> getAllByUserId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(valoracionService.getAllByUser(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Obtiene todas las valoraciones de un punto de interés.
     *
     * @param token Token de autenticación del usuario.
     * @param id    ID del punto de interés.
     * @return ResponseEntity con una lista de InfoValoracionDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener valoraciones por ID de punto de interés")
    @GetMapping("/getByPoi/{id}")
    public ResponseEntity<List<InfoValoracionDTO>> getAllByPoiId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(valoracionService.getAllByPoi(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Crea una nueva valoración.
     *
     * @param valoracionDTO Objeto NewValoracionDTO que contiene los detalles de la nueva valoración.
     * @param token         Token de autenticación del usuario.
     * @return ResponseEntity con InfoValoracionDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido o BAD_REQUEST si la operación falla.
     */
    @Operation(summary = "Crear nueva valoración")
    @PostMapping("/new")
    public ResponseEntity<InfoValoracionDTO> createNewValoracion(@Valid @RequestBody NewValoracionDTO valoracionDTO, @RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = valoracionService.createNewValoracion(token, valoracionDTO);
            if (output != null) {
                return new ResponseEntity<>(output, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Elimina una valoración por su ID.
     *
     * @param id    ID de la valoración.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con un mensaje de éxito en caso de éxito, o estado UNAUTHORIZED si el token no es válido o BAD_REQUEST si la operación falla.
     */
    @Operation(summary = "Eliminar valoración por ID")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteValoracionById(@PathVariable Integer id, @RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = valoracionService.deleteValoracion(id, token);
            if (output != null) {
                return new ResponseEntity<>(output, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("La valoración no pertenece a este usuario", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Token no válido", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtiene una valoración por su ID.
     *
     * @param id    ID de la valoración.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con InfoValoracionDTO en caso de éxito, o estado NOT_FOUND si la valoración no se encuentra, o UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener valoración por ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<InfoValoracionDTO> getValoracionById(@PathVariable Integer id, @RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = valoracionService.getValoracionById(id);
            if (output != null) {
                return new ResponseEntity<>(output, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}