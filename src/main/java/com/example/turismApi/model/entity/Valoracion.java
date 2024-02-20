package com.example.turismApi.model.entity;

import jakarta.persistence.*;

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

    public Valoracion() {

    }

    public Valoracion(Integer id, Integer puntuacion, Usuario usuario, PuntoInteres poi) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.usuario = usuario;
        this.poi = poi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PuntoInteres getPoi() {
        return poi;
    }

    public void setPoi(PuntoInteres poi) {
        this.poi = poi;
    }

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