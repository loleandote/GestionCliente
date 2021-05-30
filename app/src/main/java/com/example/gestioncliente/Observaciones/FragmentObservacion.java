package com.example.gestioncliente.Observaciones;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Datos.Observación;
import com.example.gestioncliente.Perfil.FragmentPerfil;
import com.example.gestioncliente.R;


public class FragmentObservacion extends Fragment {

    ActividadConUsuario actividadConUsuario;
    Observación observación;
    View vista;
    TextView TextViewObservacion;
    TextView TextViewTitulo;
    public FragmentObservacion() {
        // Required empty public constructor
    }

    public FragmentObservacion(ActividadConUsuario actividadConUsuario, Observación observación) {
        this.actividadConUsuario = actividadConUsuario;
        this.observación = observación;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_observacion, container, false);
        TextViewTitulo = vista.findViewById(R.id.TextViewTitulo);
        TextViewTitulo.setText(observación.getTitulo());
        TextViewObservacion = vista.findViewById(R.id.TextViewObservacionTexto);
        TextViewObservacion.setText(observación.getContenido());

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                FragmentObservaciones fragmentObservaciones = new FragmentObservaciones(actividadConUsuario);
                actividadConUsuario.cambiarFragmento(fragmentObservaciones);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return vista;
    }
}