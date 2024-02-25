package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.comunidad.InfoComunidadDTO;
import com.example.turismApi.services.ComunidadService;
import com.example.turismApi.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunidades")
public class ComunidadController {
    @Autowired
    private ComunidadService comunidadService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("")
    public ResponseEntity<List<InfoComunidadDTO>> getAllComunidades(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(comunidadService.getAllComunidades(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

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
