package com.example.gestioncliente.Datos;


import androidx.versionedparcelable.ParcelField;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Usuario {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre_usuario")
    @Expose
    private String nombre_usuario;
    @SerializedName("es_cliente")
    @Expose
    private boolean es_cliente;
    @SerializedName("contraseña_usuario")
    @Expose
    private String contraseña_usuario;
    @SerializedName("correo_usuario")
    @Expose
    private String correo_usuario;
    @SerializedName("dia_alta")
    @Expose
    private Integer dia_alta;
    @SerializedName("mes_alta")
    @Expose
    private Integer mes_alta;
    @SerializedName("anyo_alta")
    @Expose
    private Integer anyo_alta;
    @SerializedName("creditos")
    @Expose
    private Integer creditos;
    @SerializedName("penalizado")
    @Expose
    private boolean penalizado;
    @SerializedName("dia_fin_pena")
    @Expose
    private Integer dia_fin_pena;
    @SerializedName("mes_fin_pena")
    @Expose
    private Integer mes_fin_pena;
    @SerializedName("anyo_fin_pena")
    @Expose
    private Integer anyo_fin_pena;
    @SerializedName("codigo_rol")
    @Expose
    private Integer codigo_rol;
    @SerializedName("baja")
    @Expose
    private Boolean baja;

    public Usuario() {
    }

    public Usuario(Integer id, String nombre_usuario, boolean es_cliente, String contraseña_usuario, String correo_usuario, Integer dia_alta, Integer mes_alta, Integer anyo_alta, Integer creditos, boolean penalizado, Integer dia_fin_pena, Integer mes_fin_pena, Integer anyo_fin_pena, Integer codigo_rol, Boolean baja) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
        this.es_cliente = es_cliente;
        this.contraseña_usuario = contraseña_usuario;
        this.correo_usuario = correo_usuario;
        this.dia_alta = dia_alta;
        this.mes_alta = mes_alta;
        this.anyo_alta = anyo_alta;
        this.creditos = creditos;
        this.penalizado = penalizado;
        this.dia_fin_pena = dia_fin_pena;
        this.mes_fin_pena = mes_fin_pena;
        this.anyo_fin_pena = anyo_fin_pena;
        this.codigo_rol = codigo_rol;
        this.baja = baja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getDia_alta() {
        return dia_alta;
    }

    public void setDia_alta(Integer dia_alta) {
        this.dia_alta = dia_alta;
    }

    public Integer getMes_alta() {
        return mes_alta;
    }

    public void setMes_alta(Integer mes_alta) {
        this.mes_alta = mes_alta;
    }

    public Integer getAnyo_alta() {
        return anyo_alta;
    }

    public void setAnyo_alta(Integer anyo_alta) {
        this.anyo_alta = anyo_alta;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }


    public boolean isPenalizado() {
        return penalizado;
    }

    public void setPenalizado(boolean penalizado) {
        this.penalizado = penalizado;
    }

    public Integer getDia_fin_pena() {
        return dia_fin_pena;
    }

    public void setDia_fin_pena(Integer dia_fin_pena) {
        this.dia_fin_pena = dia_fin_pena;
    }

    public Integer getMes_fin_pena() {
        return mes_fin_pena;
    }

    public void setMes_fin_pena(Integer mes_fin_pena) {
        this.mes_fin_pena = mes_fin_pena;
    }

    public Integer getAnyo_fin_pena() {
        return anyo_fin_pena;
    }

    public void setAnyo_fin_pena(Integer anyo_fin_pena) {
        this.anyo_fin_pena = anyo_fin_pena;
    }

    public Integer getCodigo_rol() {
        return codigo_rol;
    }

    public void setCodigo_rol(Integer codigo_rol) {
        this.codigo_rol = codigo_rol;
    }

    public Boolean getBaja() {
        return baja;
    }

    public void setBaja(Boolean baja) {
        this.baja = baja;
    }

    public boolean isEs_cliente() {
        return es_cliente;
    }

    public void setEs_cliente(boolean es_cliente) {
        this.es_cliente = es_cliente;
    }
}
