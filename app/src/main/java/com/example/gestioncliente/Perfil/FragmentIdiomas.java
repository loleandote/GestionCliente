package com.example.gestioncliente.Perfil;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Perfil.FragmentPerfil;
import com.example.gestioncliente.Perfil.PerfilAdapter;
import com.example.gestioncliente.R;

import java.util.Locale;


public class FragmentIdiomas extends Fragment {

    private ActividadConUsuario actividadConUsuario;
    private int IdiomaPulsado;
    private View vista;
    private RecyclerView recyclerView;
    private PerfilAdapter perfilAdapter;
    public FragmentIdiomas() {
        // Required empty public constructor
    }

    public FragmentIdiomas(ActividadConUsuario actividadConUsuario) {
        this.actividadConUsuario= actividadConUsuario;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_idiomas, container, false);
        recyclerView = vista.findViewById(R.id.ReciclerViewIdiomas);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        String[]opciones = obtenerValoresOpciones();
        perfilAdapter = new PerfilAdapter(getActivity(),opciones);
        recyclerView.setAdapter(perfilAdapter);
        Resources res = getResources();
        perfilAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdiomaPulsado= recyclerView.getChildAdapterPosition(v);
                Locale locale= Locale.getDefault();
                String idioma="";
                if (IdiomaPulsado ==0) {
                    locale = Locale.getDefault();
                    // actividadConUsuario.beginPayment();
                }else {
                    locale = Locale.UK;
                    idioma="en";
                }
                actividadConUsuario.CambiarIdioma(idioma);
                Configuration config =new Configuration();
                config.locale = locale;
                getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
                Intent intent= new Intent(actividadConUsuario, ActividadConUsuario.class);
                if (actividadConUsuario.usuario !=null)
                    intent.putExtra("usuario", actividadConUsuario.usuario.getId());
                startActivity(intent);
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
    private String[] obtenerValoresOpciones(){
        Resources res = getResources();
        String[] opcion = res.getStringArray(R.array.IdiomasAplicacion);
        return  opcion;
    }
}