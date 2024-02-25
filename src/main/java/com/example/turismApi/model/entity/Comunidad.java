package com.example.turismApi.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad que representa una comunidad en el sistema.
 * Esta clase se utiliza para mapear la tabla "comunidad" en la base de datos.
 */
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

    /**
     * Constructor por defecto de la clase Comunidad.
     */
    public Comunidad() {

    }

    /**
     * Constructor que inicializa los atributos de la clase Comunidad.
     *
     * @param id         Identificador único de la comunidad.
     * @param name       Nombre de la comunidad.
     * @param provincias Lista de provincias asociadas a la comunidad.
     */
    public Comunidad(Integer id, String name, List<Provincia> provincias) {
        this.id = id;
        this.name = name;
        this.provincias = provincias;
    }

    /**
     * Obtiene el identificador único de la comunidad.
     *
     * @return Identificador único de la comunidad.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la comunidad.
     *
     * @param id Identificador único de la comunidad.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la comunidad.
     *
     * @return Nombre de la comunidad.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la comunidad.
     *
     * @param name Nombre de la comunidad.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la lista de provincias asociadas a la comunidad.
     *
     * @return Lista de provincias asociadas a la comunidad.
     */
    public List<Provincia> getProvincias() {
        return provincias;
    }

    /**
     * Establece la lista de provincias asociadas a la comunidad.
     *
     * @param provincias Lista de provincias asociadas a la comunidad.
     */
    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    /**
     * Genera una representación en cadena del objeto Comunidad.
     *
     * @return Cadena que representa el objeto Comunidad.
     */
    @Override
    public String toString() {
        return "Comunidad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provincias=" + provincias +
                '}';
    }
}