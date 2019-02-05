package com.example.iwash_app.infra.api.endpoints;

import com.example.iwash_app.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioEndPoint {

    @POST("usuario/")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

}
