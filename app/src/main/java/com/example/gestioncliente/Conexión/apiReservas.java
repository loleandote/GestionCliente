package com.example.gestioncliente.Conexión;


import com.example.gestioncliente.Datos.Reserva;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface apiReservas {
    @GET("reservas")
    Call<ArrayList<Reserva>> obtenerReservas();
    @GET("reservas")
    Call<ArrayList<Reserva>> obtenerReservas(@Query("dia") String dia);

    @FormUrlEncoded
    @POST("reservas")
    Call<Reserva> guardaReserva(
            @Field("id_usuario") int id_usuario,
            @Field("id_instalacion") int id_instalacion,
            @Field("imagen_instalacion") String imagen_instalacion,
            @Field("dia") String dia,
            @Field("hora_inicio") int hora_inicio,
            @Field("hora_fin") int hora_fin,
            @Field("cancel_usu") Boolean cancel_usu,
            @Field("no_acude") Boolean no_acude,
            @Field("pagado") Boolean pagado
    );
    @PUT("reservas/{id}")
    Call<Reserva> actualizarReserva(@Path("id") int id, @Body Reserva reserva);
}
