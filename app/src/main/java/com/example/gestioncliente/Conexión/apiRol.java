package com.example.gestioncliente.Conexión;

import com.example.gestioncliente.Datos.Rol;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiRol {
    @GET("roles")
    Call<ArrayList<Rol>> obtenerRol(@Query("id") int id_rol);
}
