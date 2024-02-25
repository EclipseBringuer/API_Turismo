package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.comentario.InfoComentarioDTO;
import com.example.turismApi.model.dto.comentario.NewComentarioDTO;
import com.example.turismApi.services.ComentarioService;
import com.example.turismApi.services.SecurityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/getAll")
    public ResponseEntity<List<InfoComentarioDTO>> getAll(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(comentarioService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getByUser/{id}")
    public ResponseEntity<List<InfoComentarioDTO>> getAllByUserId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(comentarioService.getAllByUser(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getByPoi/{id}")
    public ResponseEntity<List<InfoComentarioDTO>> getAllByPoiId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(comentarioService.getAllByPoi(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

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
}