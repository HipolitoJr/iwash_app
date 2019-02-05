package com.example.iwash_app.infra.api.endpoints;

import com.example.iwash_app.models.TokenAPIModel;
import com.example.iwash_app.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TokenEndPoint {

    @POST("token/")
    Call<TokenAPIModel> login(@Body Usuario usuario);

}
