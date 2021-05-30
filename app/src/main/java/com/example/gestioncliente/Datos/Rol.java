package com.example.gestioncliente.Datos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rol {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nombre_rol")
    @Expose
    private String nombre_rol;

    @SerializedName("realiza_reservas")
    @Expose
    private boolean realiza_reserva;
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
    @SerializedName("mod_contra_otros")
    @Expose
    private boolean mod_contra_otros;
    @SerializedName("mod_permiso")
    @Expose
    private boolean mod_permiso;
    @SerializedName("expota_importa")
    @Expose
    private boolean expota_importa;

    public Rol(int id, String nombre_rol, boolean realiza_reserva, boolean cancela_reserva, boolean modificar_usuario, boolean baja_socio, boolean realiza_informe, boolean ver_grafico, boolean mod_contra_otros, boolean mod_permiso, boolean expota_importa) {
        this.id = id;
        this.nombre_rol = nombre_rol;
        this.realiza_reserva = realiza_reserva;
        this.cancela_reserva = cancela_reserva;
        this.modificar_usuario = modificar_usuario;
        this.baja_socio = baja_socio;
        this.realiza_informe = realiza_informe;
        this.ver_grafico = ver_grafico;
        this.mod_contra_otros = mod_contra_otros;
        this.mod_permiso = mod_permiso;
        this.expota_importa = expota_importa;
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


    public boolean isRealiza_reserva() {
        return realiza_reserva;
    }

    public void setRealiza_reserva(boolean realiza_reserva) {
        this.realiza_reserva = realiza_reserva;
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

    public boolean isMod_contra_otros() {
        return mod_contra_otros;
    }

    public void setMod_contra_otros(boolean mod_contra_otros) {
        this.mod_contra_otros = mod_contra_otros;
    }

    public boolean isMod_permiso() {
        return mod_permiso;
    }

    public void setMod_permiso(boolean mod_permiso) {
        this.mod_permiso = mod_permiso;
    }

    public boolean isExpota_importa() {
        return expota_importa;
    }

    public void setExpota_importa(boolean expota_importa) {
        this.expota_importa = expota_importa;
    }
}
