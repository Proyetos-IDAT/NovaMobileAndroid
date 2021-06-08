package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.front.novomarket.model.MetodoPago;

public class MenuActivity extends AppCompatActivity {

    Button btncliente,btnproveedor,btnmetodopago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        String welcomeText = "Welcome " + getIntent().getStringExtra("username") + "!";
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);
        btncliente=findViewById(R.id.btncliente);
        btnproveedor=findViewById(R.id.btnproveedores);
        btnmetodopago=findViewById(R.id.btnmetodopago);
        btncliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,ClienteActivity.class));
            }
        });
        btnproveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,ProveedorActivity.class));
            }
        });
        btnmetodopago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,MetodoPagoActivity.class));
            }
        });
        findViewById(R.id.btnCerrarSesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,LoginActivity.class));
                Toast.makeText(MenuActivity.this,"Finalizó sesión",Toast.LENGTH_SHORT).show();
            }
        });
    }
}