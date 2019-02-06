package com.example.iwash_app.models;

import com.google.gson.annotations.SerializedName;

public class Solicitacao {


    @SerializedName("id") private long id;
    @SerializedName("servico_solicitado") private String servicoSolicitado;
    @SerializedName("consumidor") private long consumidor;
    @SerializedName("lavanderia") private long lavanderia;

    public Solicitacao(String servicoSolicitado, long consumidor, long lavanderia) {
        this.servicoSolicitado = servicoSolicitado;
        this.consumidor = consumidor;
        this.lavanderia = lavanderia;
    }

    public String getServicoSolicitado() {
        return servicoSolicitado;
    }

    public long getConsumidor() {
        return consumidor;
    }

    public long getLavanderia() {
        return lavanderia;
    }

    public long getId() {
        return this.id;
    }


}
