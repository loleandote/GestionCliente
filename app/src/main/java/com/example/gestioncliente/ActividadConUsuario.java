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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gestioncliente.Conexión.Cliente;
import com.example.gestioncliente.Conexión.apiRol;
import com.example.gestioncliente.Conexión.apiUsuario;
import com.example.gestioncliente.Datos.Rol;
import com.example.gestioncliente.Datos.Usuario;
import com.example.gestioncliente.Instalaciones.FragmentInstalaciones;
import com.example.gestioncliente.Perfil.FragmentPerfil;
import com.example.gestioncliente.Reservas.FragmentReservas;
import com.google.android.material.navigation.NavigationView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_con_usuario);

        propiedades =getBaseContext().getSharedPreferences("Idiomas",Context.MODE_PRIVATE);
        retrofit= Cliente.obtenerCliente();
        int id=1;
        id = getIntent().getIntExtra("usuario",0);
        if(id==0)
            id =1;
        obtenerUsuario(id);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icons8_menu_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        Menu menuses=navView.getMenu();
        for (int i=0; i<menuses.size(); i++){
            if (i==0){

            }
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction= false;
                Fragment fragment= null;
                switch (item.getItemId()){
                    case R.id.InstalacionesNavMenu:
                        fragmentTransaction= true;
                        fragment = new FragmentInstalaciones();
                        break;
                    case R.id.ReservasNavMenu:
                        fragmentTransaction= true;
                        fragment = new FragmentReservas();
                        break;
                    case R.id.PerfilNavMenu:
                        fragmentTransaction= true;
                        fragment=new FragmentPerfil();
                        break;
                }
                if (fragmentTransaction){
                    cambiarFragmento(fragment);
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
                   System.out.println(rol.getId());
                   FragmentReservas fragmentReservas = new FragmentReservas();
                   cambiarFragmento(fragmentReservas);
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
    private void configurarMenu(){
        
    }
}