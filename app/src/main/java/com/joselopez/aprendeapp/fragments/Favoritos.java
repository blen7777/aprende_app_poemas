package com.joselopez.aprendeapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joselopez.aprendeapp.R;
import com.joselopez.aprendeapp.adapters.AdapterCuentos;

import java.util.ArrayList;
import java.util.List;

public class Favoritos extends Fragment
{
    DatabaseReference reference;
    ArrayList<com.joselopez.aprendeapp.models.Cuento> list;
    AdapterCuentos adapterCuentos;
    private Toolbar mToolbar;
    int numberOfColumns = 2;
    //private AdView mAdView;

    ArrayList<Cuento> faArrayList = null;
    Cuento favorito;


    public Favoritos() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_cuentos, container, false);

        //MobileAds.initialize(getActivity(), String.valueOf(R.string.id_admob_test));
        //MobileAds.initialize(getActivity(), "ca-app-pub-9442185294632906~7911098357");
        //mAdView = view.findViewById(R.id.adView_cuentos_detail);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //--------------------Codigo para leer datos DB Local------
        faArrayList = new ArrayList<>();
        favorito = new Cuento();
        List<Cuento> faList = com.joselopez.aprendeapp.models.Cuento.listAll(Cuento.class);
        Log.d("data", faList.toString());
        for(int i=0; i<faList.size(); i++)
        {
            favorito = faList.get(i);
            faArrayList.add(favorito);
        }
        if (faArrayList == null  || faArrayList.isEmpty())
        {
            Log.d("dataFavNo", faArrayList.toString());
            Toast.makeText(getActivity(), "No hay datos para mostrar", Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("dataFav", faArrayList.toString());
        }
        //----------------END-------------------------------

        final RecyclerView recyclerView = getView().findViewById(R.id.recycler_cuentos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<com.joselopez.aprendeapp.models.Cuento>();

        reference = FirebaseDatabase.getInstance().getReference().child("Cuentos");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    com.joselopez.aprendeapp.models.Cuento c =
                            dataSnapshot1.getValue(com.joselopez.aprendeapp.models.Cuento.class);
                    list.add(c);
                }
                adapterCuentos = new AdapterCuentos(list, R.layout.row_cuentos);
                recyclerView.setAdapter(adapterCuentos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getActivity(), databaseError.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
}