package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.front.novomarket.common.Constante;
import com.front.novomarket.common.SharedPreferencesManager;


public class MenuActivity extends AppCompatActivity {

    Button btncliente, btnproveedor, btnmetodopago, btncategoria, btngenerarcomprobante, btnProductos,btnComprobante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String welcomeText =  "Bienvenido usuario: "+ SharedPreferencesManager.getSomeStringValue(Constante.PREF_USERNAME);
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);

        btncliente = findViewById(R.id.btncliente);
        btnproveedor = findViewById(R.id.btnproveedores);
        btnmetodopago = findViewById(R.id.btnmetodopago);
        btncategoria = findViewById(R.id.btncategoria);
        btngenerarcomprobante = findViewById(R.id.btnGenerarComprobante);
        btnProductos = findViewById(R.id.btnProductos);
        btnComprobante=findViewById(R.id.btnListaComprobante);

        btnComprobante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,ComprobanteActivity.class));
            }
        });


        btngenerarcomprobante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, GenerarComprobanteActivity.class));
            }
        });
        btncliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ClienteActivity.class));
            }
        });
        btnproveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MenuActivity.this, ProveedorActivity.class));
            }
        });
        btnmetodopago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, MetodoPagoActivity.class));
            }
        });
        btncategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MenuActivity.this, CategoriaActivity.class));
            }
        });
        btnProductos.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, ProductosActivity.class));
        });
        findViewById(R.id.btnCerrarSesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCerrarSesion();
            }
        });

    }

    private void mostrarCerrarSesion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("Estás seguro de cerrar sesión?").setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferencesManager.setSomeStringValue(Constante.PREF_USERNAME, null);
                finishAffinity();
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

}