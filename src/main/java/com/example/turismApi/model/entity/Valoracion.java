package com.example.turismApi.model.entity;

import jakarta.persistence.*;

/**
 * Clase que representa la entidad Valoracion en la base de datos.
 */
@Entity
@Table(name = "valoracion")
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer puntuacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "poi_id")
    private PuntoInteres poi;

    /**
     * Constructor por defecto de la clase Valoracion.
     */
    public Valoracion() {

    }

    /**
     * Constructor de la clase Valoracion con parámetros.
     *
     * @param id          Identificador único de la valoración.
     * @param puntuacion  Puntuación asignada en la valoración.
     * @param usuario     Usuario que realiza la valoración.
     * @param poi         Punto de interés valorado.
     */
    public Valoracion(Integer id, Integer puntuacion, Usuario usuario, PuntoInteres poi) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.usuario = usuario;
        this.poi = poi;
    }

    /**
     * Obtiene el identificador único de la valoración.
     *
     * @return Identificador único de la valoración.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la valoración.
     *
     * @param id Identificador único de la valoración.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene la puntuación asignada en la valoración.
     *
     * @return Puntuación asignada en la valoración.
     */
    public Integer getPuntuacion() {
        return puntuacion;
    }

    /**
     * Establece la puntuación asignada en la valoración.
     *
     * @param puntuacion Puntuación asignada en la valoración.
     */
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Obtiene el usuario que realiza la valoración.
     *
     * @return Usuario que realiza la valoración.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realiza la valoración.
     *
     * @param usuario Usuario que realiza la valoración.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el punto de interés valorado.
     *
     * @return Punto de interés valorado.
     */
    public PuntoInteres getPoi() {
        return poi;
    }

    /**
     * Establece el punto de interés valorado.
     *
     * @param poi Punto de interés valorado.
     */
    public void setPoi(PuntoInteres poi) {
        this.poi = poi;
    }

    /**
     * Representación en cadena de texto del objeto Valoracion.
     *
     * @return Cadena que representa el objeto Valoracion.
     */
    @Override
    public String toString() {
        return "Valoracion{" +
                "id=" + id +
                ", puntuacion=" + puntuacion +
                ", usuario=" + usuario.getName() +
                ", poi=" + poi.getName() +
                '}';
    }
}