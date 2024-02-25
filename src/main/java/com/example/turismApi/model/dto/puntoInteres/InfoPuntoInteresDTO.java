package com.example.turismApi.model.dto.puntoInteres;

import java.util.List;

public record InfoPuntoInteresDTO(String name,
                                  String turismo,
                                  String descripcion,
                                  Double precio,
                                  Double latitud,
                                  Double longitud,
                                  List<ComentarioToPuntoInteresDTO> comentarios,
                                  List<ValoracionToPuntoInteresDTO> valoraciones) {
}
