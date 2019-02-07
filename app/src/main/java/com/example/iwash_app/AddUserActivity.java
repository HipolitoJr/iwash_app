package com.example.iwash_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iwash_app.infra.api.APIService;
import com.example.iwash_app.infra.utils.ConstantsWash;
import com.example.iwash_app.infra.utils.PreferencesWash;
import com.example.iwash_app.models.Consumidor;
import com.example.iwash_app.models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerCadastraSexo;
    private Button btnCadastrar;
    private String generoSelecionado;
    private EditText editCadastraUsuario;
    private EditText editCadastraSenha;
    private EditText editCadastraEmail;
    private EditText editCadastraNomeCompleto;


    private APIService apiService;
    private PreferencesWash preferencesWash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cadastrar usuário");
        setContentView(R.layout.activity_add_user);
        initComponents();
    }

    public void bindViews(){
        btnCadastrar = findViewById(R.id.btn_cadastrar);
        editCadastraEmail = findViewById(R.id.edit_cadastra_email);
        editCadastraNomeCompleto = findViewById(R.id.edit_cadastra_nome_completo);
        editCadastraUsuario = findViewById(R.id.edit_cadastra_usuario);
        editCadastraSenha = findViewById(R.id.edit_cadastra_senha);
    }


    private void initComponents(){
        bindViews();
        initSpinner();
        preferencesWash = new PreferencesWash(this);
        apiService = new APIService(preferencesWash.getSavedString(ConstantsWash.TOKEN));

        btnCadastrar.setOnClickListener( onclick->{
            if (generoSelecionado == "S") {
                startActivity(new Intent(this, AddUserActivity.class));
                Toast.makeText(getApplicationContext(), "Você deve selecionar um gênero!", Toast.LENGTH_LONG).show();
            }
            else{
                postUsuario(new Usuario(editCadastraEmail.getText().toString(),editCadastraUsuario.getText().toString(),
                editCadastraSenha.getText().toString()));
                startActivity(new Intent(this, MainActivity.class));
            }

        } );
    }

    private void initSpinner(){
        spinnerCadastraSexo = findViewById(R.id.spinner_cadastra_sexo);
        spinnerCadastraSexo.setOnItemSelectedListener(this);

        List<String> sexo = new ArrayList<String>();
        sexo.add("Selecione seu gênero:");
        sexo.add("Masculino");
        sexo.add("Feminino");
        sexo.add("Outro");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sexo);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerCadastraSexo.setAdapter(dataAdapter);

    }

    public void postUsuario(Usuario usuario){
        Call<Usuario> call = apiService.usuarioEndPoint.cadastrarUsuario(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                getUsuarios();
                //Toast.makeText(getApplicationContext(),"Salvei usuario", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void postConsumidor(Consumidor consumidor){
        Call<Consumidor> call = apiService.consumidorEndPoint.postConsumidor(consumidor);

        call.enqueue(new Callback<Consumidor>() {
            @Override
            public void onResponse(Call<Consumidor> call, Response<Consumidor> response) {
                //Toast.makeText(getApplicationContext(),"Salvei consumidor", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Consumidor> call, Throwable t) {

            }
        });
    }

    public void getUsuarios(){
        Call<List<Usuario>> call = apiService.usuarioEndPoint.getUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                getUserCadastrado(response.body());
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

            }
        });
    }

    public void getUserCadastrado(List<Usuario> usuariosAll){
        Usuario usuario = usuariosAll.get(usuariosAll.size()-1);
        postConsumidor(new Consumidor(editCadastraNomeCompleto.getText().toString(),generoSelecionado,usuario.getId()));
        Toast.makeText(getApplicationContext(),"Usuario cadastrado com sucesso",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        switch (item) {
            case "Masculino":
                generoSelecionado = "M";
                break;
            case "Feminino":
                generoSelecionado = "F";
                break;
            case "Outro":
                generoSelecionado = "O";
                break;
            case "Selecione seu gênero:":
                generoSelecionado = "S";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
