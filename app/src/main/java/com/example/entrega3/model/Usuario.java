package com.example.entrega3.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
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

    public static List<Usuario> inicializaLista(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Evandro Lutz Guimarães","rua Dom Diogo de Souza", "29/03/1991", "Masculino"));
        usuarios.add(new Usuario(2, "Jorge Luis de Oliveira","rua Beija Flor", "12/10/1964", "Masculino"));
        usuarios.add(new Usuario(3, "Delci Lutz Guimarães","rua Beija Flor", "06/01/1963", "Feminino"));
        usuarios.add(new Usuario(4, "Mikaela da Silva Machado","rua Dom Diogo de Souza", "26/12/1994", "Feminino"));

        return usuarios;
    }


}
