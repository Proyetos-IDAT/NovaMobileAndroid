package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.front.novomarket.adapter.MetodoPagoAdapter;
import com.front.novomarket.adapter.ProveedorAdapter;
import com.front.novomarket.model.MetodoPago;
import com.front.novomarket.model.Proveedor;
import com.front.novomarket.utils.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetodoPagoActivity extends AppCompatActivity {

    List<MetodoPago> listaMetodoPagos = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago);

        listView  = findViewById(R.id.listView);

        listarMetodoPagos();
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabregresar = findViewById(R.id.FabRegrePro);
        fabregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetodoPagoActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MetodoPagoActivity.this, MetodoPagoEdActivity.class);
                intent.putExtra("ID", " ");
                intent.putExtra("TIPO DE PAGO", " ");
                startActivity(intent);
            }
        });
    }

    public void listarMetodoPagos(){
        Call<List<MetodoPago>> call= RetrofitClient.getInstance().getAPI().getMetodoPago();
        call.enqueue(new Callback<List<MetodoPago>>() {
            @Override
            public void onResponse(Call<List<MetodoPago>> call, Response<List<MetodoPago>> response) {
                if(response.isSuccessful()) {
                    listaMetodoPagos = response.body();
                    listView.setAdapter(new MetodoPagoAdapter(MetodoPagoActivity.this,R.layout.activity_metodo_pago, listaMetodoPagos));
                }
            }

            @Override
            public void onFailure(Call<List<MetodoPago>> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }
}