package com.example.iwash_app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iwash_app.infra.adapters.LavanderiasAdapter;
import com.example.iwash_app.infra.adapters.SolicitacoesRVAdapter;
import com.example.iwash_app.infra.api.APIService;
import com.example.iwash_app.infra.utils.ConstantsWash;
import com.example.iwash_app.infra.utils.PreferencesWash;
import com.example.iwash_app.models.Solicitacao;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitacoesActivity extends AppCompatActivity {

    private APIService apiService;
    private PreferencesWash preferencesWash;

    private ProgressBar progressBar;
    private RecyclerView rvSolicitacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacoes);
        setTitle("Solicitações");
        setupViews();
    }

    private void setupViews() {
        preferencesWash = new PreferencesWash(this);
        apiService = new APIService(preferencesWash.getSavedString(ConstantsWash.TOKEN));

        progressBar = findViewById(R.id.progress_solicitacoes);
        rvSolicitacoes = findViewById(R.id.rvSolicitacoes);

        getSolicitacoes();
    }

    private void getSolicitacoes() {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<Solicitacao>> solicitacoesCall = apiService.solicitacaoEndPoint.getSolicitacoes();

        solicitacoesCall.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                if (response.isSuccessful()){
                    exibirSolicitacoes(response.body());
                }else{
                    Toast.makeText(SolicitacoesActivity.this, "Erro " + response.code(), Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                Toast.makeText(SolicitacoesActivity.this, "Falha " + t.getCause().toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void exibirSolicitacoes(List<Solicitacao> solicitacoes) {
        SolicitacoesRVAdapter adapter = new SolicitacoesRVAdapter(this, this, solicitacoes);

        rvSolicitacoes.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvSolicitacoes.setHasFixedSize(true);
        rvSolicitacoes.setLayoutManager(linearLayoutManager);
    }

}
