package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.front.novomarket.adapter.CategoriaAdapter;
import com.front.novomarket.model.Categoria;
import com.front.novomarket.utils.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaActivity extends AppCompatActivity {

    List<Categoria> listaCategorias =new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        //agrega el list view buscando por su id
        listView=findViewById(R.id.listView);

        listarCategorias();
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabregresar=findViewById(R.id.fabRegresar);
        fabregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoriaActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoriaActivity.this, CategoriaEdActivity.class);
                intent.putExtra("ID CATEGORIA"," ");
                intent.putExtra("NOMBRE DE LA CATEGORIA"," ");
                startActivity(intent);
            }
        });
    }

    public void listarCategorias(){
        Call<List<Categoria>> call= RetrofitClient.getInstance().getAPI().getCategoria();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    //la variable carga todos los datos
                    listaCategorias = response.body();
                    //llamando un objeto
                    listView.setAdapter(new CategoriaAdapter(CategoriaActivity.this, R.layout.activity_categoria, listaCategorias));
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }
}