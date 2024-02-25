package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Comentario que proporciona operaciones de base de datos relacionadas con comentarios.
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    /**
     * Obtiene todos los comentarios realizados por un usuario específico.
     *
     * @param id Identificador único del usuario.
     * @return Lista de comentarios realizados por el usuario.
     */
    List<Comentario> getAllByUsuario_Id(Integer id);

    /**
     * Obtiene todos los comentarios asociados a un punto de interés específico.
     *
     * @param id Identificador único del punto de interés.
     * @return Lista de comentarios asociados al punto de interés.
     */
    List<Comentario> getAllByPoi_Id(Integer id);
}
