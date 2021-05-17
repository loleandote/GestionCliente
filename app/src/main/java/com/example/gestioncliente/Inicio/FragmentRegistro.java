package com.example.gestioncliente.Inicio;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestioncliente.Conexión.apiUsuario;
import com.example.gestioncliente.Datos.Usuario;
import com.example.gestioncliente.MainActivity;
import com.example.gestioncliente.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentRegistro extends Fragment {
    private MainActivity mainActivity;
    View vista;
    public FragmentRegistro() {
        // Required empty public constructor
    }
    public FragmentRegistro(MainActivity mainActivity) {
        this.mainActivity= mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_registro, container, false);
        EditText nombreEditText = vista.findViewById(R.id.nombreEditTexto);
        EditText contraseñaEditText = vista.findViewById(R.id.contraseñaEditTexto);
        EditText correoEditText = vista.findViewById(R.id.correoEditTexto);

        Button registro = vista.findViewById(R.id.RegistrarUsuario);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= String.valueOf(nombreEditText.getText());
                String contraseña = String.valueOf(contraseñaEditText.getText());
                String correo = String.valueOf(correoEditText.getText());
                if(nombre.length()>3&&nombre.length()<9&&contraseña.length()>7&& contraseña.length()<17&&validarEmail(correo))
                   obtenerUsuario(nombre,contraseña, correo);
                    // guardarUsuario(nombre,contraseña,correo);
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                FragmentLogin fragmentLogin = new FragmentLogin(mainActivity);
                mainActivity.cambiarFragment(fragmentLogin);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return  vista;
    }
    private void obtenerUsuario(String nombre, String contraseña, String correo){
        apiUsuario apiUsuario = mainActivity.retrofit.create(apiUsuario.class);
        Call<Usuario> respuesta = apiUsuario.obtenerUsuarioNombre(nombre);
        respuesta.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    Usuario usuarios= response.body();
                    if( usuarios==null){

                        obtenerUsuarioCorreo(nombre, contraseña, correo);
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    private  void  obtenerUsuarioCorreo(String nombre, String contraseña, String correo){
        apiUsuario apiUsuario = mainActivity.retrofit.create(com.example.gestioncliente.Conexión.apiUsuario.class);
        Call<Usuario> respuesta = apiUsuario.obtenerUsuarioCorreo(contraseña);
        respuesta.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    if (response.body()== null)
                        guardarUsuario(nombre, contraseña, correo);

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }
    private void guardarUsuario(String nombre, String contraseña, String correo){
        apiUsuario apiUsuario = mainActivity.retrofit.create(apiUsuario.class);
        Date fechavalor= new Date();
        ArrayList<String> observaciones = new ArrayList<>();
        observaciones.add("");
        System.out.println(fechavalor.getDate());

        Call<Usuario> respuesta = apiUsuario.guardaUsuario(nombre, contraseña,true, correo,fechavalor.getDate(),fechavalor.getMonth()+1,fechavalor.getYear()+1900,100, observaciones,false,"",1);
        respuesta.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                FragmentLogin fragment_login= new FragmentLogin(mainActivity);
                mainActivity.cambiarFragment(fragment_login);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });

    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}