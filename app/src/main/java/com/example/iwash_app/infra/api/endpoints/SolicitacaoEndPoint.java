package com.example.iwash_app.infra.api.endpoints;

import com.example.iwash_app.models.Solicitacao;

import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SolicitacaoEndPoint {

    @GET("solicitacoes/")
    Call<ListIterator<Solicitacao>> getSolicitacoes();

    @POST("solicitacoes/")
    Call<Solicitacao> postSolicitacao(@Body Solicitacao solicitacao);

}
