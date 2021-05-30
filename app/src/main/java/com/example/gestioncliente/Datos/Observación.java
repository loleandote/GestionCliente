package com.example.gestioncliente.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Observación {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id_usuario")
    @Expose
    private int id_usuario;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("contenido")
    @Expose
    private String contenido;

    public Observación() {
    }

    public Observación(int id, int id_usuario, String titulo, String contenido) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
