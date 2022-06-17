package com.joselopez.aprendeapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joselopez.aprendeapp.R;
import com.joselopez.aprendeapp.models.Sugerencias;

public class Sugerencia extends Fragment {
    EditText titulo;
    EditText descripcion;
    Button save;
    private AdView mAdView;
    DatabaseReference databaseReference;

    public Sugerencia() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sugerencias, container, false);

        MobileAds.initialize(getActivity(), String.valueOf(R.string.id_admob));
        mAdView = view.findViewById(R.id.adView_sugerencia);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        titulo = view.findViewById(R.id.suggestion_subject);
        descripcion = view.findViewById(R.id.suggestion_body);

        save = view.findViewById(R.id.suggestino_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tituloE = titulo.getText().toString();
                String descripcionE = descripcion.getText().toString();
                if (titulo.getText().toString().length() == 0) {
                    titulo.setError("Titulo es requerido");
                    return;
                }
                if (descripcion.getText().toString().length() == 0) {
                    descripcion.setError("Descripcion es requerida");
                    return;
                }

                Sugerencias sugerencias = new Sugerencias(tituloE, descripcionE);
                databaseReference.child("Sugerencias").push().setValue(sugerencias, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Toast.makeText(getActivity(), "Data could not be saved ", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Sugerencia enviada con éxito.", Toast.LENGTH_LONG).show();
                            titulo.setText("");
                            descripcion.setText("");
                            titulo.isFocused();
                        }
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();


    }
}