package com.example.gestioncliente.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reserva {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id_usuario")
    @Expose
    private int id_usuario;
    @SerializedName("id_instalacion")
    @Expose
    private int id_instalacion;
    @SerializedName("imagen_instalacion")
    @Expose
    private String imagen_instalacion;
    @SerializedName("nombre_instalacion")
    @Expose
    private String nombre_instalacion;
    @SerializedName("dia")
    @Expose
    private int dia;
    @SerializedName("mes")
    @Expose
    private int mes;
    @SerializedName("anyo")
    @Expose
    private int anyo;
    @SerializedName("hora_inicio")
    @Expose
    private int hora_inicio;
    @SerializedName("hora_fin")
    @Expose
    private int hora_fin;
    @SerializedName("cancel_usu")
    @Expose
    private boolean cancel_usu;
    @SerializedName("cancel_admin")
    @Expose
    private boolean cancel_admin;
    @SerializedName("no_acude")
    @Expose
    private boolean no_acude;
    @SerializedName("pagado")
    @Expose
    private boolean pagado;

    public Reserva() {
    }
    public Reserva( int id_usuario, int id_instalacion, String imagen_instalacion, String nombre_instalacion, int dia, int mes, int anyo, int hora_inicio, int hora_fin, boolean cancel_usu, boolean cancel_admin, boolean no_acude, boolean pagado) {
        this.id_usuario = id_usuario;
        this.id_instalacion = id_instalacion;
        this.imagen_instalacion = imagen_instalacion;
        this.nombre_instalacion = nombre_instalacion;
        this.dia = dia;
        this.mes = mes;
        this.anyo = anyo;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.cancel_usu = cancel_usu;
        this.cancel_admin = cancel_admin;
        this.no_acude = no_acude;
        this.pagado = pagado;
    }

    public Reserva(int id, int id_usuario, int id_instalacion, String imagen_instalacion, String nombre_instalacion, int dia, int mes, int anyo, int hora_inicio, int hora_fin, boolean cancel_usu, boolean cancel_admin, boolean no_acude, boolean pagado) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_instalacion = id_instalacion;
        this.imagen_instalacion = imagen_instalacion;
        this.nombre_instalacion = nombre_instalacion;
        this.dia = dia;
        this.mes = mes;
        this.anyo = anyo;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.cancel_usu = cancel_usu;
        this.cancel_admin = cancel_admin;
        this.no_acude = no_acude;
        this.pagado = pagado;
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

    public int getId_instalacion() {
        return id_instalacion;
    }

    public void setId_instalacion(int id_instalacion) {
        this.id_instalacion = id_instalacion;
    }

    public String getImagen_instalacion() {
        return imagen_instalacion;
    }

    public void setImagen_instalacion(String imagen_instalacion) {
        this.imagen_instalacion = imagen_instalacion;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return anyo;
    }

    public void setAño(int año) {
        this.anyo = año;
    }

    public int getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(int hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public int getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(int hora_fin) {
        this.hora_fin = hora_fin;
    }

    public boolean isCancel_usu() {
        return cancel_usu;
    }

    public void setCancel_usu(boolean cancel_usu) {
        this.cancel_usu = cancel_usu;
    }

    public boolean isCancel_admin() {
        return cancel_admin;
    }

    public void setCancel_admin(boolean cancel_admin) {
        this.cancel_admin = cancel_admin;
    }

    public boolean isNo_acude() {
        return no_acude;
    }

    public void setNo_acude(boolean no_acude) {
        this.no_acude = no_acude;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public String getNombre_instalacion() {
        return nombre_instalacion;
    }

    public void setNombre_instalacion(String nombre_instalacion) {
        this.nombre_instalacion = nombre_instalacion;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
}
