package com.example.entrega3.model;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    static List<Usuario> usuarios = new ArrayList<>();
    private int id;
    private String nome;
    private String endereco;
    private  String dataNasc;
    private String genero;


    public Usuario(){}

    public Usuario(int id, String nome, String endereco, String dataNasc, String genero) {
        this.id =  id;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNasc = dataNasc;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String data) {
        this.dataNasc = data;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setUsuario (Usuario usuario){

        usuarios.add(usuario);

    }
    public static List<Usuario> getUsuarios(){
        return usuarios;
    }

}
