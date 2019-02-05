package com.example.iwash_app.models;

import com.google.gson.annotations.SerializedName;

public class Lavanderia {

    @SerializedName("id") private long id;
    @SerializedName("cnpj") private String cnpj;
    @SerializedName("nome_fantasia") private String nomeFantasia;
    @SerializedName("usuario") private long usuario;
    @SerializedName("endereco") private String endereco;


    public Lavanderia(String cnpj, String nomeFantasia, long usuario, String endereco) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.usuario = usuario;
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public long getUsuario() {
        return usuario;
    }

    public String getEndereco() {
        return endereco;
    }
}
