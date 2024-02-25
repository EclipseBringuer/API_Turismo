package com.example.turismApi.controllers;

import com.example.turismApi.model.dto.puntoInteres.InfoPuntoInteresDTO;
import com.example.turismApi.services.PuntoInteresService;
import com.example.turismApi.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntosInteres")
public class PuntoInteresController {
    @Autowired
    private PuntoInteresService puntoInteresService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/byProvinciaId/{id}")
    public ResponseEntity<List<InfoPuntoInteresDTO>> getAllByComunidadId(@RequestParam("token") String token, @PathVariable Integer id) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(puntoInteresService.getAllByProvinciaId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<InfoPuntoInteresDTO>> getAll(@RequestParam("token") String token) {
        if (securityService.validateToken(token)) {
            return new ResponseEntity<>(puntoInteresService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

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