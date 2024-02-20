package com.example.turismApi.controllers;

import com.example.turismApi.model.entity.Provincia;
import com.example.turismApi.services.ProvinciaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/")
public class ProvinciaController {
    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("{id}/provincias")
    @Operation(summary = "Obtiene todas las provincias en base al id de una comunidad")
    public ResponseEntity<List<Provincia>> getAllByComunidadId(@PathVariable Integer id) {
        var resultado = provinciaService.getAllProvinciasByComunidadId(id);

        if (resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
    }
}