package com.joselopez.aprendeapp.adapters;

import android.content.Intent;
import android.net.Uri;
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
import com.joselopez.aprendeapp.R;
import com.joselopez.aprendeapp.models.Cuento;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCuentos extends RecyclerView.Adapter<AdapterCuentos.ViewHolder>
{
    private final ArrayList<Cuento> Cuentos;
    private final int itemLayout;
    private StorageReference mStorageRef;

    public AdapterCuentos(ArrayList<Cuento> cuentos,int itemLayout)
    {
        Cuentos = cuentos;
        this.itemLayout = itemLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView cuentoID;
        public TextView titulo;
        public ImageView image;
        public TextView cuerpo, autor, video, imgPath;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cuentoID = itemView.findViewById(R.id.id_cuento);
            titulo  = itemView.findViewById(R.id.title_cuento);
            image = itemView.findViewById(R.id.image_cuento);
            cuerpo = itemView.findViewById(R.id.cuerpo);
            autor = itemView.findViewById(R.id.autor);
            video = itemView.findViewById(R.id.video);
            imgPath = itemView.findViewById(R.id.imgPath);


            //--- Var for sent to next activity
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    Intent link = new Intent(v.getContext(), Detail.class);
                    link.putExtra("cuentoID", cuentoID.getText().toString());
                    link.putExtra("titulo", titulo.getText().toString());
                    link.putExtra("autor", autor.getText().toString());
                    link.putExtra("video", video.getText().toString());
                    link.putExtra("image", imgPath.getText().toString());
                    link.putExtra("cuerpo", cuerpo.getText().toString());
                    v.getContext().startActivity(link);
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i)
    {
        final Cuento cuentos = Cuentos.get(i);
        viewHolder.cuentoID.setText(cuentos.getId_cuento());
        viewHolder.titulo.setText(cuentos.getTitulo());
        viewHolder.cuerpo.setText(cuentos.getCuerpo());
        viewHolder.autor.setText(cuentos.getAutor());
        viewHolder.video.setText(cuentos.getVideo());
        viewHolder.imgPath.setText(cuentos.getImagen());

        mStorageRef = FirebaseStorage.getInstance().getReference().child(cuentos.getImagen());
        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso
                        .with(viewHolder.image.getContext())
                        .load(uri)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(viewHolder.image);

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
        return Cuentos.size();
    }


}
