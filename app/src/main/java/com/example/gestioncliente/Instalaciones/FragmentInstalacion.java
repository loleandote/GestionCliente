package com.example.gestioncliente.Instalaciones;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.gestioncliente.ActividadConUsuario;
import com.example.gestioncliente.Conexión.apiReservas;
import com.example.gestioncliente.Conexión.apiUsuario;
import com.example.gestioncliente.DatePickerFragment;
import com.example.gestioncliente.Datos.Instalación;
import com.example.gestioncliente.Datos.Reserva;
import com.example.gestioncliente.Datos.Usuario;
import com.example.gestioncliente.R;
import com.example.gestioncliente.Reservas.FragmentReservas;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentInstalacion extends Fragment {
    private ActividadConUsuario actividadConUsuario;
    Instalación instalación;
    private View vista;
    private EditText DiaEditText;
    private Spinner reservasInicioDisponibles;
    private Spinner reservasFinDisponibles;
    private ArrayList<Reserva> reservasDia;
    private ArrayList<Integer>listaInicio;
    private ArrayList<Integer> listaFin;
    private int horaInicio;
    private int horaFin;
    private int dia;
    private int mes;
    private int año;
    private Reserva reserva;
    private FragmentInstalaciones fragmentInstalaciones;
    public FragmentInstalacion() {
        // Required empty public constructor
    }

    public FragmentInstalacion(ActividadConUsuario actividadConUsuario) {
        this.actividadConUsuario = actividadConUsuario;
    }
    public FragmentInstalacion(ActividadConUsuario actividadConUsuario, FragmentInstalaciones fragmentInstalaciones) {
        this.actividadConUsuario = actividadConUsuario;
        this.fragmentInstalaciones = fragmentInstalaciones;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_instalacion, container, false);
        reservasDia = new ArrayList<>();
        horaInicio=0;
        horaFin=0;
        ImageView imageView = vista.findViewById(R.id.InstalacionImagen);
        Picasso.get().load(instalación.getImagenes().get(0))
                .placeholder(R.drawable.icons8_squats_30)
                .error(R.drawable.icons8_error_cloud_48)
                //.resize(80,60)
                //.centerCrop()
                .into(imageView);
        LocalDate fecha = LocalDate.now();
        dia = fecha.getDayOfMonth();
        mes= fecha.getMonth().getValue();
        año = fecha.getYear();
        String dia2= String.valueOf(fecha.getDayOfMonth());
        if (fecha.getDayOfMonth()<10){
            dia2="0"+dia2;
        }
        String mes2= String.valueOf(fecha.getMonthValue());
        if(fecha.getMonthValue()<10){
            mes2="0"+mes2;
        }
       String Textodia = dia2+"-"+mes2+"-"+String.valueOf(fecha.getYear());

        DiaEditText = vista.findViewById(R.id.DiaEditText);
        DiaEditText.setText(Textodia);
        DiaEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        reservasInicioDisponibles= vista.findViewById(R.id.reservasInicioDisponibles);
        listaInicio =instalación.getHorario();
        listaFin= new ArrayList<>();
        for( int i =0; i<listaInicio.size();i++){
            listaFin.add(listaInicio.get(i)+1);
        }
        obtenerReservasDia();
        reservasInicioDisponibles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horaInicio = listaInicio.get(position);
                //System.out.println(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        reservasFinDisponibles = vista.findViewById(R.id.reservasFinDisponibles);
        reservasFinDisponibles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horaFin= listaFin.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //ActualizarListas();
        Button reservar = vista.findViewById(R.id.ReservarBoton);
        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiReservas apiReservas = actividadConUsuario.retrofit.create(apiReservas.class);
                int diferencia = horaFin - horaInicio;
                if (horaInicio>0 && horaFin>0&& diferencia > 0 && diferencia < 3) {
                    System.out.println(diferencia);
                    Call<Reserva> respuesta = apiReservas.guardaReserva(actividadConUsuario.usuario.getId(), instalación.getId(), instalación.getImagenes().get(0),instalación.getNombre(), dia,mes,año, horaInicio, horaFin, false, false, true,false);
                    respuesta.enqueue(new Callback<Reserva>() {
                        @Override
                        public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                            actualizarSaldoUsuario(response.body());
                        }

                        @Override
                        public void onFailure(Call<Reserva> call, Throwable t) {

                        }
                    });
                }
            }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
             volverAInstalaciones();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return vista;
    }
    private void volverAInstalaciones()
    {
        actividadConUsuario.cambiarFragmento(fragmentInstalaciones);
    }
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                Date fecha = new Date(year,month+1, day);
                dia = day;
                mes= month;
                año= year;
                Date fechaActual = new Date();
                if(fecha.compareTo(fechaActual)>0){
                    long diffInDays = ( (fecha.getTime() - fechaActual.getTime())
                    );
                    double diferencia = Math.floor(diffInDays/(1000*3600*24));
                    diferencia= diferencia-693987;
                    System.out.println(diferencia);
                    System.out.println(String.valueOf(day + "-" + (month+1) + "-" + year));
                    System.out.println(fecha.getDay()+"-"+fecha.getMonth()+"-"+fecha.getYear());
                    //System.out.println(fecha.toString());

                    System.out.println(fechaActual.toString());
                    String mes2 = String.valueOf(month+1);
                    String dia2= String.valueOf(day);
                    if (diferencia<=14 && diferencia>0){
                        horaInicio=0;
                        horaFin=0;
                        if(month+1<10){
                            mes2="0"+mes2;
                        }
                        if (day<10){
                            dia2="0"+dia2;
                        }
                        String textodia = dia2 + "-" + mes2 + "-" + year;
                        DiaEditText.setText(textodia);
                        listaInicio= new ArrayList<>();
                        listaInicio =instalación.getHorario();
                        listaFin= new ArrayList<>();
                        for( int i =0; i<listaInicio.size();i++){
                            listaFin.add(listaInicio.get(i)+1);
                        }
                        obtenerReservasDia();
                    }

                }

            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    private void actualizarSaldoUsuario(Reserva reserva){
        apiUsuario apiUsuario= actividadConUsuario.retrofit.create(apiUsuario.class);
        int creditos= actividadConUsuario.usuario.getCreditos()-instalación.getPrecio_hora()*(horaFin-horaInicio);
        if (creditos>=0) {
            actividadConUsuario.usuario.setCreditos(creditos);
            Call<Usuario> respuesta = apiUsuario.actualizarUsuario(actividadConUsuario.usuario.getId(), actividadConUsuario.usuario);
            respuesta.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    apiReservas apiReservas = actividadConUsuario.retrofit.create(com.example.gestioncliente.Conexión.apiReservas.class);
                    reserva.setPagado(true);
                    apiReservas.actualizarReserva(reserva.getId(), reserva);
                    FragmentReservas fragmentReservas = new FragmentReservas(actividadConUsuario);
                    actividadConUsuario.cambiarFragmento(fragmentReservas);
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {

                }
            });
        }
    }
    private void obtenerReservasDia(){
        apiReservas apiReservas= actividadConUsuario.retrofit.create(apiReservas.class);
        Call<ArrayList<Reserva>>respuesta = apiReservas.obtenerReservasInstalacionDia(instalación.getId(),false,false,año,mes,dia);
        respuesta.enqueue(new Callback<ArrayList<Reserva>>() {
            @Override
            public void onResponse(Call<ArrayList<Reserva>> call, Response<ArrayList<Reserva>> response) {
                reservasDia=response.body();
                System.out.println(response.body().size());
                for (int i=0;i<response.body().size();i++) {

                    int diferencia = response.body().get(i).getHora_fin() - response.body().get(i).getHora_inicio();

                    for (int j = 0; j < diferencia; j++) {
                        int posicion = listaInicio.indexOf(response.body().get(i).getHora_inicio() + j);
                            listaInicio.remove(posicion);
                            listaFin.remove(posicion);
                    }

                }
                ActualizarListas();
            }

            @Override
            public void onFailure(Call<ArrayList<Reserva>> call, Throwable t) {

            }
        });
    }

    private  void ActualizarListas()
    {
        horaInicio= listaInicio.get(0);
        ArrayAdapter<Integer> listaAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, listaInicio);
        reservasInicioDisponibles.setAdapter(listaAdapter);
        reservasFinDisponibles = vista.findViewById(R.id.reservasFinDisponibles);
        horaFin= listaFin.get(0);
        ArrayAdapter<Integer> listaAdapterFin = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, listaFin);
        reservasFinDisponibles.setAdapter(listaAdapterFin);
    }

}