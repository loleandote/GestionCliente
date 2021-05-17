package com.example.gestioncliente.Reservas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestioncliente.Datos.Reserva;
import com.example.gestioncliente.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.MiViewHolder>{
    public ArrayList<Reserva> lista;
    private Context context;
    private View.OnClickListener onClickListener;
    public ReservaAdapter(Context context){
        this.context= context;
        lista= new ArrayList<>();
    }
    public void setOnItemClickListener(View.OnClickListener onClickListener){
        this.onClickListener =onClickListener;
    }
    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.elementos_lista_reserva,viewGroup,false);
        view.setOnClickListener(this.onClickListener);
        MiViewHolder miViewHolder= new MiViewHolder(view);
        return miViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int postion){

        //new DownloadImageTask(holder.imagenView).execute(lista.get(postion).getImagen_instalacion());
        Reserva reserva =lista.get(postion);
        Picasso.get().load(reserva.getImagen_instalacion())
                .placeholder(R.drawable.icons8_squats_30)
                .error(R.drawable.icons8_error_cloud_48)
                //.resize(80,60)
                //.centerCrop()
                .into(holder.imagenView);
        String texto = String.valueOf(reserva.getId());
        //Integer nombre = lista.get(postion).getId_reserva();
        holder.nombreTextView.setText(reserva.getNombre_instalacion());
       // holder.nombreTextView.setText(texto);
        String año =String.valueOf(reserva.getDia()+"-"+reserva.getMes()+"-"+reserva.getAnyo());
        holder.descripcionTextView.setText(año);
//        holder.descripcionTextView.setText(String.valueOf(!reserva.isCancel_usu() && !reserva.isCancel_admin()));
        if (reserva.isCancel_usu() || reserva.isCancel_admin()){
            holder.canceladoTextView.setVisibility(View.VISIBLE);
        }

    }


    public void cambiarLista(ArrayList<Reserva>lista){
        this.lista= lista;
        notifyDataSetChanged();
    }

    public void anyadirALista(ArrayList<Reserva> lista){
        this.lista.addAll(lista);
        notifyDataSetChanged(); // Actualizamos el recyclerView
    }

    @Override
    public int getItemCount() {
        if(lista==null){
            return 0;
        }else
            return lista.size();
    }

    public static class MiViewHolder extends  RecyclerView.ViewHolder{
        public TextView nombreTextView;
        public TextView descripcionTextView;
        public TextView canceladoTextView;
        public ImageView imagenView;
        public MiViewHolder(View view){
            super(view);
            this.imagenView =itemView.findViewById(R.id.ImagenReserva);
            this.nombreTextView =itemView.findViewById(R.id.nombreReservaTextView);
            this.descripcionTextView =itemView.findViewById(R.id.descripcionReservaTextView);
            this.canceladoTextView = itemView.findViewById(R.id.cancelado_lista);
        }
    }
    /*private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }*/
}
