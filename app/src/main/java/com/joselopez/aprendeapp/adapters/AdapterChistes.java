package com.joselopez.aprendeapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joselopez.aprendeapp.models.Adivinanza;
import com.joselopez.aprendeapp.models.Chistes;

import java.util.ArrayList;

public class AdapterChistes extends RecyclerView.Adapter<AdapterChistes.ViewHolder>
{
    private final ArrayList<com.joselopez.aprendeapp.models.Chistes> Chiste;
    private final int itemLayout;

    public AdapterChistes(ArrayList<Chistes> chiste, int itemLayout) {
        Chiste = chiste;
        this.itemLayout = itemLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView id_chiste, titulo, cuerpo, categoria, likes;

        public ViewHolder(@NonNull View itemView)
        {
            //id_chiste =  itemView.findViewById();
            super(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);

        return new AdapterChistes.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
    {
        final Chistes chistes = Chistes.get(i);
        viewHolder.adivininzaID.setText(adivinanza.getId_adivinanza());
        viewHolder.cuerpo.setText(adivinanza.getCuerpo());
        viewHolder.respuesta.setText(adivinanza.getRespuesta());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
