package com.joselopez.aprendeapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joselopez.aprendeapp.R;
import com.joselopez.aprendeapp.actions.ValidateInternetConnection;
import com.joselopez.aprendeapp.adapters.AdapterCuentos;
import com.joselopez.aprendeapp.adapters.AdapterPoemas;
import com.joselopez.aprendeapp.models.Cuento;

import java.util.ArrayList;

public class Poemas extends Fragment
{
    DatabaseReference reference;
    ArrayList<com.joselopez.aprendeapp.models.Poemas> list;
    AdapterPoemas adapterPoemas;
    ImageView erroImage;
    private AdView mAdView;

    public Poemas() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_poemas, container, false);

        MobileAds.initialize(getActivity(), String.valueOf(R.string.id_admob));
        mAdView = view.findViewById(R.id.adView_cuentos_detail);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        erroImage = getActivity().findViewById(R.id.no_data);

        final RecyclerView recyclerView = getView().findViewById(R.id.recycler_poemas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<com.joselopez.aprendeapp.models.Poemas>();

        //--------------- Call class to check internet connection
        ValidateInternetConnection request =  new ValidateInternetConnection(getActivity());
        if (request.Connec())
        {
            reference = FirebaseDatabase.getInstance().getReference().child("Poemas");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if(dataSnapshot.exists())
                    {

                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            com.joselopez.aprendeapp.models.Poemas c = dataSnapshot1.getValue(com.joselopez.aprendeapp.models.Poemas.class);
                            list.add(c);

                        }
                        adapterPoemas = new AdapterPoemas(list, R.layout.row_poemas);
                        recyclerView.setAdapter(adapterPoemas);
                    }
                    else
                    {

                        Toast.makeText(getActivity(), "No data for show in the application", Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError)
                {
                    Toast.makeText(getActivity(), databaseError.toString(), Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
