package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.front.novomarket.adapter.ComprobanteAdapter;
import com.front.novomarket.model.Cliente;
import com.front.novomarket.model.ComprobantePago;
import com.front.novomarket.utils.OnItemClickListener;
import com.front.novomarket.utils.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComprobanteActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView revComprobante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante);
        listarComprobante();

        FloatingActionButton regresar=findViewById(R.id.FloatingButtonRegresarMenu);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComprobanteActivity.this, MenuActivity.class));
            }
        });
    }

    private void listarComprobante() {

        Call<List<ComprobantePago>>call=RetrofitClient.getInstance().getAPI().getComprobante();
        call.enqueue(new Callback<List<ComprobantePago>>() {
            @Override
            public void onResponse(Call<List<ComprobantePago>> call, Response<List<ComprobantePago>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ComprobanteActivity.this, response.code() + "", Toast.LENGTH_LONG).show();
                    return;
                }
                List<ComprobantePago> comprobantePagos = response.body();
                revComprobante = findViewById(R.id.revComprobante);
                revComprobante.setLayoutManager(new GridLayoutManager(ComprobanteActivity.this,1));
                revComprobante.setAdapter(new ComprobanteAdapter(ComprobanteActivity.this, comprobantePagos, ComprobanteActivity.this));
            }

            @Override
            public void onFailure(Call<List<ComprobantePago>> call, Throwable t) {
                Toast.makeText(ComprobanteActivity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(ComprobantePago c) {

    }
}