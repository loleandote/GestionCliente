package com.example.gestioncliente.Instalaciones;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Conexión.apiInstalaciones;
import com.example.gestioncliente.Datos.Instalación;
import com.example.gestioncliente.R;
import com.example.gestioncliente.Reservas.FragmentReservas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        Resources res = getResources();
        String[]tipos = res.getStringArray(R.array.TiposInstalaciones);
        Spinner FiltroTipoInstalaciones = vista.findViewById(R.id.FiltroTipoInstalaciones);
        ArrayAdapter<String> tiposAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,tipos);
        FiltroTipoInstalaciones.setAdapter(tiposAdapter);
        FiltroTipoInstalaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    obtenerDatos();
                }else
                {
                    obtenerDatosPorTipo(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        instalaciónAdapter = new InstalaciónAdapter(getActivity(), tipos);
        recyclerView.setAdapter(instalaciónAdapter);
        instalaciónAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actividadConUsuario.rol!=null & actividadConUsuario.rol.isRealiza_reservas()){
                    instalacionPulsada= recyclerView.getChildAdapterPosition(v);
                    Instalación instalación =instalaciónAdapter.lista.get(instalacionPulsada);
                    seleccionarInstalacion(instalación);

                }

            }
        });
        Spinner OrdenarInstalaciones = vista.findViewById(R.id.OrdenarInstalaciones);

        String[] opciones = res.getStringArray(R.array.OpcionesInstalaciones);
        ArrayAdapter<String> opcionesAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,opciones);
        OrdenarInstalaciones.setAdapter(opcionesAdapter);
        OrdenarInstalaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Instalación>lista = instalaciónAdapter.lista;
                switch (position){
                    case 3:
                        Collections.sort(lista, new Comparator<Instalación>() {
                            @Override
                            public int compare(Instalación o1, Instalación o2) {
                                return o1.getNombre().compareTo(o2.getNombre());
                            }
                        });
                        break;
                    case 2:
                        Collections.sort(lista, new Comparator<Instalación>() {
                            @Override
                            public int compare(Instalación o1, Instalación o2) {
                                return o2.getNombre().compareTo(o1.getNombre());
                            }
                        });
                        break;
                    case 1:
                        Collections.sort(lista, new Comparator<Instalación>() {
                            @Override
                            public int compare(Instalación o1, Instalación o2) {
                                int precio1 =o1.getPrecio_hora();
                                int precio2=o2.getPrecio_hora();
                                if(precio1<precio2)
                                    return -1;
                                if (precio1==precio2)
                                    return 0;
                                    return 1;
                            }
                        });
                        break;
                    case 0:
                        Collections.sort(lista, new Comparator<Instalación>() {
                            @Override
                            public int compare(Instalación o1, Instalación o2) {
                                int precio1 =o1.getPrecio_hora();
                                int precio2=o2.getPrecio_hora();
                                if(precio1<precio2)
                                    return 1;
                                if (precio1==precio2)
                                    return 0;
                                return -1;
                            }
                        });
                        break;
                }
        instalaciónAdapter.cambiarLista(lista);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        obtenerDatos();
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                FragmentReservas fragmentReservas= new FragmentReservas(actividadConUsuario);
                actividadConUsuario.cambiarFragmento(fragmentReservas, R.string.Reservas);
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
                    Collections.sort(listaInstalaciones, new Comparator<Instalación>() {
                        @Override
                        public int compare(Instalación o1, Instalación o2) {
                            int precio1 =o1.getPrecio_hora();
                            int precio2=o2.getPrecio_hora();
                            if(precio1<precio2)
                                return 1;
                            if (precio1==precio2)
                                return 0;
                            return -1;
                        }
                    });
                    instalaciónAdapter.cambiarLista(listaInstalaciones);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Instalación>> call, Throwable t) {

            }
        });
    }
    private void  obtenerDatosPorTipo(int tipo){
        apiInstalaciones apiInstalaciones = actividadConUsuario.retrofit.create(apiInstalaciones.class);
        Call<ArrayList<Instalación>> respuesta = apiInstalaciones.obtenerInstalacionesPorTipo(tipo);
        respuesta.enqueue(new Callback<ArrayList<Instalación>>() {
            @Override
            public void onResponse(Call<ArrayList<Instalación>> call, Response<ArrayList<Instalación>> response) {
                if (response.isSuccessful()){
                    ArrayList<Instalación>listaInstalaciones = response.body();
                    instalaciónAdapter.cambiarLista(listaInstalaciones);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Instalación>> call, Throwable t) {

            }
        });
    }
    private void seleccionarInstalacion(Instalación instalacion){
        try{
        FragmentInstalacion fragmentInstalacion= new FragmentInstalacion(actividadConUsuario, this);
        fragmentInstalacion.instalación= instalacion;
        actividadConUsuario.cambiarFragmento(fragmentInstalacion, R.string.Instalacion);
    }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}