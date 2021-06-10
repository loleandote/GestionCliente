package com.example.gestioncliente.Reservas;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Conexión.apiReservas;
import com.example.gestioncliente.DatePickerFragment;
import com.example.gestioncliente.Datos.Reserva;
import com.example.gestioncliente.Encriptado;
import com.example.gestioncliente.Instalaciones.FragmentInstalaciones;
import com.example.gestioncliente.R;
import com.example.gestioncliente.Reservas.FragmentReserva;
import com.example.gestioncliente.Reservas.ReservaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.CryptoPrimitive;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.crypto.Cipher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentReservas extends Fragment {

    public ActividadConUsuario actividadConUsuario;
    private View vista;
    private RecyclerView recyclerView;
    public ReservaAdapter reservaAdapter;
  /*  private EditText reservaFechaInicio;
    private EditText reservaFechaFin;*/
    private Spinner AñosUsuario,MesesAño;
    private int  mes, año;
    FloatingActionButton IrAInstalaciones;
    public FragmentReservas() {
        // Required empty public constructor
    }

    public FragmentReservas(ActividadConUsuario actividadConUsuario) {
        this.actividadConUsuario= actividadConUsuario;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_reservas, container, false);
        Date fecha = new Date();
        mes= fecha.getMonth()+1;
        año = fecha.getYear()+1900;
        String texto=Encriptado.get_SHA_512_SecurePassword("hola", "a");
        System.out.println(texto);
        String plainText = texto + "a";

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = messageDigest.digest( plainText.getBytes() );

        System.out.println("Result: " + new String(hash));
        AñosUsuario = vista.findViewById(R.id.AñosUsuario);
        ArrayList<Integer>listaAños = new ArrayList<>();
        int diferencia  = año-actividadConUsuario.usuario.getAnyo_alta();
        for (int i=0;i<=diferencia;i++){
            listaAños.add(actividadConUsuario.usuario.getAnyo_alta()+i);
        }
        ArrayAdapter<Integer> listaAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, listaAños);
        AñosUsuario.setAdapter(listaAdapter);
        AñosUsuario.setSelection(listaAños.size()-1);
        MesesAño = vista.findViewById(R.id.MesesAño);
        Resources res = getResources();
        String[] listaMeses = res.getStringArray(R.array.MesesAño);
        ArrayAdapter<String> listaMesesAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, listaMeses);
        MesesAño.setAdapter(listaMesesAdapter);
        MesesAño.setSelection(mes-1);
        MesesAño.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mes =position+1;
                obtenerDatos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerView = vista.findViewById(R.id.ReciclerViewReservas);

        Spinner OrdenarReservas = vista.findViewById(R.id.OrdenarReservas);
        String[] opciones = res.getStringArray(R.array.OpcionesReservas);
        ArrayAdapter<String>opcionesAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,opciones);
        OrdenarReservas.setAdapter(opcionesAdapter);
        OrdenarReservas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Reserva>lista= reservaAdapter.lista;
                switch (position){
                    case 0:
                        Collections.sort(lista, new Comparator<Reserva>() {
                            @Override
                            public int compare(Reserva o1, Reserva o2) {
                                Date fecha1 = new Date(o1.getAnyo(), o2.getMes(), o1.getDia());
                                Date fecha2 = new Date(o2.getAnyo(), o2.getMes(), o2.getDia());
                                return fecha1.compareTo(fecha2);
                            }
                        });
                        break;
                    case 1:
                        Collections.sort(lista, new Comparator<Reserva>() {
                            @Override
                            public int compare(Reserva o1, Reserva o2) {
                                Date fecha1 = new Date(o1.getAnyo(), o2.getMes(), o1.getDia());
                                Date fecha2 = new Date(o2.getAnyo(), o2.getMes(), o2.getDia());
                                return fecha2.compareTo(fecha1);
                            }
                        });
                        break;
                    case 2:
                        Collections.sort(lista, new Comparator<Reserva>() {
                            @Override
                            public int compare(Reserva o1, Reserva o2) {
                                return o1.getNombre_instalacion().compareTo(o2.getNombre_instalacion());
                            }
                        });
                        break;
                    case 3:
                        Collections.sort(lista, new Comparator<Reserva>() {
                            @Override
                            public int compare(Reserva o1, Reserva o2) {
                                return o2.getNombre_instalacion().compareTo(o1.getNombre_instalacion());
                            }
                        });
                        break;

                }
                reservaAdapter.cambiarLista(lista);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        reservaAdapter = new ReservaAdapter(getActivity());
        reservaAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resevaPulsada = recyclerView.getChildAdapterPosition(v);
                Reserva reserva = reservaAdapter.lista.get(resevaPulsada);
              seleccionarReserva(reserva);
            }
        });
        recyclerView.setAdapter(reservaAdapter);
        IrAInstalaciones= vista.findViewById(R.id.IrAInstalaciones);
        IrAInstalaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentInstalaciones fragmentInstalaciones=new FragmentInstalaciones(actividadConUsuario);
                actividadConUsuario.cambiarFragmento(fragmentInstalaciones,R.string.Instalaciones);
            }
        });

        //obtenerDatos();
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

    private void seleccionarReserva(Reserva reserva)
    {
        FragmentReserva fragmentReserva = new FragmentReserva(actividadConUsuario, this);
        fragmentReserva.reserva= reserva;
        actividadConUsuario.cambiarFragmento(fragmentReserva, R.string.Reservas);
    }
    private void  obtenerDatos(){

        apiReservas api = actividadConUsuario.retrofit.create(apiReservas.class);
        Call<ArrayList<Reserva>> respuesta1 = api.obtenerReservas(actividadConUsuario.usuario.getId(),año,mes);
        respuesta1.enqueue(new Callback<ArrayList<Reserva>>() {
            @Override
            public void onResponse(Call<ArrayList<Reserva>> call, Response<ArrayList<Reserva>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Reserva> listaReservas = response.body();
                    reservaAdapter.cambiarLista(listaReservas);
                } else{
                    Toast.makeText(getActivity(), "Fallo en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Reserva>> call, Throwable t) {
                //Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }

}