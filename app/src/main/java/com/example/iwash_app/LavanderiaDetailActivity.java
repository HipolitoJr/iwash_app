package com.example.iwash_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwash_app.infra.api.APIService;
import com.example.iwash_app.infra.utils.ConstantsWash;
import com.example.iwash_app.infra.utils.PreferencesWash;
import com.example.iwash_app.models.Solicitacao;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LavanderiaDetailActivity extends AppCompatActivity {

    private EditText editServico;
    private FloatingActionButton fabSolicitarServico;

    private APIService apiService;
    private PreferencesWash preferencesWash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lavanderia_detail);
        setupViews();
        setTitle("Lavanderia");
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editServico = findViewById(R.id.edit_servico_solicitado);
        fabSolicitarServico = findViewById(R.id.btn_solicitar_servico);
        preferencesWash = new PreferencesWash(this);
        apiService = new APIService(preferencesWash.getSavedString(ConstantsWash.TOKEN));

        fabSolicitarServico.setOnClickListener(onclick -> {
            registrarSolicitacao(criarSolicitacao());
        });
    }

    private void registrarSolicitacao(Solicitacao solicitacao) {
        Call<Solicitacao> solicitacaoCall = apiService.solicitacaoEndPoint.postSolicitacao(solicitacao);

        solicitacaoCall.enqueue(new Callback<Solicitacao>() {
            @Override
            public void onResponse(Call<Solicitacao> call, Response<Solicitacao> response) {
                if (response.isSuccessful()){
                    startActivity(new Intent(LavanderiaDetailActivity.this, SolicitacoesActivity.class));
                }else{
                    try {
                        Toast.makeText(LavanderiaDetailActivity.this, "data: " + solicitacao.getDataSolicitada().toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(LavanderiaDetailActivity.this, "status code: " + response.code() + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Solicitacao> call, Throwable t) {
                Toast.makeText(LavanderiaDetailActivity.this, "erro conexão: " + t.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Solicitacao criarSolicitacao() {
        return new Solicitacao(editServico.getText().toString(), 2, 1);
    }
}
