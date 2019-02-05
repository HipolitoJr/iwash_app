package com.example.iwash_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iwash_app.infra.api.APIService;
import com.example.iwash_app.models.TokenAPIModel;
import com.example.iwash_app.models.Usuario;
import com.example.iwash_app.models.UsuarioErrors;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView btnCadastreSe;
    private TextView btnEsqueciSenha;
    private EditText editNomeUsuario;
    private EditText editPassword;
    private ProgressBar progressBarLogin;
    private TextView txtErrosLogin;

    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("iWash");
        setupViews();
    }

    private void setupViews() {
        bindView();
        apiService = new APIService("");

        btnLogin.setOnClickListener( onclick -> {
            progressBarLogin.setVisibility(View.VISIBLE);
            txtErrosLogin.setVisibility(View.GONE);
            logarUsuario(criarUsuario());
        } );
    }

    private void bindView() {
        btnLogin = findViewById(R.id.btn_login);
        btnCadastreSe = findViewById(R.id.btn_cadastre_se);
        btnEsqueciSenha = findViewById(R.id.btn_esqueci_senha);
        editNomeUsuario = findViewById(R.id.edit_login_usuario);
        editPassword = findViewById(R.id.edit_login_senha);
        progressBarLogin = findViewById(R.id.progressBarLogin);
        txtErrosLogin = findViewById(R.id.txtErrosLogin);
    }

    private void logarUsuario(Usuario usuario) {
        Call<TokenAPIModel> call = apiService.tokenEndPoint.login(usuario);

        call.enqueue(new Callback<TokenAPIModel>() {
            @Override
            public void onResponse(Call<TokenAPIModel> call, Response<TokenAPIModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Logado!", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        Gson gson = new Gson();
                        UsuarioErrors usuarioErrors = gson.fromJson(response.errorBody().string(), UsuarioErrors.class);
                        txtErrosLogin.setText(usuarioErrors.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    txtErrosLogin.setVisibility(View.VISIBLE);
                }
                progressBarLogin.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TokenAPIModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                progressBarLogin.setVisibility(View.GONE);
            }
        });
    }

    private Usuario criarUsuario() {
        Usuario usuario = new Usuario(editNomeUsuario.getText().toString(), editPassword.getText().toString());
        return usuario;
    }
}
