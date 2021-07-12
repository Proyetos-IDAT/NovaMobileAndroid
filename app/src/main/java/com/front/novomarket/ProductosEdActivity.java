package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.front.novomarket.model.Categoria;
import com.front.novomarket.model.Producto;
import com.front.novomarket.model.Proveedor;
import com.front.novomarket.model.Request.ProductoRequest;
import com.front.novomarket.utils.API;
import com.front.novomarket.utils.RetrofitClient;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosEdActivity extends AppCompatActivity {
    EditText etnom, etfechavenc, etprecio, etstock_min, etstock_act;
    List<Categoria> categorias;
    List<Proveedor> proveedores;
    Spinner spinnerCat, spinnerProv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_ed);

        etnom = findViewById(R.id.etNomProd);
        etfechavenc = findViewById(R.id.etFechaVenc);
        etprecio = findViewById(R.id.etPrecProd);
        etstock_act = findViewById(R.id.etStockAct);
        etstock_min = findViewById(R.id.etStockMin);
        Button btnsave = findViewById(R.id.btnProdSave);
        Button btnRegresar = findViewById(R.id.btnProdVolver);
        Button btnEliminar = findViewById(R.id.btnProdEliminar);
        spinnerCat = findViewById(R.id.spnCategorias);
        spinnerProv = findViewById(R.id.spnProveedores);


        API api = RetrofitClient.getInstance().getAPI();

        Call<List<Categoria>> call = api.getCategoria();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProductosEdActivity.this, response.code() + "", Toast.LENGTH_LONG).show();
                    return;
                }
                categorias = response.body();
                ArrayAdapter<Categoria> dataAdapter = new ArrayAdapter<>(ProductosEdActivity.this,
                        android.R.layout.simple_spinner_item, categorias);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCat.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(ProductosEdActivity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });

        Call<List<Proveedor>> call2 = api.getProveedor();
        call2.enqueue(new Callback<List<Proveedor>>() {
            @Override
            public void onResponse(Call<List<Proveedor>> call, Response<List<Proveedor>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProductosEdActivity.this, response.code() + "", Toast.LENGTH_LONG).show();
                    return;
                }
                proveedores = response.body();
                ArrayAdapter<Proveedor> dataAdapter = new ArrayAdapter<>(ProductosEdActivity.this,
                        android.R.layout.simple_spinner_item, proveedores);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProv.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Proveedor>> call, Throwable t) {
                Toast.makeText(ProductosEdActivity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });
        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(ProductosEdActivity.this, ProductosActivity.class));
        });
        btnsave.setOnClickListener(v -> {
            agregarProducto();
        });
    }

    public void agregarProducto() {
        int idprov = ((Proveedor) spinnerProv.getSelectedItem()).getIdprove();
        int idcat = ((Categoria) spinnerCat.getSelectedItem()).getIdcat();
        String nombre = etnom.getText().toString();
        String fechav = etfechavenc.getText().toString();
        Double precio = Double.parseDouble(etprecio.getText().toString());
        int stock_act = Integer.parseInt(etstock_act.getText().toString());
        int stock_min = Integer.parseInt(etstock_min.getText().toString());

        Categoria catReque = new Categoria(idcat, null);
        Proveedor provReque = new Proveedor(idprov, null, null,
                null, null, null);
        ProductoRequest pr = new ProductoRequest(nombre, fechav, precio
                , stock_act, stock_min, catReque, provReque);
        Call<Producto> call = RetrofitClient.getInstance().getAPI().RegistrarProducto(pr);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Toast.makeText(ProductosEdActivity.this,"prod registrado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });
    }
}