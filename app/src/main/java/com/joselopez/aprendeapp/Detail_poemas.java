package com.joselopez.aprendeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.joselopez.aprendeapp.adapters.ViewPagerApdapter;
import com.joselopez.aprendeapp.fragments.FragmentDetailPoema;
import com.joselopez.aprendeapp.fragments.FragmentVideo;
import com.joselopez.aprendeapp.fragments.FragmenteDetail;

public class Detail_poemas extends AppCompatActivity {

    //---code for config ViewPager
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public TextView id, titulo, autor, cuerpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_poemas);

        //-------Code on Create methos for viewpager
        tabLayout = findViewById(R.id.tab_layout_id);
        viewPager = findViewById(R.id.view_pager);

        ViewPagerApdapter adapter = new ViewPagerApdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentDetailPoema(), "Poema");
        adapter.AddFragment(new FragmentVideo(), "Poema en Video");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}