package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad Provincia que proporciona operaciones de base de datos relacionadas con provincias.
 */
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    /**
     * Obtiene todas las provincias asociadas a una comunidad específica.
     *
     * @param id Identificador único de la comunidad.
     * @return Lista de provincias asociadas a la comunidad.
     */
    @Query("SELECT p FROM Provincia p WHERE p.id_comunidad=:id")
    List<Provincia> findAllByComunidadId(@Param("id")Integer id);
}
