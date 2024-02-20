package com.example.turismApi.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "comunidad")
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany
    @JoinColumn(name = "id_comunidad")
    private List<Provincia> provincias;

    public Comunidad() {

    }

    public Comunidad(Integer id, String name, List<Provincia> provincias) {
        this.id = id;
        this.name = name;
        this.provincias = provincias;
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

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    @Override
    public String toString() {
        return "Comunidad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provincias=" + provincias +
                '}';
    }
}