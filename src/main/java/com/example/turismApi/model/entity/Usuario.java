package com.example.turismApi.model.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Clase que representa la entidad Usuario en la base de datos.
 */
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "gmail")
    private String gmail;
    @Column(name = "contraseña")
    private String pass;
    @Column(name = "token")
    private String token;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Valoracion> valoraciones;

    /**
     * Constructor por defecto de la clase Usuario.
     */
    public Usuario() {

    }

    /**
     * Constructor de la clase Usuario con parámetros.
     *
     * @param id           Identificador único del usuario.
     * @param name         Nombre del usuario.
     * @param gmail        Correo electrónico del usuario.
     * @param pass         Contraseña del usuario.
     * @param token        Token de autenticación del usuario.
     * @param comentarios  Lista de comentarios realizados por el usuario.
     * @param valoraciones Lista de valoraciones realizadas por el usuario.
     */
    public Usuario(Integer id, String name, String gmail, String pass, String token, List<Comentario> comentarios, List<Valoracion> valoraciones) {
        this.id = id;
        this.name = name;
        this.gmail = gmail;
        this.pass = pass;
        this.token = token;
        this.comentarios = comentarios;
        this.valoraciones = valoraciones;
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return Identificador único del usuario.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id Identificador único del usuario.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico del usuario.
     */
    public String getGmail() {
        return gmail;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param gmail Correo electrónico del usuario.
     */
    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param pass Contraseña del usuario.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Obtiene el token de autenticación del usuario.
     *
     * @return Token de autenticación del usuario.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el token de autenticación del usuario.
     *
     * @param token Token de autenticación del usuario.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name Nombre del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la lista de comentarios realizados por el usuario.
     *
     * @return Lista de comentarios realizados por el usuario.
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Establece la lista de comentarios realizados por el usuario.
     *
     * @param comentarios Lista de comentarios realizados por el usuario.
     */
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Obtiene la lista de valoraciones realizadas por el usuario.
     *
     * @return Lista de valoraciones realizadas por el usuario.
     */
    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    /**
     * Establece la lista de valoraciones realizadas por el usuario.
     *
     * @param valoraciones Lista de valoraciones realizadas por el usuario.
     */
    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    /**
     * Representación en cadena de texto del objeto Usuario.
     *
     * @return Cadena que representa el objeto Usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gmail='" + gmail + '\'' +
                ", pass='" + pass + '\'' +
                ", token='" + token + '\'' +
                ", comentarios=" + comentarios +
                ", valoraciones=" + valoraciones +
                '}';
    }
}