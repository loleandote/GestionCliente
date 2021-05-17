package com.example.gestioncliente.Reservas;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Conexión.apiReservas;
import com.example.gestioncliente.Datos.Reserva;
import com.example.gestioncliente.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentReserva extends Fragment {

    private ActividadConUsuario actividadConUsuario;
    private View vista;
    public Reserva reserva;
    private ImageView imagenReserva;
    private FragmentReservas fragmentReservas;
    public FragmentReserva() {
        // Required empty public constructor
    }

    public FragmentReserva(ActividadConUsuario actividadConUsuario, FragmentReservas fragmentReservas) {
        this.actividadConUsuario= actividadConUsuario;
        this.fragmentReservas= fragmentReservas;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_reserva, container, false);


        imagenReserva = vista.findViewById(R.id.ReservaImagen);
        if (reserva!= null & reserva.getImagen_instalacion()!=null)
        Picasso.get().load(reserva.getImagen_instalacion())
                .placeholder(R.drawable.icons8_squats_30)
                .error(R.drawable.icons8_error_cloud_48)
                //.resize(80,60)
                //.centerCrop()
                .into(imagenReserva);
        TextView fechaReserva = vista.findViewById(R.id.FechaResevaTextView);
        String textoFecha = String.valueOf(reserva.getDia())+"-"+String.valueOf(reserva.getMes())+"-"+String.valueOf(reserva.getAño());
        fechaReserva.setText(textoFecha);
        TextView fechaDesdeReserva = vista.findViewById(R.id.FechaDesdeTextView);
        fechaDesdeReserva.setText(String.valueOf(reserva.getHora_inicio())+":00");
        TextView fechaHastaReserva = vista.findViewById(R.id.FechaHastaTextView);
        fechaHastaReserva.setText(String.valueOf(reserva.getHora_fin())+":00");
        if (!reserva.isCancel_admin() && !reserva.isCancel_usu())
        {
            TextView canceladaReserva = vista.findViewById(R.id.CamceladaReservaTextView);
            canceladaReserva.setVisibility(View.GONE);
        }
        Button cancelarReserva = vista.findViewById(R.id.CancelarReserva);
        if (reserva != null & reserva.isCancel_usu() || reserva.isCancel_admin()){
            cancelarReserva.setVisibility(View.INVISIBLE);
        }
        cancelarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Date fechaReserva=new Date(1900+reserva.getAño(), reserva.getMes(), reserva.getDia());
                    fechaReserva.setHours(reserva.getHora_inicio()-1);
                    Date fechaActual = new Date();
System.out.println(fechaActual.toString());
System.out.println(fechaReserva.toString());
System.out.println(fechaActual.before(fechaReserva));
                    if (fechaActual.compareTo(fechaReserva)==-1){


                reserva.setCancel_usu(true);
                apiReservas apiReserva = actividadConUsuario.retrofit.create(apiReservas.class);
                Call<Reserva> respuesta = apiReserva.actualizarReserva(reserva.getId(),reserva);
                respuesta.enqueue(new Callback<Reserva>() {
                    @Override
                    public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                        FragmentReservas fragmentReservas= new FragmentReservas(actividadConUsuario);
                        actividadConUsuario.cambiarFragmento(fragmentReservas);
                    }

                    @Override
                    public void onFailure(Call<Reserva> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                    }
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            volverAReservas();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return vista;
    }
    private void volverAReservas(){
        actividadConUsuario.cambiarFragmento(fragmentReservas);
    }

}