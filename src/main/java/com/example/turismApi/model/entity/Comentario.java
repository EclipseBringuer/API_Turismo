package com.example.turismApi.model.entity;

import jakarta.persistence.*;

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

    public Comentario() {

    }

    public Comentario(Integer id, String contenido, Usuario usuario, PuntoInteres poi) {
        this.id = id;
        this.contenido = contenido;
        this.usuario = usuario;
        this.poi = poi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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
        return "Comentario{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", usuario=" + usuario.getName() +
                ", poi=" + poi.getName() +
                '}';
    }
}