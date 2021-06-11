package com.example.gestioncliente.Conexión;


import com.example.gestioncliente.Datos.Observación;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiObservaciones {
    //Observa la observación

    @GET("observaciones")
    Call<ArrayList<Observación>> obtenerObservacionesUsuario(@Query("id_usuario")int id_usuario);

}
