package com.example.turismApi.controllers;

import com.example.turismApi.model.entity.PuntoInteres;
import com.example.turismApi.services.PuntoInteresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PuntoInteresController {
    @Autowired
    private PuntoInteresService puntoInteresService;

    @GetMapping("/{id}/puntosInteres")
    public ResponseEntity<List<PuntoInteres>> getAllByComunidadId(@PathVariable Integer id) {
        var resultado = puntoInteresService.getAllByProvinciaId(id);

        if(resultado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
    }
}
