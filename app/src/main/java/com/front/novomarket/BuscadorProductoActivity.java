package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.front.novomarket.adapter.BuscadorProductoAdapter;
import com.front.novomarket.model.Producto;
import com.front.novomarket.utils.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscadorProductoActivity extends AppCompatActivity {

    EditText etBuscador;
    RecyclerView rvLista;
    BuscadorProductoAdapter adaptador;
    List<Producto> listaProductos;
    FloatingActionButton floatingRegresarBuscProduc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_producto);

        floatingRegresarBuscProduc=findViewById(R.id.floatingRegresarBuscProduc);
        floatingRegresarBuscProduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuscadorProductoActivity.this,MenuActivity.class));
            }
        });

        etBuscador=findViewById(R.id.etBuscador);
        etBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filtrar(s.toString());
            }
        });
        rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new GridLayoutManager(this, 1));

        listarProducto();
    }

    private void listarProducto() {
        Call<List<Producto>> call = RetrofitClient.getInstance().getAPI().getProducto();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    listaProductos = response.body();
                    adaptador = new BuscadorProductoAdapter(BuscadorProductoActivity.this, listaProductos);
                    rvLista.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }

    private void filtrar(String texto) {

        ArrayList<Producto> filtrarLista = new ArrayList<>();

        for(Producto producto : listaProductos) {
            if(producto.getNomprod().toLowerCase().contains(texto.toLowerCase())) {
                filtrarLista.add(producto);
            }
        }

        adaptador.filtrar(filtrarLista);
    }
}