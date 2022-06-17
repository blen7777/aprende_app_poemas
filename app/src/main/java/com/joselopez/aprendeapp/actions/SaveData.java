package com.joselopez.aprendeapp.actions;

import android.util.Log;

import com.joselopez.aprendeapp.models.Cuento;


public class SaveData
{
    public String SaveCuentos(String id, String titulo, String cuerpo,String autor, String videoName, String imagenName)
    {
        /**
         *  For this method is will use the next message for identify the possible events in the
         *  action when save de data in local storage.
         *  save: Message when the data had been saved successfully
         *  fail: Message when the data has problem for to insert in local storage
         */

        String msg;
        try
        {
            Cuento register = new Cuento(id, titulo, cuerpo, autor, imagenName, videoName);
            register.save();
            msg = "save";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Log.e("errorSave", e.toString());
            msg = "fail";
        }
        return msg;
    }

    public String DeleteCuentos(String idCuento)
    {
        String msg;
        try
        {
            //Cuento register = new Cuento(idCuento);
            //register.save();
            msg = "save";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Log.e("errorSave", e.toString());
            msg = "fail";
        }
        return msg;
    }
}