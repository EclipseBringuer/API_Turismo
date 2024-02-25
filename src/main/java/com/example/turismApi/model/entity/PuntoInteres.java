package com.example.turismApi.model.entity;

import jakarta.persistence.*;

import java.util.List;

/**
 * Entidad que representa un punto de interés turístico en el sistema.
 * Esta clase se utiliza para mapear la tabla "punto_interes" en la base de datos.
 */
@Entity
@Table(name = "punto_interes")
public class PuntoInteres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "tipo_turismo")
    private String tipoTurismo;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "longitud")
    private Double longitud;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "poi")
    private List<Comentario> comentarios;
    @OneToMany(mappedBy = "poi")
    private List<Valoracion> valoraciones;
    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    /**
     * Constructor por defecto de la clase PuntoInteres.
     */
    public PuntoInteres() {

    }

    /**
     * Constructor que inicializa los atributos de la clase PuntoInteres.
     *
     * @param id            Identificador único del punto de interés.
     * @param name          Nombre del punto de interés.
     * @param tipoTurismo   Tipo de turismo asociado al punto de interés.
     * @param precio        Precio del punto de interés.
     * @param latitud       Latitud del punto de interés.
     * @param longitud      Longitud del punto de interés.
     * @param descripcion   Descripción del punto de interés.
     * @param comentarios   Lista de comentarios asociados al punto de interés.
     * @param valoraciones  Lista de valoraciones asociadas al punto de interés.
     * @param provincia     Provincia a la que pertenece el punto de interés.
     */
    public PuntoInteres(Integer id, String name, String tipoTurismo, Double precio, Double latitud, Double longitud, String descripcion, List<Comentario> comentarios, List<Valoracion> valoraciones, Provincia provincia) {
        this.id = id;
        this.name = name;
        this.tipoTurismo = tipoTurismo;
        this.precio = precio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        this.comentarios = comentarios;
        this.valoraciones = valoraciones;
        this.provincia = provincia;
    }
    /**
     * Obtiene el identificador único del punto de interés.
     *
     * @return Identificador único del punto de interés.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del punto de interés.
     *
     * @param id Identificador único del punto de interés.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene la descripción del punto de interés.
     *
     * @return Descripción del punto de interés.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del punto de interés.
     *
     * @param descripcion Descripción del punto de interés.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre del punto de interés.
     *
     * @return Nombre del punto de interés.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del punto de interés.
     *
     * @param name Nombre del punto de interés.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el tipo de turismo asociado al punto de interés.
     *
     * @return Tipo de turismo del punto de interés.
     */
    public String getTipoTurismo() {
        return tipoTurismo;
    }

    /**
     * Establece el tipo de turismo asociado al punto de interés.
     *
     * @param tipoTurismo Tipo de turismo del punto de interés.
     */
    public void setTipoTurismo(String tipoTurismo) {
        this.tipoTurismo = tipoTurismo;
    }

    /**
     * Obtiene el precio del punto de interés.
     *
     * @return Precio del punto de interés.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del punto de interés.
     *
     * @param precio Precio del punto de interés.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la latitud del punto de interés.
     *
     * @return Latitud del punto de interés.
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * Establece la latitud del punto de interés.
     *
     * @param latitud Latitud del punto de interés.
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * Obtiene la longitud del punto de interés.
     *
     * @return Longitud del punto de interés.
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * Establece la longitud del punto de interés.
     *
     * @param longitud Longitud del punto de interés.
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    /**
     * Obtiene la provincia a la que pertenece el punto de interés.
     *
     * @return Provincia a la que pertenece el punto de interés.
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Establece la provincia a la que pertenece el punto de interés.
     *
     * @param provincia Provincia a la que pertenece el punto de interés.
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    /**
     * Obtiene la lista de comentarios asociados al punto de interés.
     *
     * @return Lista de comentarios asociados al punto de interés.
     */
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Establece la lista de comentarios asociados al punto de interés.
     *
     * @param comentarios Lista de comentarios asociados al punto de interés.
     */
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Obtiene la lista de valoraciones asociadas al punto de interés.
     *
     * @return Lista de valoraciones asociadas al punto de interés.
     */
    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    /**
     * Establece la lista de valoraciones asociadas al punto de interés.
     *
     * @param valoraciones Lista de valoraciones asociadas al punto de interés.
     */
    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    /**
     * Representación en cadena de texto del objeto PuntoInteres.
     *
     * @return Cadena que representa el objeto PuntoInteres.
     */
    @Override
    public String toString() {
        return "PuntoInteres{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tipoTurismo='" + tipoTurismo + '\'' +
                ", precio=" + precio +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", descripcion='" + descripcion + '\'' +
                ", comentarios=" + comentarios +
                ", valoraciones=" + valoraciones +
                ", provincia=" + provincia.getName() +
                '}';
    }
}