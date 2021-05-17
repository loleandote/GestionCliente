package com.example.gestioncliente.Instalaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestioncliente.Datos.Instalación;
import com.example.gestioncliente.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InstalaciónAdapter extends RecyclerView.Adapter<InstalaciónAdapter.MiViewHolder>{
    ArrayList<Instalación> lista;
    private Context context;
    private View.OnClickListener onClickListener;

    public InstalaciónAdapter(Context context) {
        lista= new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
        System.out.println("hola");
        View view=inflater.inflate(R.layout.elementos_lista_instalacion,viewGroup,false);
        view.setOnClickListener(this.onClickListener);
      MiViewHolder miViewHolder= new MiViewHolder(view);
        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        try {
            Picasso.get().load(lista.get(position).getImagenes().get(0))
                        .placeholder(R.drawable.icons8_squats_30)
                    .error(R.drawable.icons8_error_cloud_48)
                    //.resize(80,60)
                    //.centerCrop()
                    .into(holder.imagenView);
        }catch (IllegalArgumentException ex){

        }


        String texto = String.valueOf(lista.get(position).getId());
        //Integer nombre = lista.get(postion).getId_reserva();
        //holder.nombreTextView.setText(texto);
        holder.nombreTextView.setText(lista.get(position).getNombre());
        String tipo = String.valueOf(lista.get(position).getTipo());
        holder.tipoTextView.setText(tipo);
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener)
    {
        this.onClickListener= onClickListener;
    }
    @Override
    public int getItemCount() {
        if(lista==null){
            return 0;
        }else
            return lista.size();
    }
    public void anyadirALista(ArrayList<Instalación> lista){
        this.lista.addAll(lista);
        notifyDataSetChanged(); // Actualizamos el recyclerView
    }


    public static class MiViewHolder extends  RecyclerView.ViewHolder{
        public TextView nombreTextView;
        public TextView tipoTextView;
        public ImageView imagenView;
        public MiViewHolder(View view){
            super(view);
            this.imagenView =itemView.findViewById(R.id.ImagenInstalacion);
            this.nombreTextView =itemView.findViewById(R.id.nombreInstalacionTextView);
            this.tipoTextView =itemView.findViewById(R.id.tipoInstalacionTextView);
        }
    }
}
