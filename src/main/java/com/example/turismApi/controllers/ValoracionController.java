package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.valoracion.InfoValoracionDTO;
import com.example.turismApi.model.dto.valoracion.NewValoracionDTO;
import com.example.turismApi.services.SecurityService;
import com.example.turismApi.services.ValoracionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {
    @Autowired
    private ValoracionService valoracionService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/getAll")
    public ResponseEntity<List<InfoValoracionDTO>> getAll(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(valoracionService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getByUser/{id}")
    public ResponseEntity<List<InfoValoracionDTO>> getAllByUserId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(valoracionService.getAllByUser(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getByPoi/{id}")
    public ResponseEntity<List<InfoValoracionDTO>> getAllByPoiId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(valoracionService.getAllByPoi(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

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