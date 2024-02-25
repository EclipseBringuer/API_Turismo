package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.comentario.InfoComentarioDTO;
import com.example.turismApi.model.dto.comentario.NewComentarioDTO;
import com.example.turismApi.services.ComentarioService;
import com.example.turismApi.services.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para manejar operaciones relacionadas con comentarios.
 */
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private SecurityService securityService;

    /**
     * Recupera todos los comentarios.
     *
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con una lista de InfoComentarioDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener todos los comentarios")
    @GetMapping("/getAll")
    public ResponseEntity<List<InfoComentarioDTO>> getAll(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(comentarioService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Recupera todos los comentarios por ID de usuario.
     *
     * @param token Token de autenticación del usuario.
     * @param id    ID de usuario.
     * @return ResponseEntity con una lista de InfoComentarioDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener todos los comentarios por ID de usuario")
    @GetMapping("/getByUser/{id}")
    public ResponseEntity<List<InfoComentarioDTO>> getAllByUserId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(comentarioService.getAllByUser(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Recupera todos los comentarios por ID de Punto de Interés (POI).
     *
     * @param token Token de autenticación del usuario.
     * @param id    ID de POI.
     * @return ResponseEntity con una lista de InfoComentarioDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener todos los comentarios por ID de POI")
    @GetMapping("/getByPoi/{id}")
    public ResponseEntity<List<InfoComentarioDTO>> getAllByPoiId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(comentarioService.getAllByPoi(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Crea un nuevo comentario.
     *
     * @param comentarioDTO Nuevo objeto NewComentarioDTO que contiene los detalles del comentario.
     * @param token          Token de autenticación del usuario.
     * @return ResponseEntity con InfoComentarioDTO en caso de éxito, o estado UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Crear un nuevo comentario")
    @PostMapping("/new")
    public ResponseEntity<InfoComentarioDTO> createNewComentario(@Valid @RequestBody NewComentarioDTO comentarioDTO, @RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = comentarioService.createNewComentario(token, comentarioDTO);
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
     * Elimina un comentario por ID.
     *
     * @param id    ID del comentario.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con un mensaje de éxito, o estado BAD_REQUEST con un mensaje de error.
     */
    @Operation(summary = "Eliminar comentario por ID")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteComentarioById(@PathVariable Integer id, @RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = comentarioService.deleteComentario(id, token);
            if (output != null) {
                return new ResponseEntity<>(output, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El comentario no pertenece a este usuario", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Token no válido", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Recupera un comentario por ID.
     *
     * @param id    ID del comentario.
     * @param token Token de autenticación del usuario.
     * @return ResponseEntity con InfoComentarioDTO en caso de éxito, NOT_FOUND si el comentario no se encuentra, o UNAUTHORIZED si el token no es válido.
     */
    @Operation(summary = "Obtener comentario por ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<InfoComentarioDTO> getComentarioById(@PathVariable Integer id, @RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            var output = comentarioService.getComentarioById(id);
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