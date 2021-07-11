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


public class MenuActivity extends AppCompatActivity {
    Button btncliente, btnproveedor, btnmetodopago, btncategoria, btnProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //String welcomeText = "Welcome " + getIntent().getStringExtra("username") + "!";
        String welcomeText = "Bienvenido";
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);
        btncliente = findViewById(R.id.btncliente);
        btnproveedor = findViewById(R.id.btnproveedores);
        btnmetodopago = findViewById(R.id.btnmetodopago);
        btncategoria = findViewById(R.id.btncategoria);
        btnProductos = findViewById(R.id.btnProductos);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setTitle("Cerrar Sesión");
                builder.setMessage("Estás seguro de cerrar sesión?").setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                /*startActivity(new Intent(MenuActivity.this,LoginActivity.class));
                Toast.makeText(MenuActivity.this,"Finalizó sesión",Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}