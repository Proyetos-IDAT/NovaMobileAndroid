package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.front.novomarket.model.MetodoPago;
import com.front.novomarket.model.Proveedor;
import com.front.novomarket.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetodoPagoEdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago_ed);

        TextView idmetpago = findViewById(R.id.IdMetPago);
        EditText txtMetpago = findViewById(R.id.txtIdMetPago);
        TextView tipopago = findViewById(R.id.TipoPago);
        final EditText txtTipopago = findViewById(R.id.txtTipoPago);

        Button btnSavemetpag = findViewById(R.id.btnSavemetpag);
        Button btnVolvermetpag = findViewById(R.id.btnVolvermetpag);
        Button btnEliminarmetpag = findViewById(R.id.btnEliminarmetpag);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("ID");
        String tipopag = bundle.getString("TIPO DE PAGO");

        txtMetpago.setText(id);
        txtTipopago.setText(tipopag);

        if (id.trim().length() == 0 || id.equals("")) {
            idmetpago.setVisibility(View.INVISIBLE);
            txtMetpago.setVisibility(View.INVISIBLE);
        }
        btnSavemetpag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipopago= txtTipopago.getText().toString().trim();
                if (tipopago.isEmpty()) {
                    txtTipopago.setError("Se requerie colocar el tipo de pago");
                    txtTipopago.requestFocus();
                    return;
                }if(tipopago.contains("0") || tipopago.contains("1") ||
                        tipopago.contains("2") || tipopago.contains("3") || tipopago.contains("4") ||
                        tipopago.contains("5") || tipopago.contains("6") || tipopago.contains("7") || tipopago.contains("8") ||
                        tipopago.contains("9")){
                    txtTipopago.setError("El tipo de pago no puede contener números");
                    txtTipopago.requestFocus();
                    return;
                }
                if(tipopago.contains(".") || tipopago.contains("@") || tipopago.contains("$") || tipopago.contains("!") || tipopago.contains("¡") || tipopago.contains("#") || tipopago.contains(":") || tipopago.contains(";") || tipopago.contains("&") || tipopago.contains("_") || tipopago.contains("-") || tipopago.contains("(") || tipopago.contains(")") || tipopago.contains("'") || tipopago.contains(",") || tipopago.contains("?") || tipopago.contains("¿") || tipopago.contains("+") || tipopago.contains("*") || tipopago.contains("/") || tipopago.contains("=") || tipopago.contains("%") || tipopago.contains("<") || tipopago.contains(">") || tipopago.contains("|") || tipopago.contains("{") || tipopago.contains("}") || tipopago.contains("[") || tipopago.contains("]")){
                    txtTipopago.setError("El nombre de la categoria no puede contener caracteres espciales");
                    txtTipopago.requestFocus();
                    return;
                }
                MetodoPago mp=new MetodoPago();
                //hemos seteado los nombres y apellidos enviandolo al metodo llevando después al webservice
                mp.setTipopago(txtTipopago.getText().toString());
                //colocar condición para cuando guarde y actualize
                if(id.trim().length()==0|| id.equals("")){
                    addMetodoPago(mp);
                    Intent intent =new Intent(MetodoPagoEdActivity.this,MetodoPagoActivity.class);
                    startActivity(intent);
                }else{
                    //Si la condición no es guardar se va a actualizar los datos de persona llamandolo por su ai
                    //En el cual luego se va de modificar se va a ir al activitu main para poder ir a la ubicadción
                    updateMetodoPago(mp,Integer.valueOf(id));
                    Intent intent =new Intent(MetodoPagoEdActivity.this,MetodoPagoActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnEliminarmetpag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método que nos permite transformar en entero
                deleteMetodoPago(Integer.valueOf(id));
                Intent intent =new Intent(MetodoPagoEdActivity.this,MetodoPagoActivity.class);
                startActivity(intent);
            }
        });

        btnVolvermetpag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MetodoPagoEdActivity.this,MetodoPagoActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addMetodoPago(MetodoPago mp){
        Call<MetodoPago> call= RetrofitClient.getInstance().getAPI().addMetodoPago(mp);
        call.enqueue(new Callback<MetodoPago>() {
            @Override
            public void onResponse(Call<MetodoPago> call, Response<MetodoPago> response) {
                if(response!=null){
                    Toast.makeText(MetodoPagoEdActivity.this,"Se agregó con éxito el Método de Pago",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MetodoPago> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent=new Intent(MetodoPagoEdActivity.this,MetodoPagoActivity.class);
        startActivity(intent);
    }
    public void updateMetodoPago(MetodoPago metodopago, int id){
        Call<MetodoPago>call=RetrofitClient.getInstance().getAPI().updateMetodoPago(metodopago,id);
        call.enqueue(new Callback<MetodoPago>() {
            @Override
            public void onResponse(Call<MetodoPago> call, Response<MetodoPago> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MetodoPagoEdActivity.this,"Se actualizó con éxito el Método de Pago",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MetodoPago> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent=new Intent(MetodoPagoEdActivity.this,MetodoPagoActivity.class);
        startActivity(intent);
    }
    public void deleteMetodoPago(int id){
        Call<MetodoPago>call=RetrofitClient.getInstance().getAPI().deleteMetodoPago(id);
        call.enqueue(new Callback<MetodoPago>() {
            @Override
            public void onResponse(Call<MetodoPago> call, Response<MetodoPago> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MetodoPagoEdActivity.this,"Se eliminó con éxito el Método de Pago",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MetodoPago> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
        Intent intent=new Intent(MetodoPagoEdActivity.this,MetodoPagoActivity.class);
        startActivity(intent);
    }
}