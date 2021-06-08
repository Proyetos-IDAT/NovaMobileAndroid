package com.front.novomarket;

import android.content.Intent;
import android.os.Bundle;


import com.front.novomarket.adapter.ClienteAdapter;
import com.front.novomarket.model.Cliente;

import com.front.novomarket.utils.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteActivity extends AppCompatActivity {

    List<Cliente> listaClientes =new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        //agrega el list view buscando por su id
        listView=findViewById(R.id.listView);

        listarClientes();
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabregresar=findViewById(R.id.fabRegresar);
        fabregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClienteActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ClienteActivity.this, ClienteEdActivity.class);
                intent.putExtra("ID"," ");
                intent.putExtra("NOMBRE"," ");
                intent.putExtra("APELLIDO"," ");
                intent.putExtra("NUMERO"," ");
                startActivity(intent);
            }
        });
    }
    public void listarClientes(){
        Call<List<Cliente>>call= RetrofitClient.getInstance().getAPI().getCliente();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if(response.isSuccessful()) {
                    //la variable carga todos los datos
                    listaClientes = response.body();
                    //llamando un objeto
                    listView.setAdapter(new ClienteAdapter(ClienteActivity.this,R.layout.activity_cliente, listaClientes));
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }
}