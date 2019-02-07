package com.example.iwash_app.infra.api.endpoints;

import com.example.iwash_app.models.Consumidor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ConsumidorEndPoint {


    @POST("consumidores/")
    Call<Consumidor> postConsumidor(@Body Consumidor consumidor);
}
