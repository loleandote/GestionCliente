package com.example.gestioncliente;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gestioncliente.Conexión.apiUsuario;
import com.example.gestioncliente.Datos.Usuario;
import com.example.gestioncliente.Perfil.FragmentPerfil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentoConfigurarCuenta extends Fragment {

    ActividadConUsuario actividadConUsuario;
    View vista;
    public FragmentoConfigurarCuenta() {
        // Required empty public constructor
    }

    public FragmentoConfigurarCuenta(ActividadConUsuario actividadConUsuario) {
        this.actividadConUsuario= actividadConUsuario;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_fragmento_configurar_cuenta, container, false);
        EditText nombreEditText = vista.findViewById(R.id.nombreEditTextoConfigurar);
        nombreEditText.setText(actividadConUsuario.usuario.getNombre_usuario());
        EditText contraseñaEditText = vista.findViewById(R.id.contraseñaEditTextoConfigurar);
        contraseñaEditText.setText(actividadConUsuario.usuario.getContraseña_usuario());
        EditText  correoEditText = vista.findViewById(R.id.correoEditTextoConfigurar);
        contraseñaEditText.setText(actividadConUsuario.usuario.getCorreo_usuario());
        Button botonGuardar = vista.findViewById(R.id.GuardarCambiosUsuario);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiUsuario apiUsuario = actividadConUsuario.retrofit.create(com.example.gestioncliente.Conexión.apiUsuario.class);
                Usuario usuarioGuardar = actividadConUsuario.usuario;
                usuarioGuardar.setNombre_usuario(String.valueOf(nombreEditText.getText()));
                usuarioGuardar.setContraseña_usuario(String.valueOf(contraseñaEditText.getText()));
                usuarioGuardar.setCorreo_usuario(String.valueOf(correoEditText.getText()));
                Call<Usuario> respuesta =  apiUsuario.actualizarUsuario(usuarioGuardar.getId(),usuarioGuardar);
               respuesta.enqueue(new Callback<Usuario>() {
                   @Override
                   public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                       if (response.isSuccessful()){
                           actividadConUsuario.usuario= response.body();
                           FragmentPerfil fragmentPerfil= new FragmentPerfil(actividadConUsuario);
                           actividadConUsuario.cambiarFragmento(fragmentPerfil);
                       }
                   }

                   @Override
                   public void onFailure(Call<Usuario> call, Throwable t) {
                        System.out.println(t.getMessage());
                   }
               });
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                FragmentPerfil fragmentPerfil = new FragmentPerfil(actividadConUsuario);
                actividadConUsuario.cambiarFragmento(fragmentPerfil);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return vista;
    }
}