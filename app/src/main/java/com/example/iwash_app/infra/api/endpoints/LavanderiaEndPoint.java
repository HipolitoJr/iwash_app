package com.example.iwash_app.infra.api.endpoints;

import com.example.iwash_app.models.Lavanderia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LavanderiaEndPoint {

    @GET("lavanderias/")
    Call<List<Lavanderia>> getLavanderias();

}
