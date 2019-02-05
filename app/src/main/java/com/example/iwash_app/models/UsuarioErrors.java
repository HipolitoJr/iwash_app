package com.example.iwash_app.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsuarioErrors {

    @SerializedName("id") private long id;
    @SerializedName("username") private List<String> username;
    @SerializedName("email") private String email;
    @SerializedName("password") private List<String> password;
    @SerializedName("non_field_errors") private List<String> non_field_errors;

    public UsuarioErrors(List<String> username, List<String> password, List<String> non_field_errors) {
        this.username = username;
        this.password = password;
        this.non_field_errors = non_field_errors;
    }

    public String toString(){
        String retorno = "";

        try {
            if (!this.username.isEmpty()){
                retorno += "Usuário: ";
                for (String item: this.username) {
                    retorno += item;
                }
                retorno += "\n";
            }
        } catch (NullPointerException np){
            np.printStackTrace();
        }

        try {
            if (!this.password.isEmpty()){
                retorno += "Senha: ";
                for (String item: this.password) {
                    retorno += item;
                }
                retorno += "\n";
            }
        } catch (NullPointerException np){
            np.printStackTrace();
        }

        try {
            if (!this.non_field_errors.isEmpty()){
                retorno += "Ops.: ";
                for (String item: this.non_field_errors) {
                    retorno += item;
                }
            }
        } catch (NullPointerException np){
            np.printStackTrace();
        }

        return retorno;
    }

}