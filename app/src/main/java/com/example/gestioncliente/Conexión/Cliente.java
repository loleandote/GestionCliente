package com.example.gestioncliente.Conexión;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cliente {
    private static final String URL = "http://192.168.1.133:3000";
    //private static final String URL = "http://192.168.1.141:3000";
    //https://api.github.com/repos/octocat/Hello-World
    //https://mygithub.github.io/loleandote/loleandote.github.io/main/db2.json/?
   // private static final String URL = "https://gist.githubusercontent.com/loleandote/b71adcbbfa31fc003b06cbc6b75983d3/raw/2e1b8f3d20f9b2e43e3c456705514da889c7ff21/";
    private static Retrofit retrofit = null;
    public static Retrofit obtenerCliente(){
        try {
            if (retrofit == null) {
                // Construimos el objeto Retrofit asociando la URL del servidor y el convertidor Gson
                // para formatear la respuesta JSON.
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(URL);
                builder.addConverterFactory(GsonConverterFactory.create());
                retrofit = builder
                        .build();

            }
        }catch (ExceptionInInitializerError ex ){
            System.err.println(ex.getMessage());
        }
        return retrofit;
    }
}
