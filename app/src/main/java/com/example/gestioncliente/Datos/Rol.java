package com.example.gestioncliente.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rol {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("rango_rol")
    @Expose
    private int rango_rol;
    @SerializedName("nombre_rol")
    @Expose
    private String nombre_rol;
    @SerializedName("realiza_reservas")
    @Expose
    private boolean realiza_reservas;
    @SerializedName("realiza_reservas_otros")
    @Expose
    private boolean realiza_reservas_otros;
    @SerializedName("cancela_reserva")
    @Expose
    private boolean cancela_reserva;
    @SerializedName("modificar_usuario")
    @Expose
    private boolean modificar_usuario;
    @SerializedName("baja_socio")
    @Expose
    private boolean baja_socio;
    @SerializedName("realiza_informe")
    @Expose
    private boolean realiza_informe;
    @SerializedName("ver_grafico")
    @Expose
    private boolean ver_grafico;
    @SerializedName("mod_usuario_otros")
    @Expose
    private boolean mod_usuario_otros;
    @SerializedName("mod_permiso")
    @Expose
    private boolean mod_permiso;
    @SerializedName("exporta_importa")
    @Expose
    private boolean exporta_importa;


    public Rol() {
    }

    public Rol(int id, int rango_rol, String nombre_rol, boolean realiza_reservas, boolean realiza_reservas_otros, boolean cancela_reserva, boolean modificar_usuario, boolean baja_socio, boolean realiza_informe, boolean ver_grafico, boolean mod_usuario_otros, boolean mod_permiso, boolean exporta_importa) {
        this.id = id;
        this.rango_rol = rango_rol;
        this.nombre_rol = nombre_rol;
        this.realiza_reservas = realiza_reservas;
        this.realiza_reservas_otros = realiza_reservas_otros;
        this.cancela_reserva = cancela_reserva;
        this.modificar_usuario = modificar_usuario;
        this.baja_socio = baja_socio;
        this.realiza_informe = realiza_informe;
        this.ver_grafico = ver_grafico;
        this.mod_usuario_otros = mod_usuario_otros;
        this.mod_permiso = mod_permiso;
        this.exporta_importa = exporta_importa;
    }

    public boolean isRealiza_reservas() {
        return realiza_reservas;
    }

    public void setRealiza_reservas(boolean realiza_reservas) {
        this.realiza_reservas = realiza_reservas;
    }

    public boolean isRealiza_reservas_otros() {
        return realiza_reservas_otros;
    }

    public void setRealiza_reservas_otros(boolean realiza_reservas_otros) {
        this.realiza_reservas_otros = realiza_reservas_otros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public boolean isCancela_reserva() {
        return cancela_reserva;
    }

    public void setCancela_reserva(boolean cancela_reserva) {
        this.cancela_reserva = cancela_reserva;
    }

    public boolean isModificar_usuario() {
        return modificar_usuario;
    }

    public void setModificar_usuario(boolean modificar_usuario) {
        this.modificar_usuario = modificar_usuario;
    }

    public boolean isBaja_socio() {
        return baja_socio;
    }

    public void setBaja_socio(boolean baja_socio) {
        this.baja_socio = baja_socio;
    }

    public boolean isRealiza_informe() {
        return realiza_informe;
    }

    public void setRealiza_informe(boolean realiza_informe) {
        this.realiza_informe = realiza_informe;
    }

    public boolean isVer_grafico() {
        return ver_grafico;
    }

    public void setVer_grafico(boolean ver_grafico) {
        this.ver_grafico = ver_grafico;
    }

    public boolean ismod_usuario_otros() {
        return mod_usuario_otros;
    }

    public void setmod_usuario_otros(boolean mod_usuario_otros) {
        this.mod_usuario_otros = mod_usuario_otros;
    }

    public boolean isMod_permiso() {
        return mod_permiso;
    }

    public void setMod_permiso(boolean mod_permiso) {
        this.mod_permiso = mod_permiso;
    }

    public boolean isExporta_importa() {
        return exporta_importa;
    }

    public void setExporta_importa(boolean exporta_importa) {
        this.exporta_importa = exporta_importa;
    }

    public int getRango_rol() {
        return rango_rol;
    }

    public void setRango_rol(int rango_rol) {
        this.rango_rol = rango_rol;
    }
}
