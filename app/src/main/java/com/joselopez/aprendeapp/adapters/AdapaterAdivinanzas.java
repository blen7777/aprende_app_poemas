package com.joselopez.aprendeapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.joselopez.aprendeapp.R;
import com.joselopez.aprendeapp.models.Adivinanza;

import java.util.ArrayList;

public class AdapaterAdivinanzas extends RecyclerView.Adapter<AdapaterAdivinanzas.ViewHolder>
{

    private final ArrayList<com.joselopez.aprendeapp.models.Adivinanza> Adivinanza;
    private final int itemLayout;

    public AdapaterAdivinanzas(ArrayList<Adivinanza> adivinanzas,int itemLayout)
    {
        Adivinanza = adivinanzas;
        this.itemLayout = itemLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView adivininzaID;
        public TextView cuerpo;
        public TextView respuesta;
        public Button verRespuesta;
        public Button ocultarRespuesta;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            adivininzaID = itemView.findViewById(R.id.id_adivinanza);
            cuerpo  = itemView.findViewById(R.id.cuerpo_adivinanza);
            respuesta = itemView.findViewById(R.id.respuesta);
            verRespuesta = itemView.findViewById(R.id.respuesta_event);
            ocultarRespuesta = itemView.findViewById(R.id.back);

            verRespuesta.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    cuerpo.setVisibility(View.GONE);
                    respuesta.setVisibility(View.VISIBLE);
                    ocultarRespuesta.setVisibility(View.VISIBLE);
                    verRespuesta.setVisibility(View.GONE);

                }
            });

            ocultarRespuesta.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v)
                {
                    cuerpo.setVisibility(View.VISIBLE);
                    respuesta.setVisibility(View.GONE);
                    verRespuesta.setVisibility(View.VISIBLE);
                    ocultarRespuesta.setVisibility(View.GONE);
                }
            });

        }
    }

    @Override
    public AdapaterAdivinanzas.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);

        return new AdapaterAdivinanzas.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapaterAdivinanzas.ViewHolder viewHolder, int i)
    {
        final Adivinanza adivinanza = Adivinanza.get(i);
        viewHolder.adivininzaID.setText(adivinanza.getId_adivinanza());
        viewHolder.cuerpo.setText(adivinanza.getCuerpo());
        viewHolder.respuesta.setText(adivinanza.getRespuesta());

    }

    @Override
    public int getItemCount()
    {
        return Adivinanza.size();
    }


}