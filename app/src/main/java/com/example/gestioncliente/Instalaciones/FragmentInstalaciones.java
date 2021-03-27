package com.example.gestioncliente.Instalaciones;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Conexión.apiInstalaciones;
import com.example.gestioncliente.Datos.Instalación;
import com.example.gestioncliente.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentInstalaciones extends Fragment {
    public ActividadConUsuario actividadConUsuario;
    private View vista;
    private RecyclerView recyclerView;
    public InstalaciónAdapter instalaciónAdapter;
    private int instalacionPulsada;
    public FragmentInstalaciones() {
        // Required empty public constructor
    }
    public FragmentInstalaciones(ActividadConUsuario actividadConUsuario) {
        this.actividadConUsuario=actividadConUsuario;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_instalaciones, container, false);
        recyclerView = vista.findViewById(R.id.ReciclerViewInstalaciones);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        instalaciónAdapter = new InstalaciónAdapter(getActivity());
        recyclerView.setAdapter(instalaciónAdapter);
        instalaciónAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instalacionPulsada= recyclerView.getChildAdapterPosition(v);
                Instalación instalación =instalaciónAdapter.lista.get(instalacionPulsada);
                System.out.println(instalación.getNombre());
                seleccionarInstalacion(instalación);
            }
        });
        obtenerDatos();
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
    private void  obtenerDatos(){
        apiInstalaciones apiInstalaciones = actividadConUsuario.retrofit.create(apiInstalaciones.class);
        Call<ArrayList<Instalación>> respuesta = apiInstalaciones.obtenerInstalaciones();
        respuesta.enqueue(new Callback<ArrayList<Instalación>>() {
            @Override
            public void onResponse(Call<ArrayList<Instalación>> call, Response<ArrayList<Instalación>> response) {
                if (response.isSuccessful()){
                    ArrayList<Instalación>listaInstalaciones = response.body();
                    instalaciónAdapter.anyadirALista(listaInstalaciones);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Instalación>> call, Throwable t) {

            }
        });
    }
    private void seleccionarInstalacion(Instalación instalacion){
        FragmentInstalacion fragmentInstalacion= new FragmentInstalacion(actividadConUsuario);
        fragmentInstalacion.instalación= instalacion;
        actividadConUsuario.cambiarFragmento(fragmentInstalacion);
    }
}