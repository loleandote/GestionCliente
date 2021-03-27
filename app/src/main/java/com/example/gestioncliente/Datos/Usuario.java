package com.example.gestioncliente.Datos;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Usuario {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nombre_usuario")
    @Expose
    private String nombre_usuario;
    @SerializedName("contraseña_usuario")
    @Expose
    private String contraseña_usuario;
    @SerializedName("correo_usuario")
    @Expose
    private String correo_usuario;
    @SerializedName("fecha_alta")
    @Expose
    private String fecha_alta;
    @SerializedName("creditos")
    @Expose
    private int creditos;
    @SerializedName("observaciones")
    @Expose
    private ArrayList<String> observaciones;
    @SerializedName("penalizado")
    @Expose
    private boolean penalizado;
    @SerializedName("fecha_fin_pena")
    @Expose
    @Nullable
    private String fecha_fin_pena;
    @SerializedName("codigo_rol")
    @Expose
    private int codigo_rol;

    public Usuario() {
    }

    public Usuario(int id, String nombre_usuario, String contraseña_usuario, String correo_usuario, String fecha_alta, int creditos, ArrayList<String> observaciones, boolean penalizado, @Nullable String fecha_fin_pena, int codigo_rol) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.contraseña_usuario = contraseña_usuario;
        this.correo_usuario = correo_usuario;
        this.fecha_alta = fecha_alta;
        this.creditos = creditos;
        this.observaciones = observaciones;
        this.penalizado = penalizado;
        this.fecha_fin_pena = fecha_fin_pena;
        this.codigo_rol = codigo_rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña_usuario() {
        return contraseña_usuario;
    }

    public void setContraseña_usuario(String contraseña_usuario) {
        this.contraseña_usuario = contraseña_usuario;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public ArrayList<String> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(ArrayList<String> observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isPenalizado() {
        return penalizado;
    }

    public void setPenalizado(boolean penalizado) {
        this.penalizado = penalizado;
    }

    @Nullable
    public String getFecha_fin_pena() {
        return fecha_fin_pena;
    }

    public void setFecha_fin_pena(@Nullable String fecha_fin_pena) {
        this.fecha_fin_pena = fecha_fin_pena;
    }

    public int getCodigo_rol() {
        return codigo_rol;
    }

    public void setCodigo_rol(int codigo_rol) {
        this.codigo_rol = codigo_rol;
    }
}
