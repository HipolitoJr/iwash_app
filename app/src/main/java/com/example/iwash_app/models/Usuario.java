package com.example.iwash_app.models;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id") private long id;
    @SerializedName("username") private String username;
    @SerializedName("email") private String email;
    @SerializedName("password") private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(String email,String username, String password){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.username;
    }
}