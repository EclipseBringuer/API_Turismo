package com.example.turismApi.repositories;

import com.example.turismApi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Usuario que proporciona operaciones de base de datos relacionadas con usuarios.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    /**
     * Obtiene un usuario por su token.
     *
     * @param token Token único del usuario.
     * @return Usuario asociado al token proporcionado.
     */
    Usuario getByToken(String token);

    /**
     * Obtiene el ID máximo de los usuarios en la base de datos.
     *
     * @return ID máximo de los usuarios.
     */
    @Query("SELECT u.id FROM Usuario u ORDER BY u.id DESC LIMIT 1")
    Integer getMaxId();

    /**
     * Obtiene un usuario por su dirección de correo electrónico y contraseña.
     *
     * @param gmail Dirección de correo electrónico del usuario.
     * @param pass Contraseña del usuario.
     * @return Usuario asociado a la dirección de correo electrónico y contraseña proporcionadas.
     */
    Usuario getUsuarioByGmailAndPass(String gmail, String pass);

    /**
     * Elimina un usuario por su token.
     *
     * @param token Token único del usuario a ser eliminado.
     */
    void deleteByToken(String token);
}
