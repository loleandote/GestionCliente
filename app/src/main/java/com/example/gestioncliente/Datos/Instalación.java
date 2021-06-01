package com.example.gestioncliente.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Instalación {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tipo")
    @Expose
    private int tipo;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("precio_hora")
    @Expose
    private int precio_hora;
    @SerializedName("tiempo_min")
    @Expose
    private int tiempo_min;
    @SerializedName("tiempo_max")
    @Expose
    private int tiempo_max;
    @SerializedName("imagenes")
    @Expose
    private ArrayList<String> imagenes;
    @SerializedName("horario")
    @Expose
    private ArrayList<Integer> horario;

    public Instalación() {
    }

    public Instalación(int id, int tipo, String nombre, int precio_hora, int tiempo_min, int tiempo_max, ArrayList<String> imagenes, ArrayList<Integer> horario) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio_hora = precio_hora;
        this.tiempo_min = tiempo_min;
        this.tiempo_max = tiempo_max;
        this.imagenes = imagenes;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio_hora() {
        return precio_hora;
    }

    public void setPrecio_hora(int precio_hora) {
        this.precio_hora = precio_hora;
    }

    public int getTiempo_min() {
        return tiempo_min;
    }

    public void setTiempo_min(int tiempo_min) {
        this.tiempo_min = tiempo_min;
    }

    public int getTiempo_max() {
        return tiempo_max;
    }

    public void setTiempo_max(int tiempo_max) {
        this.tiempo_max = tiempo_max;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }

    public ArrayList<Integer> getHorario() {
        return horario;
    }

    public void setHorario(ArrayList<Integer> horario) {
        this.horario = horario;
    }
}
