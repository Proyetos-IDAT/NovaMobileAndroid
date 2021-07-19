package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.front.novomarket.adapter.ProductoAdapter;
import com.front.novomarket.model.Producto;
import com.front.novomarket.utils.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosActivity extends AppCompatActivity {

    List<Producto> listaProductos = new ArrayList<>();
    ListView listView;
    EditText etFiltro;
    ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);


        listView = findViewById(R.id.listView);
        etFiltro = findViewById(R.id.etFiltroProd);

        listarProductos();

        Call<List<Producto>> call = RetrofitClient.getInstance().getAPI().getProducto();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    listaProductos = response.body();
                    adapter = new ProductoAdapter(ProductosActivity.this, R.layout.activity_productos, listaProductos);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });

        etFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabRegresar = findViewById(R.id.fabRegresar);
        fabRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductosActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductosActivity.this, ProductosEdActivity.class);
                intent.putExtra("ID PRODUCTO", " ");
                intent.putExtra("NOMBRE DEL PRODUCTO", " ");
                startActivity(intent);
            }
        });
    }

    public void listarProductos() {

    }
}