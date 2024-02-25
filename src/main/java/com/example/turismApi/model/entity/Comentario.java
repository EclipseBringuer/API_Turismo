package com.example.turismApi.model.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa un comentario en el sistema.
 * Esta clase se utiliza para mapear la tabla "comentario" en la base de datos.
 */
@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "contenido")
    private String contenido;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "poi_id")
    private PuntoInteres poi;

    /**
     * Constructor por defecto de la clase Comentario.
     */
    public Comentario() {

    }

    /**
     * Constructor que inicializa los atributos de la clase Comentario.
     *
     * @param id        Identificador único del comentario.
     * @param contenido Contenido del comentario.
     * @param usuario   Usuario que realiza el comentario.
     * @param poi       Punto de interés al que se refiere el comentario.
     */
    public Comentario(Integer id, String contenido, Usuario usuario, PuntoInteres poi) {
        this.id = id;
        this.contenido = contenido;
        this.usuario = usuario;
        this.poi = poi;
    }

    /**
     * Obtiene el identificador único del comentario.
     *
     * @return Identificador único del comentario.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del comentario.
     *
     * @param id Identificador único del comentario.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el contenido del comentario.
     *
     * @return Contenido del comentario.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido del comentario.
     *
     * @param contenido Contenido del comentario.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene el usuario que realizó el comentario.
     *
     * @return Usuario que realizó el comentario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realizó el comentario.
     *
     * @param usuario Usuario que realizó el comentario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el punto de interés al que se refiere el comentario.
     *
     * @return Punto de interés al que se refiere el comentario.
     */
    public PuntoInteres getPoi() {
        return poi;
    }

    /**
     * Establece el punto de interés al que se refiere el comentario.
     *
     * @param poi Punto de interés al que se refiere el comentario.
     */
    public void setPoi(PuntoInteres poi) {
        this.poi = poi;
    }

    /**
     * Genera una representación en cadena del objeto Comentario.
     *
     * @return Cadena que representa el objeto Comentario.
     */
    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", usuario=" + usuario.getName() +
                ", poi=" + poi.getName() +
                '}';
    }
}