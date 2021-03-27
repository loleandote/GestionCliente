package com.example.gestioncliente.Perfil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestioncliente.R;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {
    private Context context;
    public String[] lista;
    private View.OnClickListener onClickListener;

    public PerfilAdapter(Context context, String[]lista) {
        this.context = context;

        this.lista = lista;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.elementos_lista_perfil,parent,false);

        view.setOnClickListener(this.onClickListener);

        PerfilViewHolder miViewHolder=new PerfilViewHolder(view);

        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder holder, int position) {
        String opcion = lista[position];
        holder.opcionTextView.setText(opcion);
    }

    @Override
    public int getItemCount() {
        if(lista == null)
            return 0;
        else
            return lista.length;
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    class PerfilViewHolder extends RecyclerView.ViewHolder{
        TextView opcionTextView;

        public PerfilViewHolder(View itemView) {
            super(itemView);
            opcionTextView = itemView.findViewById(R.id.OpcionPerfilTextView);
        }
    }
}
