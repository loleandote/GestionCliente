package com.example.gestioncliente.Conexión;

import com.example.gestioncliente.Datos.Usuario;

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

public interface apiUsuario {
    @GET("usuarios")
    Call<ArrayList<Usuario>> obtenerUsuario(@Query("id") int id_usuario);
    @GET("usuarios")
    Call<ArrayList<Usuario>> obtenerUsuario(@Query("nombre_usuario") String nombre_usuario, @Query("contrasña_usuario") String contraseña);
    @GET("usuarios")
    Call<ArrayList<Usuario>> obtenerUsuarioNomberContraseñaCorreo(@Query("nombre_usuario") String nombre_usuario, @Query("contrasña_usuario") String contraseña,@Query("correo_usuario")String correoUsuario);
    @FormUrlEncoded
    @POST("usuarios")
    Call<Usuario> guardaUsuario(
            @Field("nombre_usuario") String nombre,
            @Field("contraseña_usuario") String contraseña,
            @Field("correo_usuario") String correo,
            @Field("fecha_alta") String fecha_alta,
            @Field("creditos") int creditos,
            @Field("observaciones")ArrayList<String>observaciones,
            @Field("penalizado") Boolean penalizado,
            @Field("fecha_fin_pena") String fecha_fin_pena,
            @Field("codigo_rol") int codigo_rol
    );
    @PUT("usuarios/{id}")
    Call<Usuario> actualizarUsuario(@Path("id") int id, @Body Usuario usuario);

}
