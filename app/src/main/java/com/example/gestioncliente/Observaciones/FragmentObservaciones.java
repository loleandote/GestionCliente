package com.example.gestioncliente.Observaciones;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Conexión.apiObservaciones;
import com.example.gestioncliente.Datos.Observación;
import com.example.gestioncliente.Perfil.FragmentPerfil;
import com.example.gestioncliente.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentObservaciones extends Fragment {

  ActividadConUsuario actividadConUsuario;
  View vista;
    ObservaciónAdapter observaciónAdapter;
    RecyclerView recyclerView;
    public FragmentObservaciones() {
        // Required empty public constructor
    }

    public FragmentObservaciones(ActividadConUsuario actividadConUsuario) {
        this.actividadConUsuario = actividadConUsuario;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_observaciones, container, false);
        TextView TituloDeLasObservaciones= vista.findViewById(R.id.TituloDeLasObservaciones);
        Resources res = getResources();
        if (actividadConUsuario.lenguaje.equals("es")|| actividadConUsuario.lenguaje.equals("")) {
            TituloDeLasObservaciones.setText( res.getString(R.string.ObservacionesDe) + actividadConUsuario.usuario.getNombre_usuario());
        }
        else
            TituloDeLasObservaciones.setText(actividadConUsuario.usuario.getNombre_usuario()+res.getString(R.string.ObservacionesDe));
        recyclerView = vista.findViewById(R.id.ReciclerviewObservaciones);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        observaciónAdapter = new ObservaciónAdapter(getActivity());
        recyclerView.setAdapter(observaciónAdapter);

        observaciónAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int observaciónPulsada = recyclerView.getChildAdapterPosition(v);
                Observación observación = observaciónAdapter.lista.get(observaciónPulsada);
                FragmentObservacion fragmentObservacion= new FragmentObservacion(actividadConUsuario, observación);
                actividadConUsuario.cambiarFragmento(fragmentObservacion, R.string.Observacion);
            }
        });

        obtenerObservaciones();
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                FragmentPerfil fragmentPerfil = new FragmentPerfil(actividadConUsuario);
                actividadConUsuario.cambiarFragmento(fragmentPerfil, R.string.Perfil);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return vista;
    }

    private void obtenerObservaciones(){
        apiObservaciones apiObservaciones = actividadConUsuario.retrofit.create(com.example.gestioncliente.Conexión.apiObservaciones.class);
        Call<ArrayList<Observación>> respuesta = apiObservaciones.obtenerObservacionesUsuario(actividadConUsuario.usuario.getId());
        respuesta.enqueue(new Callback<ArrayList<Observación>>() {
            @Override
            public void onResponse(Call<ArrayList<Observación>> call, Response<ArrayList<Observación>> response) {
                if (response.isSuccessful()){
                    observaciónAdapter.anyadirALista(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Observación>> call, Throwable t) {

            }
        });
    }
}