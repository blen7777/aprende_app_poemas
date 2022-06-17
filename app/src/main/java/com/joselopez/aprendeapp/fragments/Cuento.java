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

import java.util.ArrayList;
import java.util.List;

public class Cuento extends Fragment
{

    DatabaseReference reference;
    ArrayList<com.joselopez.aprendeapp.models.Cuento> list;
    AdapterCuentos adapterCuentos;
    private Toolbar mToolbar;
    int numberOfColumns = 2;
    ImageView erroImage;
    private AdView mAdView;
    com.joselopez.aprendeapp.models.Cuento Cuent;
    ArrayList<com.joselopez.aprendeapp.models.Cuento> faArrayList = null;

    public Cuento() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_cuentos, container, false);


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
        list = new ArrayList<com.joselopez.aprendeapp.models.Cuento>();

        //--------------- Call class to check internet connection
        ValidateInternetConnection request =  new ValidateInternetConnection(getActivity());
        if (request.Connec())
        {
            reference = FirebaseDatabase.getInstance().getReference().child("Cuentos");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    if(dataSnapshot.exists())
                    {
                        //Toast.makeText(getActivity(), "Data load successfully", Toast.LENGTH_LONG).show();
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            com.joselopez.aprendeapp.models.Cuento c = dataSnapshot1.getValue(com.joselopez.aprendeapp.models.Cuento.class);
                            list.add(c);

                            // Save data after get information from database on firebase
                            /*com.joselopez.aprendeapp.models.Cuento requestSave =
                                    new com.joselopez.aprendeapp.models.Cuento(c.getId_cuento(), c.getTitulo(), c.getCuerpo(),
                                            c.getAutor(), c.getImagen(), c.getVideo());
                            requestSave.save();*/

                        }
                        adapterCuentos = new AdapterCuentos(list, R.layout.row_cuentos);
                        recyclerView.setAdapter(adapterCuentos);
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
        /*else{

            //--------------------Codigo para leer datos DB Local------
            try
            {
                faArrayList = new ArrayList<>();
                Cuent = new com.joselopez.aprendeapp.models.Cuento();
                List<com.joselopez.aprendeapp.models.Cuento> faList = com.joselopez.aprendeapp.models.Cuento.listAll(com.joselopez.aprendeapp.models.Cuento.class);

                for(int i=0; i<faList.size(); i++)
                {
                    Cuent = faList.get(i);
                    faArrayList.add(Cuent);
                }
                adapterCuentos =  new AdapterCuentos(faArrayList, R.layout.row_cuentos);
                recyclerView.setAdapter(adapterCuentos);

            }
            catch (Exception e)
            {
                erroImage.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "No hay datos para mostrar", Toast.LENGTH_LONG).show();
                //Log.d("ELoadCuento", e.toString());
            }

            //----------------END-------------------------------
        }*/


    }
}
