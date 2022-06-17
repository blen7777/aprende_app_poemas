package com.joselopez.aprendeapp.adapters;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.joselopez.aprendeapp.Detail;
import com.joselopez.aprendeapp.Detail_poemas;
import com.joselopez.aprendeapp.R;
import com.joselopez.aprendeapp.models.Cuento;
import com.joselopez.aprendeapp.models.Poemas;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPoemas extends RecyclerView.Adapter<AdapterPoemas.ViewHolder>
{
    private final ArrayList<Poemas> Poemas;
    private final int itemLayout;
    private StorageReference mStorageRef;

    public AdapterPoemas(ArrayList<Poemas> poemas, int itemLayout)
    {
        Poemas = poemas;
        this.itemLayout = itemLayout;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView id_poema, cuerpo_poema, autor_poema, video, imgPath_poema, like_poema;
        public TextView titulo_poema;
        public ImageView image_poema;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id_poema = itemView.findViewById(R.id.id_poema);
            titulo_poema = itemView.findViewById(R.id.titulo_poema);
            cuerpo_poema = itemView.findViewById(R.id.cuerpo_poema);
            autor_poema = itemView.findViewById(R.id.autor_poema);
            video = itemView.findViewById(R.id.video);
            imgPath_poema = itemView.findViewById(R.id.imgPath_poema);
            like_poema = itemView.findViewById(R.id.like_poema);
            image_poema = itemView.findViewById(R.id.image_poema);

            //--- Var for sent to next activity
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    Intent link = new Intent(v.getContext(), Detail_poemas.class);
                    link.putExtra("id_poema", id_poema.getText().toString());
                    link.putExtra("titulo_poema", titulo_poema.getText().toString());
                    link.putExtra("autor_poema", autor_poema.getText().toString());
                    link.putExtra("video", video.getText().toString());
                    link.putExtra("imgPath_poema", imgPath_poema.getText().toString());
                    link.putExtra("cuerpo_poema", cuerpo_poema.getText().toString());
                    link.putExtra("like_poema", like_poema.getText().toString());
                    v.getContext().startActivity(link);
                }
            });
        }
    }

    @NonNull
    @Override
    public AdapterPoemas.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterPoemas.ViewHolder holder, int i)
    {
        final Poemas poemas = Poemas.get(i);
        holder.id_poema.setText(poemas.getId_poema());
        holder.titulo_poema.setText(poemas.getTitulo());
        holder.cuerpo_poema.setText(poemas.getCuerpo());
        holder.autor_poema.setText(poemas.getAutor());
        holder.video.setText(poemas.getVideo());
        holder.imgPath_poema.setText(poemas.getImagen());
        holder.like_poema.setText(poemas.getLikes().toString());

        mStorageRef = FirebaseStorage.getInstance().getReference().child(poemas.getImagen());
        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso
                        .with(holder.image_poema.getContext())
                        .load(uri)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(holder.image_poema);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //Log.d("Images Errro", e.toString());

            }
        });
    }

    @Override
    public int getItemCount() {
        return Poemas.size();
    }

}
