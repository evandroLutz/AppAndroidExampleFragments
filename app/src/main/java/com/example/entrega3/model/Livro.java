package com.example.entrega3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Livro implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private String id;
    private String titulo;
    private String autor;
    private  String assunto;

    public Livro(){}

    public Livro(String titulo, String autor, String assunto) {
        this.titulo = titulo;
        this.autor = autor;
        this.assunto = assunto;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

