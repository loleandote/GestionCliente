package com.example.gestioncliente.Conexión;

import com.example.gestioncliente.Datos.Instalación;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiInstalaciones {
    @GET("instalaciones")
    Call<ArrayList<Instalación>> obtenerInstalaciones();
    @GET("instalaciones")
    Call<ArrayList<Instalación>> obtenerInstalacionesPorTipo(@Query("tipo")int tipo);
}
