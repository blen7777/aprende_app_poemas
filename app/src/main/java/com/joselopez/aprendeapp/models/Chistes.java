package com.joselopez.aprendeapp.models;

import com.orm.SugarRecord;

public class Chistes extends SugarRecord
{
    String titulo, categoria, cuerpo, id_chiste, likes;

    public Chistes() {
    }

    public Chistes(String titulo, String categoria, String cuerpo, String id_chiste, String likes) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.cuerpo = cuerpo;
        this.id_chiste = id_chiste;
        this.likes = likes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getId_chiste() {
        return id_chiste;
    }

    public void setId_chiste(String id_chiste) {
        this.id_chiste = id_chiste;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
