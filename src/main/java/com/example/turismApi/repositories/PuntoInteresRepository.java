package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.PuntoInteres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad PuntoInteres que proporciona operaciones de base de datos relacionadas con puntos de interés.
 */
@Repository
public interface PuntoInteresRepository extends JpaRepository<PuntoInteres, Integer> {
    /**
     * Obtiene todos los puntos de interés asociados a una provincia específica.
     *
     * @param id Identificador único de la provincia.
     * @return Lista de puntos de interés asociados a la provincia.
     */
    @Query("SELECT pi FROM PuntoInteres pi WHERE pi.provincia.id=:id")
    List<PuntoInteres> getAllById_provincia(@Param("id")Integer id);
}
