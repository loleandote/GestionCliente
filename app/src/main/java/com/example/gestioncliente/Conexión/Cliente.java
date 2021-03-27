package com.example.gestioncliente.Conexión;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cliente {
    private static final String URL = "http://192.168.1.133:3000";
    private static Retrofit retrofit = null;
    public static Retrofit obtenerCliente(){
        try {
            if (retrofit == null) {

                // Construimos el objeto Retrofit asociando la URL del servidor y el convertidor Gson
                // para formatear la respuesta JSON.
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(URL);
                System.out.println("hola");
                builder.addConverterFactory(GsonConverterFactory.create());
                retrofit = builder
                        .build();
            }
        }catch (ExceptionInInitializerError ex ){

            System.out.println(ex);
        }
        return retrofit;
    }
}
