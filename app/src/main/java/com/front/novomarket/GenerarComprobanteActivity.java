package com.front.novomarket;

import   androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.front.novomarket.model.Cliente;
import com.front.novomarket.model.ComprobantePago;
import com.front.novomarket.model.MetodoPago;
import com.front.novomarket.model.Producto;
import com.front.novomarket.utils.API;
import com.front.novomarket.utils.RetrofitClient;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerarComprobanteActivity extends AppCompatActivity {

    EditText etId, fechas,etcantidad,etprecio;
    Spinner spinnerProductos, spinnerMetodoPago, spinnerCliente;
    public List<MetodoPago> metodoPagos;
    public List<Producto> productos;
    public List<Cliente> clientes;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_comprobante);

        etId = findViewById(R.id.etId);
        fechas = findViewById(R.id.fecha);
        spinnerProductos = findViewById(R.id.etProducto);
        spinnerCliente = findViewById(R.id.etCliente);
        spinnerMetodoPago = findViewById(R.id.etMetodoPago);
        etcantidad=findViewById(R.id.etcantidad);
        etprecio=findViewById(R.id.etprecio);

        final Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);

        fechas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        GenerarComprobanteActivity.this, android.R.style.Theme_Holo_Light_Dialog,setListener,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;
                String date=String.format(Locale.getDefault(),"%02d-%02d-%02d",year,month,dayOfMonth);
                fechas.setText(date);
            }
        };

        API api = RetrofitClient.getInstance().getAPI();
        Call<List<Cliente>> call = api.getCliente();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(GenerarComprobanteActivity.this, response.code() + "", Toast.LENGTH_LONG).show();
                    return;
                }
                clientes = response.body();
                Log.e("Fetched Cliente", clientes.get(0).getNomcli());
                //creamos un ArrayAdapter parausar el listado de categorias el cual por defecto se va a agregar al spinner
                ArrayAdapter<Cliente> dataAdapter = new ArrayAdapter<>(GenerarComprobanteActivity.this,
                        android.R.layout.simple_spinner_item, clientes);
                //m??s chiquito el spinner
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerCliente.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Toast.makeText(GenerarComprobanteActivity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });

        Call<List<MetodoPago>> call1 = RetrofitClient.getInstance().getAPI().getMetodoPago();
        call1.enqueue(new Callback<List<MetodoPago>>() {
            @Override
            public void onResponse(Call<List<MetodoPago>> call, Response<List<MetodoPago>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(GenerarComprobanteActivity.this, response.code() + "", Toast.LENGTH_LONG).show();
                    return;
                }
                metodoPagos = response.body();
                Log.e("Fetched MetodoPago", metodoPagos.get(0).getTipopago());
                //creamos un ArrayAdapter parausar el listado de categorias el cual por defecto se va a agregar al spinner
                ArrayAdapter<MetodoPago> dataAdapter = new ArrayAdapter<>(GenerarComprobanteActivity.this,
                        android.R.layout.simple_spinner_item, metodoPagos);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerMetodoPago.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<MetodoPago>> call, Throwable t) {
                Toast.makeText(GenerarComprobanteActivity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });
        Call<List<Producto>> call2 = RetrofitClient.getInstance().getAPI().getProducto();
        call2.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(GenerarComprobanteActivity.this, response.code() + "", Toast.LENGTH_LONG).show();
                    return;
                }
                productos = response.body();
                Log.e("Fetched Productos", productos.get(0).getNomprod());
                //creamos un ArrayAdapter para usar el listado de categorias el cual por defecto se va a agregar al spinner
                ArrayAdapter<Producto> dataAdapter = new ArrayAdapter<>(GenerarComprobanteActivity.this,
                        android.R.layout.simple_spinner_item, productos);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerProductos.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(GenerarComprobanteActivity.this, t.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btnAddProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerarComprobante();
            }
        });
        findViewById(R.id.btnvolverMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GenerarComprobanteActivity.this,MenuActivity.class));
            }
        });

    }



    private void GenerarComprobante() {
        //validaciones de campos
        String valid=etId.getText().toString().trim();
        String valfech=fechas.getText().toString().trim();
        String valpre=etprecio.getText().toString().trim();
        String valcant=etcantidad.getText().toString().trim();
        //validar campos vacios
        if(valid.isEmpty()){
            etId.setError("Id requerido para el comprobante");
            etId.requestFocus();
            return;
        }
        if(valfech.isEmpty()){
            fechas.setError("fecha requerida");
            fechas.requestFocus();
            return;
        }if(valcant.isEmpty()){
            etcantidad.setError("Cantidad requerida");
            etcantidad.requestFocus();
            return;
        }if(valpre.isEmpty()){
            etprecio.setError(("Precio requerido"));
            etprecio.requestFocus();
            return;
        }if(valcant.isEmpty()) {
            etcantidad.setError("Cantidad requerida");
            etcantidad.requestFocus();
            return;
        }
        ////////////////
        ////////////////////////////////////////////////validar caracter
       /* if(valpre.contains(".") || valpre.contains("@") || valpre.contains("$") || valpre.contains("!") || valpre.contains("??") || valpre.contains("#") || valpre.contains(":") || valpre.contains(";") || valpre.contains("&") || valpre.contains("_") || valpre.contains("-") || valpre.contains("(") || valpre.contains(")") || valpre.contains("'") || valpre.contains(",") || valpre.contains("?") || valpre.contains("??") || valpre.contains("+") || valpre.contains("*") || valpre.contains("/") || valpre.contains("=") || valpre.contains("%") || valpre.contains("<") || valpre.contains(">") || valpre.contains("|") || valpre.contains("{") || valpre.contains("}") || valpre.contains("[") || valpre.contains("]")){
            etprecio.setError("El precio no puede contener caracteres espciales");
            etprecio.requestFocus();
            return;
        }
        if(valcant.contains(".") || valcant.contains("@") || valcant.contains("$") || valcant.contains("!") || valcant.contains("??") || valcant.contains("#") || valcant.contains(":") || valcant.contains(";") || valcant.contains("&") || valcant.contains("_") || valcant.contains("-") || valcant.contains("(") || valcant.contains(")") || valcant.contains("'") || valcant.contains(",") || valcant.contains("?") || valcant.contains("??") || valcant.contains("+") || valcant.contains("*") || valcant.contains("/") || valcant.contains("=") || valcant.contains("%") || valcant.contains("<") || valcant.contains(">") || valcant.contains("|") || valcant.contains("{") || valcant.contains("}") || valcant.contains("[") || valcant.contains("]")){
            etcantidad.setError("La cantidad no puede contener caracteres espciales");
            etcantidad.requestFocus();
            return;
        }*/
        //////////////////////////////////////////////////
        //llamada retrofit el cual va a traer relaciones
        long id = Long.parseLong(etId.getText().toString().trim());
        String fecha = fechas.getText().toString();
        int clienteId = ((Cliente) spinnerCliente.getSelectedItem()).getIdcli();
        int productoId = ((Producto) spinnerProductos.getSelectedItem()).getIdprod();
        int metodopagoId = ((MetodoPago) spinnerMetodoPago.getSelectedItem()).getIdmetpago();
        double precio=Double.parseDouble(etprecio.getText().toString());
        int cantidad=Integer.parseInt(etcantidad.getText().toString());

        Call<ResponseBody> call = RetrofitClient.getInstance().getAPI().GenerarComprobante(new ComprobantePago(id, fecha, clienteId, productoId, metodopagoId,precio,cantidad));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String test = response.body().string();
                    Toast.makeText(GenerarComprobanteActivity.this, "Se creo el comprobante", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(GenerarComprobanteActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(GenerarComprobanteActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        etId.getText().clear();
        fechas.getText().clear();
        etprecio.getText().clear();
        etcantidad.getText().clear();
    }
}