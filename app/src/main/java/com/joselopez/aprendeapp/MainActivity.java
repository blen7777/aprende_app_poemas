package com.joselopez.aprendeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.joselopez.aprendeapp.fragments.Adivinanza;
import com.joselopez.aprendeapp.fragments.Cuento;
import com.joselopez.aprendeapp.fragments.Poemas;
import com.joselopez.aprendeapp.fragments.Sugerencia;

public class MainActivity extends AppCompatActivity
{
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        //bottomNavigation.inflateMenu(R.menu.navigation);

        fragmentManager = getSupportFragmentManager();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_poemas:
                        fragment = new Poemas();
                        break;
                    case R.id.navigation_chistes:
                        fragment = new Adivinanza();
                        break;
                    case R.id.navigation_sugerencia:
                        fragment = new Sugerencia();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

        fragment = new Poemas();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();

    }

}
