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

import java.util.ArrayList;
import java.util.List;

public class Adivinanza extends Fragment
{
    DatabaseReference reference;
    ArrayList<com.joselopez.aprendeapp.models.Adivinanza> list;
    AdapaterAdivinanzas adapaterAdivinanzas;
    private Toolbar mToolbar;
    int numberOfColumns = 2;
    Button button;
    private AdView mAdView;
    ImageView erroImage;
    final boolean empty = true;

    com.joselopez.aprendeapp.models.Adivinanza Adivi;
    ArrayList<com.joselopez.aprendeapp.models.Adivinanza> faArrayList = null;

    public Adivinanza()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_adivinanzas, container, false);

        MobileAds.initialize(getActivity(), String.valueOf(R.string.id_admob));
        mAdView = view.findViewById(R.id.adView_adivinazas);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        erroImage = getActivity().findViewById(R.id.no_data2);

        final RecyclerView recyclerView = getView().findViewById(R.id.recycler_adivinanzas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<com.joselopez.aprendeapp.models.Adivinanza>();

        //--------------- Call class to check internet connection
        ValidateInternetConnection request =  new ValidateInternetConnection(getActivity());
        if (request.Connec())
        {

            reference = FirebaseDatabase.getInstance().getReference().child("Adivinanzas");
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
        else
        {
            //--------------------Codigo para leer datos DB Local------
            faArrayList = new ArrayList<>();
            Adivi = new com.joselopez.aprendeapp.models.Adivinanza();
            List<com.joselopez.aprendeapp.models.Adivinanza> faList2 =
                    com.joselopez.aprendeapp.models.Adivinanza.listAll(com.joselopez.aprendeapp.models.Adivinanza.class);
            if (faList2.size() > 0)
            {

                for(int i=0; i<faList2.size(); i++)
                {

                    Adivi = faList2.get(i);
                    faArrayList.add(Adivi);
                }
                adapaterAdivinanzas =  new AdapaterAdivinanzas(faArrayList, R.layout.row_adivinanzas);
                recyclerView.setAdapter(adapaterAdivinanzas);
            }
            else{
                erroImage.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "No hay datos para mostrar", Toast.LENGTH_LONG).show();
            }
            //----------------END-------------------------------
        }
    }

}
