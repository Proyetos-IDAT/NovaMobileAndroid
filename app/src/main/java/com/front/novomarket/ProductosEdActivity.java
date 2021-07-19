package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.front.novomarket.model.Categoria;
import com.front.novomarket.model.Producto;
import com.front.novomarket.model.Proveedor;
import com.front.novomarket.model.Request.ProductoRequest;
import com.front.novomarket.utils.API;
import com.front.novomarket.utils.RetrofitClient;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosEdActivity extends AppCompatActivity {
    EditText etnom, etfechavenc, etprecio, etstock_min, etstock_act, etfoto;
    List<Categoria> categorias;
    List<Proveedor> proveedores;
    Spinner spinnerCat, spinnerProv;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_ed);

        etnom = findViewById(R.id.etNomProd);
        etfechavenc = findViewById(R.id.etFechaVenc);
        etprecio = findViewById(R.id.etPrecProd);
        etstock_act = findViewById(R.id.etStockAct);
        etstock_min = findViewById(R.id.etStockMin);
        etfoto = findViewById(R.id.etFoto);
        Button btnsave = findViewById(R.id.btnProdSave);
        Button btnRegresar = findViewById(R.id.btnProdVolver);
        spinnerCat = findViewById(R.id.spnCategorias);
        spinnerProv = findViewById(R.id.spnProveedores);

        final Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);

        etfechavenc.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog=new DatePickerDialog(
                    ProductosEdActivity.this, android.R.style.Theme_Holo_Light_Dialog,setListener,year,month,day
            );
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=String.format(Locale.getDefault(),"%02d-%02d-%02d",year,month,dayOfMonth);
                etfechavenc.setText(date);
            }
        };

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

        String n= etnom.getText().toString();
        String fechavalid = etfechavenc.getText().toString();
        String fotovalid=etfoto.getText().toString();
        String preciovalid = etprecio.getText().toString();
        String stockminvalid = etstock_min.getText().toString();
        String stockactvalid = etstock_act.getText().toString();

        if (n.isEmpty()) {
            etnom.setError("Nombre del producto requerido");
            etnom.requestFocus();
            return;
        }if (preciovalid.isEmpty()){
            etprecio.setError("Precio del producto requerido");
            etprecio.requestFocus();
            return;
        }if(fotovalid.isEmpty()){
            etfoto.getText().toString();
            etfoto.getText().toString();
        }
        if (fechavalid.isEmpty()) {
            etfechavenc.setError("Fecha de vencimiento del producto requerido");
            etfechavenc.requestFocus();
            return;
        }if (stockminvalid.isEmpty()){
            etstock_min.setError("Stock mínimo del producto requerido");
            etstock_min.requestFocus();
            return;
        }if (stockactvalid.isEmpty()){
            etstock_act.setError("Stock mínimo del producto requerido");
            etstock_act.requestFocus();
            return;
        }
        //validación de numero
        if(n.contains("0") || n.contains("1") || n.contains("2") || n.contains("3") ||
                n.contains("4") || n.contains("5") || n.contains("6") || n.contains("7") || n.contains("8") || n.contains("9")){
            etnom.setError("El nombre del producto no puede contener números");
            etnom.requestFocus();
            return;
        }if(n.contains(".") || n.contains("@") || n.contains("$") || n.contains("!") || n.contains("¡") || n.contains("#") || n.contains(":") || n.contains(";") || n.contains("&") || n.contains("_") || n.contains("-") || n.contains("(") || n.contains(")") || n.contains("'") || n.contains(",") || n.contains("?") || n.contains("¿") || n.contains("+") || n.contains("*") || n.contains("/") || n.contains("=") || n.contains("%") || n.contains("<") || n.contains(">") || n.contains("|") || n.contains("{") || n.contains("}") || n.contains("[") || n.contains("]")){
            etnom.setError("El nombre del producto no puede contener caracteres espciales");
            etnom.requestFocus();
            return;
        }

        int idprov = ((Proveedor) spinnerProv.getSelectedItem()).getIdprove();
        int idcat = ((Categoria) spinnerCat.getSelectedItem()).getIdcat();
        String nombre = etnom.getText().toString();
        String fechav = etfechavenc.getText().toString();
        String foto = etfoto.getText().toString();
        Double precio = Double.parseDouble(etprecio.getText().toString());
        int stock_act = Integer.parseInt(etstock_act.getText().toString());
        int stock_min = Integer.parseInt(etstock_min.getText().toString());

        Categoria catReque = new Categoria(idcat, null);
        Proveedor provReque = new Proveedor(idprov, null, null,
                null, null, null);
        ProductoRequest pr = new ProductoRequest(nombre, fechav, precio
                ,stock_min, stock_act, foto, catReque, provReque);
        Call<Producto> call = RetrofitClient.getInstance().getAPI().RegistrarProducto(pr);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Toast.makeText(ProductosEdActivity.this, "prod registrado", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ProductosEdActivity.this, ProductosActivity.class));
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

            }
        });
    }
}