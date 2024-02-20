package com.example.turismApi.model.entity;

import jakarta.persistence.*;

import java.util.List;

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

    public PuntoInteres() {

    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipoTurismo() {
        return tipoTurismo;
    }

    public void setTipoTurismo(String tipoTurismo) {
        this.tipoTurismo = tipoTurismo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

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