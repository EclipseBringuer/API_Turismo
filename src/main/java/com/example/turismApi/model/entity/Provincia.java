package com.example.turismApi.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad que representa una provincia en el sistema.
 * Esta clase se utiliza para mapear la tabla "provincia" en la base de datos.
 */
@Entity
@Table(name = "provincia")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "longitud")
    private Double longitud;
    @OneToMany
    @JoinColumn(name = "id_provincia")
    private List<PuntoInteres> puntosInteres;
    @Column(name = "id_comunidad")
    private Integer id_comunidad;

    /**
     * Constructor por defecto de la clase Provincia.
     */
    public Provincia() {

    }

    /**
     * Constructor que inicializa los atributos de la clase Provincia.
     *
     * @param id             Identificador único de la provincia.
     * @param name           Nombre de la provincia.
     * @param latitud        Latitud de la provincia.
     * @param longitud       Longitud de la provincia.
     * @param puntosInteres  Lista de puntos de interés asociados a la provincia.
     * @param id_comunidad   Identificador de la comunidad a la que pertenece la provincia.
     */
    public Provincia(Integer id, String name, Double latitud, Double longitud, List<PuntoInteres> puntosInteres, Integer id_comunidad) {
        this.id = id;
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
        this.puntosInteres = puntosInteres;
        this.id_comunidad = id_comunidad;
    }

    /**
     * Obtiene el identificador único de la provincia.
     *
     * @return Identificador único de la provincia.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la provincia.
     *
     * @param id Identificador único de la provincia.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la provincia.
     *
     * @return Nombre de la provincia.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la provincia.
     *
     * @param name Nombre de la provincia.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la latitud de la provincia.
     *
     * @return Latitud de la provincia.
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * Establece la latitud de la provincia.
     *
     * @param latitud Latitud de la provincia.
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * Obtiene la longitud de la provincia.
     *
     * @return Longitud de la provincia.
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * Establece la longitud de la provincia.
     *
     * @param longitud Longitud de la provincia.
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    /**
     * Obtiene la lista de puntos de interés asociados a la provincia.
     *
     * @return Lista de puntos de interés asociados a la provincia.
     */
    public List<PuntoInteres> getPuntosInteres() {
        return puntosInteres;
    }

    /**
     * Establece la lista de puntos de interés asociados a la provincia.
     *
     * @param puntosInteres Lista de puntos de interés asociados a la provincia.
     */
    public void setPuntosInteres(List<PuntoInteres> puntosInteres) {
        this.puntosInteres = puntosInteres;
    }

    /**
     * Obtiene el identificador de la comunidad a la que pertenece la provincia.
     *
     * @return Identificador de la comunidad.
     */
    public Integer getId_comunidad() {
        return id_comunidad;
    }

    /**
     * Establece el identificador de la comunidad a la que pertenece la provincia.
     *
     * @param id_comunidad Identificador de la comunidad.
     */
    public void setId_comunidad(Integer id_comunidad) {
        this.id_comunidad = id_comunidad;
    }

    /**
     * Genera una representación en cadena del objeto Provincia.
     *
     * @return Cadena que representa el objeto Provincia.
     */
    @Override
    public String toString() {
        return "Provincia{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", puntosInteres=" + puntosInteres +
                ", id_comunidad=" + id_comunidad +
                '}';
    }
}