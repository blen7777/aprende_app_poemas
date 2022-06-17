package com.joselopez.aprendeapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.joselopez.aprendeapp.adapters.AdapaterAdivinanzas;
import com.joselopez.aprendeapp.models.Adivinanza;

import java.util.ArrayList;
import java.util.List;

public class Chiste extends Fragment{

    DatabaseReference reference;
    ArrayList<Adivinanza> list;
    AdapaterAdivinanzas adapaterAdivinanzas;
    private Toolbar mToolbar;
    int numberOfColumns = 2;
    Button button;
    private AdView mAdView;
    ImageView erroImage;
    final boolean empty = true;

    com.joselopez.aprendeapp.models.Adivinanza Adivi;
    ArrayList<com.joselopez.aprendeapp.models.Adivinanza> faArrayList = null;

    public Chiste() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_chistes, container, false);

        MobileAds.initialize(getActivity(), String.valueOf(R.string.id_admob));
        mAdView = view.findViewById(R.id.adView_chiste);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        erroImage = getActivity().findViewById(R.id.no_data2);

        final RecyclerView recyclerView = getView().findViewById(R.id.recycler_chiste);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<com.joselopez.aprendeapp.models.Adivinanza>();

        //--------------- Call class to check internet connection
        ValidateInternetConnection request =  new ValidateInternetConnection(getActivity());
        if (request.Connec())
        {

            reference = FirebaseDatabase.getInstance().getReference().child("Chistes");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        com.joselopez.aprendeapp.models.Adivinanza c =
                                dataSnapshot1.getValue(com.joselopez.aprendeapp.models.Adivinanza.class);
                        list.add(c);

                        // Save data after get information from database on firebase
                        com.joselopez.aprendeapp.models.Adivinanza requestSave =
                                new com.joselopez.aprendeapp.models.Adivinanza(c.getId_adivinanza(), c.getCuerpo(), "N/A",
                                        c.getRespuesta());
                        requestSave.save();
                    }
                    adapaterAdivinanzas = new AdapaterAdivinanzas(list, R.layout.row_adivinanzas);
                    recyclerView.setAdapter(adapaterAdivinanzas);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(), databaseError.toString(), Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
