package com.joselopez.aprendeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.joselopez.aprendeapp.adapters.ViewPagerApdapter;
import com.joselopez.aprendeapp.fragments.FragmentVideo;
import com.joselopez.aprendeapp.fragments.FragmenteDetail;
import com.joselopez.aprendeapp.models.Cuento;

public class Detail extends AppCompatActivity
{
    //---code for config ViewPager
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public TextView id, titulo, autor, cuerpo, videoName, imagenName;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //-------Code on Create methos for viewpager
        tabLayout = findViewById(R.id.tab_layout_id);
        viewPager = findViewById(R.id.view_pager);

        ViewPagerApdapter adapter = new ViewPagerApdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmenteDetail(), "Cuento en Texto");
        adapter.AddFragment(new FragmentVideo(), "Cuento en Video");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void addFavorito(View view)
    {

        id = findViewById(R.id.cuento_id);
        titulo = findViewById(R.id.title_cuento);
        cuerpo = findViewById(R.id.cuerpo);
        autor = findViewById(R.id.autor);
        imagenName = findViewById(R.id.cuento_img_name);
        videoName = findViewById(R.id.cuento_video_name);

        //Cuento cuentoLoad = Cuento.findById(id.getText().toString());
        try
        {
            Cuento register = new Cuento(id.getText().toString(),titulo.getText().toString(),cuerpo.getText().toString(),autor.getText().toString(), imagenName.getText().toString(),videoName.getText().toString());
            register.save();
            Toast.makeText(this, "Se guardo para ver sin conexión  de datos ;)", Toast.LENGTH_LONG).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Algo salio mal", Toast.LENGTH_LONG).show();

            e.printStackTrace();
            Log.e("errorSave", e.toString());
        }
    }
}
