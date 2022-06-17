package com.joselopez.aprendeapp.models;

public class Sugerencias
{
    public String titulo;
    public String descripcion;

    public Sugerencias() {
    }

    public Sugerencias(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
