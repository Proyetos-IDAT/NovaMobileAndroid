package com.front.novomarket;

import android.content.Intent;
import android.os.Bundle;

import com.front.novomarket.adapter.ProveedorAdapter;
import com.front.novomarket.model.Proveedor;
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

public class ProveedorActivity extends AppCompatActivity {

    List<Proveedor> listaProveedores = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor);

        listView  = findViewById(R.id.listView);

        listarProveedores();
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabregresar = findViewById(R.id.FabRegrePro);
        fabregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProveedorActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProveedorActivity.this, ProveedorEdActivity.class);
                intent.putExtra("ID", "");
                intent.putExtra("NOMBRE PROVEEDOR", "");
                intent.putExtra("RUC", "");
                intent.putExtra("NOMBRE CONTACTO", "");
                intent.putExtra("DIRECCION", "");
                intent.putExtra("TELEFONO", "");
                startActivity(intent);
            }
        });
    }
    public void listarProveedores(){
        Call<List<Proveedor>> call= RetrofitClient.getInstance().getAPI().getProveedor();
        call.enqueue(new Callback<List<Proveedor>>() {
            @Override
            public void onResponse(Call<List<Proveedor>> call, Response<List<Proveedor>> response) {
                if(response.isSuccessful()) {

                    listaProveedores = response.body();

                    listView.setAdapter(new ProveedorAdapter(ProveedorActivity.this,R.layout.activity_proveedor, listaProveedores));
                }
            }

            @Override
            public void onFailure(Call<List<Proveedor>> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }
}