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

@RestController
@RequestMapping("/provincias")
public class ProvinciaController {
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/getByComunidad/{id}")
    @Operation(summary = "Obtiene todas las provincias en base al id de una comunidad")
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