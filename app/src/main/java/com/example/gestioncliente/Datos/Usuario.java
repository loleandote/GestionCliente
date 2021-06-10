package com.example.gestioncliente.Datos;



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
    @SerializedName("dia_alta")
    @Expose
    private int dia_alta;
    @SerializedName("mes_alta")
    @Expose
    private int mes_alta;
    @SerializedName("anyo_alta")
    @Expose
    private int anyo_alta;
    @SerializedName("creditos")
    @Expose
    private int creditos;
    @SerializedName("es_cliente")
    @Expose
    private boolean es_cliente;
    @SerializedName("penalizado")
    @Expose
    private boolean penalizado;
    @SerializedName("dia_fin_pena")
    @Expose
    private int dia_fin_pena;
    @SerializedName("mes_fin_pena")
    @Expose
    private int mes_fin_pena;
    @SerializedName("anyo_fin_pena")
    @Expose
    private int anyo_fin_pena;
    @SerializedName("codigo_rol")
    @Expose
    private int codigo_rol;
    @SerializedName("nombre_rol")
    @Expose
    private String nombre_rol;

    public Usuario() {
    }

    public Usuario(int id, String nombre_usuario, String contraseña_usuario, String correo_usuario, int dia_alta, int mes_alta, int anyo_alta, int creditos, boolean es_cliente, boolean penalizado, int dia_fin_pena, int mes_fin_pena, int anyo_fin_pena, int codigo_rol, String nombre_rol) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.contraseña_usuario = contraseña_usuario;
        this.correo_usuario = correo_usuario;
        this.dia_alta = dia_alta;
        this.mes_alta = mes_alta;
        this.anyo_alta = anyo_alta;
        this.creditos = creditos;
        this.es_cliente = es_cliente;
        this.penalizado = penalizado;
        this.dia_fin_pena = dia_fin_pena;
        this.mes_fin_pena = mes_fin_pena;
        this.anyo_fin_pena = anyo_fin_pena;
        this.codigo_rol = codigo_rol;
        this.nombre_rol = nombre_rol;
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

    public int getDia_alta() {
        return dia_alta;
    }

    public void setDia_alta(int dia_alta) {
        this.dia_alta = dia_alta;
    }

    public int getMes_alta() {
        return mes_alta;
    }

    public void setMes_alta(int mes_alta) {
        this.mes_alta = mes_alta;
    }

    public int getAnyo_alta() {
        return anyo_alta;
    }

    public void setAnyo_alta(int anyo_alta) {
        this.anyo_alta = anyo_alta;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public boolean isPenalizado() {
        return penalizado;
    }

    public void setPenalizado(boolean penalizado) {
        this.penalizado = penalizado;
    }

    public int getDia_fin_pena() {
        return dia_fin_pena;
    }

    public void setDia_fin_pena(int dia_fin_pena) {
        this.dia_fin_pena = dia_fin_pena;
    }

    public int getMes_fin_pena() {
        return mes_fin_pena;
    }

    public void setMes_fin_pena(int mes_fin_pena) {
        this.mes_fin_pena = mes_fin_pena;
    }

    public int getAnyo_fin_pena() {
        return anyo_fin_pena;
    }

    public void setAnyo_fin_pena(int anyo_fin_pena) {
        this.anyo_fin_pena = anyo_fin_pena;
    }

    public int getCodigo_rol() {
        return codigo_rol;
    }

    public void setCodigo_rol(int codigo_rol) {
        this.codigo_rol = codigo_rol;
    }

    public boolean isEs_cliente() {
        return es_cliente;
    }

    public void setEs_cliente(boolean es_cliente) {
        this.es_cliente = es_cliente;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }
}
