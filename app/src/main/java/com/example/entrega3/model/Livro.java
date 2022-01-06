package com.example.entrega3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Livro implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private String titulo;
    private String autor;
    private  String assunto;

    public Livro(){}

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
