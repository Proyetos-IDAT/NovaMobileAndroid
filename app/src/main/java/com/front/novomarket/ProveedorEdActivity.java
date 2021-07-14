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

import com.front.novomarket.model.Proveedor;
import com.front.novomarket.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProveedorEdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor_ed);

        TextView idprove = findViewById(R.id.IdProve);
        EditText txtIdprove = findViewById(R.id.txtIdProve);
        TextView nomprove = findViewById(R.id.Nomprove);
        final EditText txtNomprove = findViewById(R.id.txtNomprove);
        TextView ruc = findViewById(R.id.Ruc);
        final EditText txtRuc = findViewById(R.id.txtRuc);
        TextView nomcontacto = findViewById(R.id.Nomcontacto);
        final EditText txtNomcontacto = findViewById(R.id.txtNomcontacto);
        TextView direccion = findViewById(R.id.Direccion);
        final EditText txtDireccion = findViewById(R.id.txtDireccion);
        TextView telefono = findViewById(R.id.Telefono);
        final EditText txtTelefono = findViewById(R.id.txtTelefono);

        Button btnSavepro = findViewById(R.id.btnSaveprove);
        Button btnVolverpro = findViewById(R.id.btnVolverprove);
        Button btnEliminarpro = findViewById(R.id.btnEliminarprove);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("ID");
        String nompr = bundle.getString("NOMBRE PROVEEDOR");
        String rucs = bundle.getString("RUC");
        String nomco = bundle.getString("NOMBRE CONTACTO");
        String dir = bundle.getString("DIRECCION");
        String tel = bundle.getString("TELEFONO");

        txtIdprove.setText(id);
        txtNomprove.setText(nompr);
        txtRuc.setText(rucs);
        txtNomcontacto.setText(nomco);
        txtDireccion.setText(dir);
        txtTelefono.setText(tel);

        if (id.trim().length() == 0 || id.equals("")) {
            idprove.setVisibility(View.INVISIBLE);
            txtIdprove.setVisibility(View.INVISIBLE);
        }
        btnSavepro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nompro= txtNomprove.getText().toString().trim();
                String ruc= txtRuc.getText().toString().trim();
                String contacto= txtNomcontacto.getText().toString().trim();
                String direccion= txtDireccion.getText().toString().trim();
                String telefono= txtTelefono.getText().toString().trim();
                if (nompro.isEmpty()) {
                    txtNomprove.setError("Nombre proveedor requerido");
                    txtNomprove.requestFocus();
                    return;
                }if(ruc.isEmpty()) {
                    txtRuc.setError("Ruc requerido");
                    txtRuc.requestFocus();
                    return;
                }if(contacto.isEmpty()) {
                    txtNomcontacto.setError("Nombre del contacto requerido");
                    txtNomcontacto.requestFocus();
                    return;
                }if(direccion.isEmpty()) {
                    txtDireccion.setError("Direccion requerido");
                    txtDireccion.requestFocus();
                    return;
                }if(telefono.isEmpty()) {
                    txtTelefono.setError("teléfono requerido");
                    txtTelefono.requestFocus();
                    return;
                }
                //length para los espacios requeridos
                if(telefono.length() != 9){
                    txtTelefono.setError("El número de teléfono debe poseer 9 digitos");
                    txtTelefono.requestFocus();
                    return;
                }if(ruc.length() != 8) {
                    txtRuc.setError("El Ruc debe poseer 8 digitos");
                    txtRuc.requestFocus();
                    return;
                }
                /////////////////////////////////////////no ingresar números
                if(nompro.contains("0") || nompro.contains("1") || nompro.contains("2") || nompro.contains("3") ||
                        nompro.contains("4") || nompro.contains("5") || nompro.contains("6") || nompro.contains("7") ||
                        nompro.contains("8") || nompro.contains("9")){
                    txtNomprove.setError("El nombre del proveedor no puede contener números");
                    txtNomprove.requestFocus();
                    return;
                }if(contacto.contains("0") || contacto.contains("1") || contacto.contains("2") || contacto.contains("3") || contacto.contains("4") || contacto.contains("5") || contacto.contains("6") || contacto.contains("7") || contacto.contains("8") || contacto.contains("9")){
                    txtNomcontacto.setError("El nombre del contacto del proveedor no puede contener números");
                    txtNomcontacto.requestFocus();
                    return;
                }
                //////////////////////////////////////////////////////////




                Proveedor pr=new Proveedor();
                //hemos seteado los nombres y apellidos enviandolo al metodo llevando después al webservice
                pr.setNomprove(txtNomprove.getText().toString());
                pr.setRuc(txtRuc.getText().toString());
                pr.setNomcontacto(txtNomcontacto.getText().toString());
                pr.setDireccion(txtDireccion.getText().toString());
                pr.setTelefono(txtTelefono.getText().toString());
                //colocar condición para cuando guarde y actualize
                if(id.trim().length()==0|| id.equals("")){
                    addProveedor(pr);
                    Intent intent =new Intent(ProveedorEdActivity.this,ProveedorActivity.class);
                    startActivity(intent);
                }else{
                    //Si la condición no es guardar se va a actualizar los datos de persona llamandolo por su ai
                    //En el cual luego se va de modificar se va a ir al activitu main para poder ir a la ubicadción
                    updateProveedor(pr,Integer.valueOf(id) );
                    Intent intent =new Intent(ProveedorEdActivity.this,ProveedorActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnEliminarpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método que nos permite transformar en entero
                deleteProveedor(Integer.valueOf(id));
                Intent intent =new Intent(ProveedorEdActivity.this,ProveedorActivity.class);
                startActivity(intent);
            }
        });

        btnVolverpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProveedorEdActivity.this,ProveedorActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addProveedor(Proveedor pr){
        Call<Proveedor> call= RetrofitClient.getInstance().getAPI().addProveedor(pr);
        call.enqueue(new Callback<Proveedor>() {
            @Override
            public void onResponse(Call<Proveedor> call, Response<Proveedor> response) {
                if(response!=null){
                    Toast.makeText(ProveedorEdActivity.this,"Se agregó con éxito el Proveedor",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Proveedor> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent=new Intent(ProveedorEdActivity.this,ProveedorActivity.class);
        startActivity(intent);
    }
    public void updateProveedor(Proveedor proveedor, int id){
        Call<Proveedor>call=RetrofitClient.getInstance().getAPI().updateProveedor(proveedor,id);
        call.enqueue(new Callback<Proveedor>() {
            @Override
            public void onResponse(Call<Proveedor> call, Response<Proveedor> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProveedorEdActivity.this,"Se actualizó con éxito el Proveedor",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Proveedor> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent=new Intent(ProveedorEdActivity.this,ProveedorActivity.class);
        startActivity(intent);
    }
    public void deleteProveedor(int id){
        Call<Proveedor>call=RetrofitClient.getInstance().getAPI().deleteProveedor(id);
        call.enqueue(new Callback<Proveedor>() {
            @Override
            public void onResponse(Call<Proveedor> call, Response<Proveedor> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ProveedorEdActivity.this,"Se eliminó con éxito el Proveedor",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Proveedor> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
        Intent intent=new Intent(ProveedorEdActivity.this,ProveedorActivity.class);
        startActivity(intent);
    }
}