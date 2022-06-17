package com.joselopez.aprendeapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.joselopez.aprendeapp.R;
import com.joselopez.aprendeapp.adapters.AdapterCuentos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmenteDetail extends Fragment
{

    private StorageReference mStorageRef;
    View view;
    //-------Reference to ID of variables
    public TextView titulo;
    public TextView autor;
    public TextView cuerpo;

    public TextView videoName;
    public TextView imageName;

    ImageView image;
    private AdView mAdView;
    private AdView mAdView2;
    DatabaseReference reference;
    ArrayList<Cuento> list;
    AdapterCuentos adapterCuentos;
    private Toolbar mToolbar;
    int numberOfColumns = 2;

    public FragmenteDetail() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.detail_fragment, container, false);

        MobileAds.initialize(getActivity(), String.valueOf(R.string.id_admob));
        //MobileAds.initialize(getActivity(), "ca-app-pub-9442185294632906~7911098357");
        mAdView = view.findViewById(R.id.adView_cuentos_detail_full);
        mAdView2 = view.findViewById(R.id.adView_cuentos_detail_full2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView2.loadAd(adRequest);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //---------Code for call variables fron view
        titulo = getView().findViewById(R.id.titulo_view);
        cuerpo = getView().findViewById(R.id.cuerpo_view);
        autor = getView().findViewById(R.id.autor_view);
        image = getView().findViewById(R.id.image_cuento_banner);

        imageName = getView().findViewById(R.id.cuento_img_name);
        videoName = getView().findViewById(R.id.cuento_video_name);

        //---Code for call register to Firebase Database by ID
        Bundle extras = getActivity().getIntent().getExtras();
        final String sTitulo = extras.getString("titulo");
        final String sAutor = extras.getString("autor");
        final String sImage = extras.getString("image");
        final String sCuerpo = extras.getString("cuerpo");

        final String sImageName = extras.getString("image");
        final String sVideoName = extras.getString("video");

        //-----------Set Values to Detail
        titulo.setText(sTitulo);
        autor.setText(sAutor);
        cuerpo.setText(sCuerpo);

        //imageName.setText(sImageName);
        //videoName.setText(sVideoName);

        mStorageRef = FirebaseStorage.getInstance().getReference().child(sImage);
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
            public void onFailure(@NonNull Exception e) {

                //Log.d("Images Error", e.toString());

            }
        });



    }

}