package com.joselopez.aprendeapp.actions;

import android.graphics.Color;

import java.util.Random;

public class Common {

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
