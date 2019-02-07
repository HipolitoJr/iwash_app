package com.example.iwash_app.models;

import com.google.gson.annotations.SerializedName;

public class Consumidor {

    @SerializedName("id") private long id;
    @SerializedName("nome_completo") private String nome_completo;
    @SerializedName("sexo") private String sexo;
    @SerializedName("usuario") private long usuario;


    public Consumidor(String nome_completo, String sexo, long usuario){
        this.nome_completo = nome_completo;
        this.sexo = sexo;
        this.usuario = usuario;
    }

}
