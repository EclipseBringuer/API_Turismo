package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.puntoInteres.InfoPuntoInteresDTO;
import com.example.turismApi.services.PuntoInteresService;
import com.example.turismApi.services.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar operaciones relacionadas con puntos de interés.
 */
@RestController
@RequestMapping("/puntosInteres")
public class PuntoInteresController {
    @Autowired
    private PuntoInteresService puntoInteresService;
    @Autowired
    private SecurityService securityService;

    /**
     * Obtiene todos los puntos de interés por ID de provincia.
     *
     * @param token Token de autenticación del usuario.
     * @param id    ID de la provincia.
     * @return ResponseEntity con una lista de InfoPuntoInteresDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener todos los puntos de interés por ID de provincia")
    @GetMapping("/byProvinciaId/{id}")
    public ResponseEntity<List<InfoPuntoInteresDTO>> getAllByComunidadId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(puntoInteresService.getAllByProvinciaId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Obtiene todos los puntos de interés.
     *
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con una lista de InfoPuntoInteresDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener todos los puntos de interés")
    @GetMapping("/getAll")
    public ResponseEntity<List<InfoPuntoInteresDTO>> getAll(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(puntoInteresService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Obtiene un punto de interés por su ID.
     *
     * @param token Token de autenticación del usuario.
     * @param id    ID del punto de interés.
     * @return ResponseEntity con InfoPuntoInteresDTO en caso de éxito, NOT_FOUND si el punto de interés no se encuentra, o UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener punto de interés por ID")
    @GetMapping("/getById/{id}")
    public ResponseEntity<InfoPuntoInteresDTO> getById(@RequestParam("token") String token, @PathVariable Integer id){
        if (securityService.validateToken(token)){
            var output = puntoInteresService.getById(id);
            if(output!=null){
                return new ResponseEntity<>(output, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}