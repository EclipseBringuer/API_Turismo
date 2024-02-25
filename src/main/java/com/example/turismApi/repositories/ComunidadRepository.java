package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Comunidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Comunidad que proporciona operaciones de base de datos relacionadas con comunidades.
 */
@Repository
public interface ComunidadRepository extends JpaRepository<Comunidad, Integer> {
}
