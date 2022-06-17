package com.joselopez.aprendeapp.models;

import com.orm.SugarRecord;

public class Poemas extends SugarRecord
{
    String autor,cuerpo,id_poema,imagen,titulo,video,categoria;
    Integer likes;

    public Poemas() {
    }

    public Poemas(String autor, String cuerpo, String id_poema, String imagen, String titulo, String video, String categoria, Integer likes) {
        this.autor = autor;
        this.cuerpo = cuerpo;
        this.id_poema = id_poema;
        this.imagen = imagen;
        this.titulo = titulo;
        this.video = video;
        this.categoria = categoria;
        this.likes = likes;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getId_poema() {
        return id_poema;
    }

    public void setId_poema(String id_poema) {
        this.id_poema = id_poema;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
