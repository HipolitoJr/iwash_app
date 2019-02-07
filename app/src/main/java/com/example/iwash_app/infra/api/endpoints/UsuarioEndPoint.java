package com.example.iwash_app.infra.api.endpoints;

import com.example.iwash_app.models.Usuario;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsuarioEndPoint {

    @POST("usuarios/")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

    @GET("usuarios/")
    Call<List<Usuario>> getUsuarios();

}
