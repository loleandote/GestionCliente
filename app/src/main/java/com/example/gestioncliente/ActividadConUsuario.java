package com.example.gestioncliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.gestioncliente.Conexión.Cliente;
import com.example.gestioncliente.Conexión.apiRol;
import com.example.gestioncliente.Conexión.apiUsuario;
import com.example.gestioncliente.Datos.Rol;
import com.example.gestioncliente.Datos.Usuario;
import com.example.gestioncliente.Instalaciones.FragmentInstalaciones;
import com.example.gestioncliente.Perfil.FragmentPerfil;
import com.example.gestioncliente.Reservas.FragmentReservas;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActividadConUsuario extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    public Retrofit retrofit;
    public Usuario usuario;
    public SharedPreferences propiedades;
    public Rol rol;
    public String lenguaje;
    public Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_con_usuario);
        propiedades =getBaseContext().getSharedPreferences("Idiomas",Context.MODE_PRIVATE);
        lenguaje = propiedades.getString("Idioma","");
        retrofit= Cliente.obtenerCliente();
        int id;
        id = getIntent().getIntExtra("usuario",0);
        obtenerUsuario(id);
resources= getResources();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction= false;
                Fragment fragment= null;
                int id=0;
                switch (item.getItemId()){
                    case R.id.InstalacionesNavMenu:
                        fragmentTransaction= true;
                        fragment = new FragmentInstalaciones();
                        id= R.string.Instalaciones;
                        break;
                    case R.id.ReservasNavMenu:
                        fragmentTransaction= true;
                        fragment = new FragmentReservas();
                        id= R.string.Reservas;
                        break;
                    case R.id.PerfilNavMenu:
                        fragmentTransaction= true;
                        fragment=new FragmentPerfil();
                        id= R.string.Perfil;
                        break;
                }
                if (fragmentTransaction){
                    cambiarFragmento(fragment, id);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else
                {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void obtenerUsuario(int idUsuario){
        apiUsuario apiUsuario = retrofit.create(apiUsuario.class);
        Call<ArrayList<Usuario>> listaUsuarios = apiUsuario.obtenerUsuario(idUsuario);
        listaUsuarios.enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                if(response.body().size()>0) {
                    usuario = response.body().get(0);
                    View header = navView.getHeaderView(0);
                    TextView tituloNombre = header.findViewById(R.id.nombreUsuarioMenu);
                    tituloNombre.setText(usuario.getNombre_usuario());
                    TextView tituloCorreo = header.findViewById(R.id.correoUsuarioMenu);
                    tituloCorreo.setText(usuario.getCorreo_usuario());
                    obtenerRol(usuario.getCodigo_rol());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {
                System.out.println(t.getCause());
            }
        });
    }

    private void obtenerRol(int idRol){
       apiRol apiRol = retrofit.create(com.example.gestioncliente.Conexión.apiRol.class);
       Call<ArrayList<Rol>> respuesta = apiRol.obtenerRol(idRol);
       respuesta.enqueue(new Callback<ArrayList<Rol>>() {
           @Override
           public void onResponse(Call<ArrayList<Rol>> call, Response<ArrayList<Rol>> response) {
               if (response.isSuccessful()&& response.body().size()>0) {
                   rol = response.body().get(0);
                   FragmentReservas fragmentReservas = new FragmentReservas();
                   cambiarFragmento(fragmentReservas, R.string.Reservas);
               }
           }

           @Override
           public void onFailure(Call<ArrayList<Rol>> call, Throwable t) {
                System.out.println(t.getCause());
           }
       });
    }
    public void cambiarFragmento(Fragment fragment){
        if( fragment instanceof FragmentInstalaciones)
        {
            ((FragmentInstalaciones) fragment).actividadConUsuario= this;
        }
        else{
            if (fragment instanceof FragmentReservas) {
                ((FragmentReservas) fragment).actividadConUsuario = this;
            } else {
                if (fragment instanceof FragmentPerfil) {
                    ((FragmentPerfil) fragment).actividadConUsuario = this;
                }
            }
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutPrincipal,fragment);
        fragmentTransaction.commit();
    }

    public void CambiarIdioma(String idioma){
        SharedPreferences sharedPref = getSharedPreferences("Idiomas",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Idioma",idioma);
        editor.commit();
    }
    public void mensajeError(View vista, LayoutInflater inflater, int idMensaje, String textoopcional){
        Snackbar snackbar = Snackbar.make(vista, "", Snackbar.LENGTH_LONG);
// Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
// Hide the text
       /* TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);*/

// Inflate our custom view
        View snackView = inflater.inflate(R.layout.snackbarlayout, null);
// Configure the view

        TextView textViewTop = (TextView) snackView.findViewById(R.id.snackbar_text);
        String mensaje=getResources().getString(idMensaje)+textoopcional;
        textViewTop.setText(mensaje);
        textViewTop.setTextColor(Color.WHITE);
        ImageView imagenEstado= snackView.findViewById(R.id.ImagenEstado);
        imagenEstado.setImageResource(R.drawable.close_81512);

//If the view is not covering the whole snackbar layout, add this line
        layout.setPadding(0,0,0,0);

// Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
// Show the Snackbar
        snackbar.show();
    }
    public void mensajeCorrecto(View vista, LayoutInflater inflater,  int idMensaje){
        Snackbar snackbar = Snackbar.make(vista, "", Snackbar.LENGTH_LONG);
// Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
// Hide the text
       /* TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);*/

// Inflate our custom view
        View snackView = inflater.inflate(R.layout.snackbarlayout, null);
// Configure the view

        TextView textViewTop = (TextView) snackView.findViewById(R.id.snackbar_text);
        textViewTop.setText(getResources().getString(idMensaje));
        textViewTop.setTextColor(Color.WHITE);
        ImageView imagenEstado= snackView.findViewById(R.id.ImagenEstado);
        imagenEstado.setImageResource(R.drawable.sign_check_icon_34365);

//If the view is not covering the whole snackbar layout, add this line
        layout.setPadding(0,0,0,0);

// Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
// Show the Snackbar
        snackbar.show();
    }
    public void cambiarFragmento(Fragment fragment, int titulo){
        String titular= resources.getString(titulo);
        getSupportActionBar().setTitle(titular);

        if( fragment instanceof FragmentInstalaciones)
        {
            ((FragmentInstalaciones) fragment).actividadConUsuario= this;
        }
        else {
            if (fragment instanceof FragmentReservas)
                ((FragmentReservas) fragment).actividadConUsuario = this;
             else
                if (fragment instanceof FragmentPerfil)
                    ((FragmentPerfil) fragment).actividadConUsuario = this;


        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutPrincipal,fragment);
        fragmentTransaction.commit();
    }

}