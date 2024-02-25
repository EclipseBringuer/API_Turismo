package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> getAllByUsuario_Id(Integer id);
    List<Comentario> getAllByPoi_Id(Integer id);
}
