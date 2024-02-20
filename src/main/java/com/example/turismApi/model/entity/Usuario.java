package com.example.turismApi.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "gmail")
    private String gmail;
    @Column(name = "contraseña")
    private String pass;
    @Column(name = "token")
    private String token;

    public Usuario() {

    }

    public Usuario(Integer id, String name, String gmail, String pass, String token) {
        this.id = id;
        this.name = name;
        this.gmail = gmail;
        this.pass = pass;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gmail='" + gmail + '\'' +
                ", pass='" + pass + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}