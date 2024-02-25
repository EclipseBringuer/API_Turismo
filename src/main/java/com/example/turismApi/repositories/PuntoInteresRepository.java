package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.PuntoInteres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PuntoInteresRepository extends JpaRepository<PuntoInteres, Integer> {
    @Query("SELECT pi FROM PuntoInteres pi WHERE pi.provincia.id=:id")
    List<PuntoInteres> getAllById_provincia(@Param("id")Integer id);

    PuntoInteres getPuntoInteresBy(Integer id);
}
