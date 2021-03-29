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
import android.view.MenuItem;

import com.example.gestioncliente.Conexión.Cliente;
import com.example.gestioncliente.Conexión.apiUsuario;
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
   /* public static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("AT7W9-gdwD-U9UU50vBot626CNkKmjZukFp9JSFen5Yi6JEfqaQtBTZjEr9zHa0SE-hy1u6EdzaXmFKf");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_con_usuario);
        /*try {
            Intent serviceConfig = new Intent(this, PayPalService.class);
            serviceConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
            startService(serviceConfig);
        }catch(Exception ex){
            System.out.println(ex.getCause());
        }*/

        propiedades =getBaseContext().getSharedPreferences("Idiomas",Context.MODE_PRIVATE);
        retrofit= Cliente.obtenerCliente();
        int id=1;
        id = getIntent().getIntExtra("usuario",0);
        if(id==0)
            id =1;
        obtenerUsuario(id);
        FragmentReservas fragmentReservas = new FragmentReservas(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutPrincipal,fragmentReservas);
        fragmentTransaction.commit();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icons8_menu_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
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
                if(response.body().size()>0)
                    usuario = response.body().get(0);
            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {

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
    /*
    public void beginPayment(){
        try {
            PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(2)),"USD",
                    "Purchase Goods",PayPalPayment.PAYMENT_INTENT_SALE);
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
            startActivityForResult(intent,0);
        } catch (Exception ex){
            System.out.println(ex.getCause());
        }
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(resultCode);
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(
                    PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("sampleapp", confirm.toJSONObject().toString(4));
                    System.out.println("Gik");
                    // TODO: send 'confirm' to your server for verification

                } catch (JSONException e) {
                    Log.e("sampleapp", "no confirmation data: ", e);
                    System.out.println("Gik");
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("sampleapp", "The user canceled.");
            System.out.println("Gik");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("sampleapp", "Invalid payment / config set");
            System.out.println("Gik");
        }
    }
    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }*/
}