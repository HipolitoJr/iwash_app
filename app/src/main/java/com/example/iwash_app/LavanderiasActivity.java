package com.example.iwash_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iwash_app.infra.adapters.LavanderiasAdapter;
import com.example.iwash_app.infra.api.APIService;
import com.example.iwash_app.infra.utils.ConstantsWash;
import com.example.iwash_app.infra.utils.PreferencesWash;
import com.example.iwash_app.models.Lavanderia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LavanderiasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ProgressBar progressLavanderias;
    private RecyclerView rvLavanderias;

    private APIService apiService;
    private PreferencesWash preferencesWash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lavanderias);
        setupViews();
        setTitle("Lavanderias");
    }

    private void setupViews() {
        bindView();
        setSupportActionBar(toolbar);
        initNavBar();
        preferencesWash = new PreferencesWash(this);
        apiService = new APIService(preferencesWash.getSavedString(ConstantsWash.TOKEN));
    }

    public void bindView(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressLavanderias = findViewById(R.id.progress_lavanderias);
        rvLavanderias = findViewById(R.id.rvLavanderias);
    }

    private void getLavanderias() {
        Call<List<Lavanderia>> lavanderiasCall = apiService.lavanderiaEndPoint.getLavanderias();

        lavanderiasCall.enqueue(new Callback<List<Lavanderia>>() {
            @Override
            public void onResponse(Call<List<Lavanderia>> call, Response<List<Lavanderia>> response) {
                if (response.isSuccessful()){
                    exibirLavanderias(response.body());
                }else{
                    Toast.makeText(LavanderiasActivity.this, "Erro " + response.code(), Toast.LENGTH_SHORT).show();
                }
                progressLavanderias.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Lavanderia>> call, Throwable t) {
                Toast.makeText(LavanderiasActivity.this, "Conexão Falha: " + t.getCause(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exibirLavanderias(List<Lavanderia> lavanderias) {
        LavanderiasAdapter lavanderiasAdapter = new LavanderiasAdapter(this, this, lavanderias);

        rvLavanderias.setAdapter(lavanderiasAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvLavanderias.setHasFixedSize(true);
        rvLavanderias.setLayoutManager(linearLayoutManager);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_lavanderias) {
            progressLavanderias.setVisibility(View.VISIBLE);
            getLavanderias();
        } else if (id == R.id.nav_pedidos) {

        }
        else if(id == R.id.nav_sair){
            logout();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initNavBar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void logout() {
        preferencesWash.limpar();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
