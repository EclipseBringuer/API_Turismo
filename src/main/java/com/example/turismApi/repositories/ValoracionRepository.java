package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
    List<Valoracion> getAllByUsuario_Id(Integer id);
    List<Valoracion> getAllByPoi_Id(Integer id);
}
