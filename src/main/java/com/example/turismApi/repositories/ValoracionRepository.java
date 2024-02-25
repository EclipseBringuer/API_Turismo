package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Valoracion que proporciona operaciones de base de datos relacionadas con valoraciones.
 */
@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {

    /**
     * Obtiene todas las valoraciones realizadas por un usuario específico.
     *
     * @param id ID del usuario.
     * @return Lista de valoraciones realizadas por el usuario.
     */
    List<Valoracion> getAllByUsuario_Id(Integer id);

    /**
     * Obtiene todas las valoraciones asociadas a un Punto de Interés (POI) específico.
     *
     * @param id ID del Punto de Interés (POI).
     * @return Lista de valoraciones asociadas al POI.
     */
    List<Valoracion> getAllByPoi_Id(Integer id);
}
