package com.example.iwash_app.infra.api.endpoints;

import com.example.iwash_app.models.Lavanderia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface LavanderiaEndPoint {

    @GET("lavanderias/")
    Call<List<Lavanderia>> getLavanderias();

    @GET("lavanderias/{id}/")
    Call<Lavanderia> getLavanderiaDetail(@Path("id") long id);

}
