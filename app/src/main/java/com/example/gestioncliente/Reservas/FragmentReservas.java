package com.example.gestioncliente.Reservas;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Conexión.apiReservas;
import com.example.gestioncliente.DatePickerFragment;
import com.example.gestioncliente.Datos.Reserva;
import com.example.gestioncliente.R;
import com.example.gestioncliente.Reservas.FragmentReserva;
import com.example.gestioncliente.Reservas.ReservaAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentReservas extends Fragment {

    public ActividadConUsuario actividadConUsuario;
    private View vista;
    private RecyclerView recyclerView;
    public ReservaAdapter reservaAdapter;
    private EditText reservaFechaInicio;
    private EditText reservaFechaFin;
    public FragmentReservas() {
        // Required empty public constructor
    }

    public FragmentReservas(ActividadConUsuario actividadConUsuario) {
        this.actividadConUsuario= actividadConUsuario;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_reservas, container, false);
        reservaFechaInicio= vista.findViewById(R.id.ReservaFechaInicio);
        reservaFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        reservaFechaFin= vista.findViewById(R.id.ReservaFechaFin);
        reservaFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog2();
            }
        });
        recyclerView = vista.findViewById(R.id.ReciclerViewReservas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        reservaAdapter = new ReservaAdapter(getActivity());
        reservaAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resevaPulsada = recyclerView.getChildAdapterPosition(v);
                Reserva reserva = reservaAdapter.lista.get(resevaPulsada);
                FragmentReserva fragmentReserva = new FragmentReserva(actividadConUsuario);
                fragmentReserva.reserva= reserva;
                actividadConUsuario.cambiarFragmento(fragmentReserva);
            }
        });
        recyclerView.setAdapter(reservaAdapter);
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

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "-" + (month+1) + "-" + year;
                reservaFechaInicio.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    private void showDatePickerDialog2() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "-" + (month+1) + "-" + year;
                reservaFechaFin.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    private void  obtenerDatos(){

        apiReservas api = actividadConUsuario.retrofit.create(apiReservas.class);
        Call<ArrayList<Reserva>> respuesta1 = api.obtenerReservas();
        System.out.println("Hol.a");
        respuesta1.enqueue(new Callback<ArrayList<Reserva>>() {
            @Override
            public void onResponse(Call<ArrayList<Reserva>> call, Response<ArrayList<Reserva>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Reserva> listapersonajes = response.body();
                    reservaAdapter.anyadirALista(listapersonajes);
                    //System.out.println(listapersonajes.size());
                    System.out.println("Hol.a4");
                } else{
                    Toast.makeText(getActivity(), "Fallo en la respuesta", Toast.LENGTH_SHORT).show();
                    System.out.println("Hol.a2");
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