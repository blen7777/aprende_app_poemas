package com.joselopez.aprendeapp.models;

import com.orm.SugarRecord;

public class Cuento extends SugarRecord
{

    String id_cuento, titulo, cuerpo, autor, imagen, video;

    public Cuento()
    {
    }

    public Cuento(String id_cuento, String titulo, String cuerpo, String autor, String imagen, String video) {
        this.id_cuento = id_cuento;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.imagen = imagen;
        this.video = video;
    }



    public String getId_cuento() {
        return id_cuento;
    }

    public void setId_cuento(String id_cuento) {
        this.id_cuento = id_cuento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}