package com.example.iwash_app.models;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Solicitacao {


    @SerializedName("id") private long id;
    @SerializedName("servico_solicitado") private String servicoSolicitado;
    @SerializedName("consumidor") private long consumidor;
    @SerializedName("lavanderia") private long lavanderia;
    @SerializedName("status") private String status;
    @SerializedName("data_solicitada") private String dataSolicitada;

    public Solicitacao(String servicoSolicitado, long consumidor, long lavanderia) {
        this.servicoSolicitado = servicoSolicitado;
        this.consumidor = consumidor;
        this.lavanderia = lavanderia;

        this.dataSolicitada = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public String getDataSolicitada() {
        return dataSolicitada;
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

    public String getStatus() {
        return status;
    }

    public long getId() {
        return this.id;
    }


}
