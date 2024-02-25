package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario getByToken(String token);
    @Query("SELECT u.id FROM Usuario u ORDER BY u.id DESC LIMIT 1")
    Integer getMaxId();

    Usuario getUsuarioByGmailAndPass(String gmail, String pass);
}
