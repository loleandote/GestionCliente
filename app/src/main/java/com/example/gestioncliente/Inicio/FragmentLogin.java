package com.example.gestioncliente.Inicio;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Conexión.apiRol;
import com.example.gestioncliente.Conexión.apiUsuario;
import com.example.gestioncliente.Datos.Rol;
import com.example.gestioncliente.Datos.Usuario;
import com.example.gestioncliente.MainActivity;
import com.example.gestioncliente.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.EmptyStackException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentLogin extends Fragment {

    private MainActivity mainActivity;
    private View vista;
    public FragmentLogin(){}
    public FragmentLogin(MainActivity mainActivity) {
        this.mainActivity= mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_login, container, false);
        EditText nombreEditText = vista.findViewById(R.id.nombreEditText);
        EditText contraseñaEditText = vista.findViewById(R.id.contraseñaEditText);
        Button entrar = vista.findViewById(R.id.EntrarButton);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= String.valueOf(nombreEditText.getText());
                String contraseña= String.valueOf(contraseñaEditText.getText());
                obtenerUsuario(nombre, contraseña);
            }
        });
        Button registro = vista.findViewById(R.id.IrRegistroFragmentButton);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentRegistro fragment_registro = new FragmentRegistro(mainActivity);
                mainActivity.cambiarFragment(fragment_registro);
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finishAffinity();
                // Handle the back button event

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return vista;
    }
    private void obtenerUsuario(String nombre, String contraseña){

        apiUsuario apiUsuario = mainActivity.retrofit.create(com.example.gestioncliente.Conexión.apiUsuario.class);
        Call<ArrayList<Usuario>> respuesta=apiUsuario.obtenerUsuario(nombre, contraseña, false, false, true);
       // Call<ArrayList<Usuario>> respuesta=apiUsuario.obtenerUsuario();
        respuesta.enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                if (response.isSuccessful()){
                ArrayList<Usuario> lista = response.body();
                if(lista.size()>0){
                        Usuario usuario = lista.get(0);
                            Intent intent = new Intent(mainActivity, ActividadConUsuario.class);
                            intent.putExtra("usuario", usuario.getId());
                            startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                System.err.println(String.valueOf(t.getLocalizedMessage()));
            }
        });
    }


}