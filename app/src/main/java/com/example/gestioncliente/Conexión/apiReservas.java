package com.example.gestioncliente.Conexión;


import com.example.gestioncliente.Datos.Reserva;
import com.google.gson.JsonParser;

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
/*    @GET("reservas")
    Call<ArrayList<Reserva>> obtenerReservas(@Query("id_usuario") int id_usuairo);
    @GET("reservas")
    Call<ArrayList<Reserva>> obtenerReservas(@Query("id_usuario") int id_usuairo,@Query("anyo") int año);*/
    @GET("reservas")
    Call<ArrayList<Reserva>> obtenerReservas(@Query("id_usuario") int id_usuairo, @Query("anyo") int año, @Query("mes") int mes);
    @GET("reservas")
    Call<ArrayList<Reserva>> obtenerReservas(@Query("id_usuario") int id_usuairo, @Query("anyo") int año, @Query("mes") int mes, @Query("dia") int dia);
    @GET("reservas")
    Call<ArrayList<Reserva>> obtenerReservasInstalacionDia(@Query("id_instalacion") int id_instalacion,@Query("cancel_usu")boolean cancel_usu,@Query("cancel_admin")boolean cancel_admin, @Query("anyo") int año, @Query("mes") int mes, @Query("dia") int dia);

    @FormUrlEncoded
    @POST("reservas")
    Call<Reserva> guardaReserva(
            @Field("id_usuario") int id_usuario,
            @Field("id_instalacion") int id_instalacion,
            @Field("imagen_instalacion") String imagen_instalacion,
            @Field("nombre_instalacion")String nombre_instalacion,
            @Field("dia") int dia,
            @Field("mes") int mes,
            @Field("anyo")int anyo,
            @Field("hora_inicio") int hora_inicio,
            @Field("hora_fin") int hora_fin,
            @Field("cancel_usu") Boolean cancel_usu,
            @Field("cancel_admin") Boolean cancel_admin,
            @Field("no_acude") Boolean no_acude,
            @Field("pagado") Boolean pagado
    );


    @PUT("reservas/{id}")
    Call<Reserva> actualizarReserva(@Path("id") int id, @Body Reserva reserva);
}
