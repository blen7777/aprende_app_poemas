package com.joselopez.aprendeapp.models;

import com.orm.SugarRecord;

public class Adivinanza extends SugarRecord
{
    private String id_adivinanza;
    private String cuerpo;
    private String categoria;
    private String respuesta;

    public Adivinanza() {
    }

    public Adivinanza(String id_adivinanza, String cuerpo, String categoria, String respuesta) {
        this.id_adivinanza = id_adivinanza;
        this.cuerpo = cuerpo;
        this.categoria = categoria;
        this.respuesta = respuesta;
    }



    public String getId_adivinanza() {
        return id_adivinanza;
    }

    public void setId_adivinanza(String id_adivinanza) {
        this.id_adivinanza = id_adivinanza;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}