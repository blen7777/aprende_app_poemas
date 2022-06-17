package com.joselopez.aprendeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash_aprende extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_aprende);

        Thread timer = new Thread()
        {
            public  void run()
            {
                try
                {
                    sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {

                    Intent intent = new Intent(Splash_aprende.this, MainActivity.class);
                    Splash_aprende.this.startActivity(intent);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
