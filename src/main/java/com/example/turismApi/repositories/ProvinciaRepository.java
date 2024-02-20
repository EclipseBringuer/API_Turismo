package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    @Query("SELECT p FROM Provincia p WHERE p.id_comunidad=:id")
    List<Provincia> findAllByComunidadId(@Param("id")Integer id);
}
