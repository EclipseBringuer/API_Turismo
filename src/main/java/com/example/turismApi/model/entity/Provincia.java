package com.example.turismApi.model.entity;

import jakarta.persistence.*;
import java.util.List;

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

    public Provincia() {

    }

    public Provincia(Integer id, String name, Double latitud, Double longitud, List<PuntoInteres> puntosInteres, Integer id_comunidad) {
        this.id = id;
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
        this.puntosInteres = puntosInteres;
        this.id_comunidad = id_comunidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PuntoInteres> getPuntosInteres() {
        return puntosInteres;
    }

    public void setPuntosInteres(List<PuntoInteres> puntosInteres) {
        this.puntosInteres = puntosInteres;
    }

    public Integer getId_comunidad() {
        return id_comunidad;
    }

    public void setId_comunidad(Integer id_comunidad) {
        this.id_comunidad = id_comunidad;
    }

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