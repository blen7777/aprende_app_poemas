package com.joselopez.aprendeapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.joselopez.aprendeapp.R;
import com.squareup.picasso.Picasso;

public class FragmentDetailPoema extends Fragment
{
    View view;
    //-------Reference to ID of variables
    public TextView titulo_poema;
    public TextView autor;
    public TextView cuerpo;
    public TextView likes;

    public TextView videoName;
    public TextView imageName;

    ImageView image;
    private AdView mAdView3;

    public FragmentDetailPoema() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.datail_fragment_poema, container, false);

        MobileAds.initialize(getActivity(), String.valueOf(R.string.id_admob));
        AdView mAdView = view.findViewById(R.id.ads_poema_detalle);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //---------Code for call variables fron view
        titulo_poema = getView().findViewById(R.id.titulo_poema_detail);
        cuerpo = getView().findViewById(R.id.cuerpo_poema_detail);
        autor = getView().findViewById(R.id.autor_poema_detail);
        image = getView().findViewById(R.id.imagen_poema);
        likes = getView().findViewById(R.id.like_poema_detail);

        imageName = getView().findViewById(R.id.poema_img_name);
        videoName = getView().findViewById(R.id.poema_video_name);

        //---Code for call register to Firebase Database by ID
        Bundle extras = getActivity().getIntent().getExtras();
        final String sTitulo = extras.getString("titulo_poema");
        final String sAutor = extras.getString("autor_poema");
        final String sImage = extras.getString("imgPath_poema");
        final String sCuerpo = extras.getString("cuerpo_poema");
        final String sLikes = extras.getString("like_poema");

        //-----------Set Values to Detail
        titulo_poema.setText(sTitulo);
        autor.setText(sAutor);
        cuerpo.setText(sCuerpo);
        likes.setText(sLikes);

        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference().child(sImage);
        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso
                        .with(image.getContext())
                        .load(uri)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(image);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
            }
        });
    }
}
