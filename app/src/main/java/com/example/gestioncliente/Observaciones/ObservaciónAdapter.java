package com.example.gestioncliente.Observaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestioncliente.Datos.Observación;
import com.example.gestioncliente.R;

import java.util.ArrayList;

public class ObservaciónAdapter extends RecyclerView.Adapter<ObservaciónAdapter.MiViewHolder>{
    ArrayList<Observación> lista;
    private Context context;
    private View.OnClickListener onClickListener;

    public ObservaciónAdapter(Context context) {
        lista= new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.elementos_lista_observacion,parent,false);
        view.setOnClickListener(this.onClickListener);
        MiViewHolder miViewHolder= new MiViewHolder(view);
        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.tituloObservacion.setText(lista.get(position).getTitulo());
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
    public void anyadirALista(ArrayList<Observación> lista){
        this.lista=lista;
        notifyDataSetChanged(); // Actualizamos el recyclerView
    }
    public static class MiViewHolder extends  RecyclerView.ViewHolder{
    public TextView tituloObservacion;
    public MiViewHolder(View view){
        super(view);
        this.tituloObservacion =itemView.findViewById(R.id.tituloObservacion);

    }
}
}
