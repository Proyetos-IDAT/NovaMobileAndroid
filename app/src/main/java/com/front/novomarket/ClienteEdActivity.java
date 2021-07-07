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

import com.front.novomarket.model.Cliente;
import com.front.novomarket.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteEdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_ed);

        TextView idcli = findViewById(R.id.Id);
        EditText txtId = findViewById(R.id.txtId);

        TextView nombres = findViewById(R.id.nombres);
        final EditText txtNombres = findViewById(R.id.txtNombres);

        TextView apellidos = findViewById(R.id.apellidos);
        final EditText txtApellidos = findViewById(R.id.txtApellidos);

        TextView numero = findViewById(R.id.numero);
        final EditText txtNumero = findViewById(R.id.txtNumero);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnEliminar = findViewById(R.id.btnEliminar);

        //Estamos obteniendo los valores de una fila
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("ID");
        String ape = bundle.getString("NOMBRE");
        String nom = bundle.getString("APELLIDOS");
        String num = bundle.getString("NUMERO");

        txtId.setText(id);
        txtNombres.setText(nom);
        txtApellidos.setText(ape);
        txtNumero.setText(num);
        //cantidad de digitos sea igual a 0 se bloquea el id
        if (id.trim().length() == 0 || id.equals("")) {
            idcli.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom= txtNombres.getText().toString().trim();
                String ape= txtApellidos.getText().toString().trim();
                String num= txtNumero.getText().toString().trim();
                 if(nom.isEmpty()){
                    txtNombres.setError("Nombre requerido");
                    txtNombres.requestFocus();
                    return;
                } if(ape.isEmpty()){
                    txtApellidos.setError("Apellido requerido");
                    txtApellidos.requestFocus();
                     return;
                }if(num.isEmpty()) {
                    txtNumero.setError("Numero requerido");
                    txtNumero.requestFocus();
                     return;
                 }

                Cliente c=new Cliente();
                //hemos seteado los nombres y apellidos enviandolo al metodo llevando después al webservice
                c.setNomcli(txtNombres.getText().toString());
                c.setApecli(txtApellidos.getText().toString());
                c.setNumcli(txtNumero.getText().toString());
                //colocar condición para cuando guarde y actualize
                if(id.trim().length()==0|| id.equals("")){
                    addPersona(c);
                    Intent intent =new Intent(ClienteEdActivity.this,ClienteActivity.class);
                    startActivity(intent);
                }else{
                    //Si la condición no es guardar se va a actualizar los datos de persona llamandolo por su ai
                    //En el cual luego se va de modificar se va a ir al activitu main para poder ir a la ubicadción
                    updatePersona(c,Integer.valueOf(id) );
                    Intent intent =new Intent(ClienteEdActivity.this,ClienteActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método que nos permite transformar en entero
                deletePersona(Integer.valueOf(id));
                Intent intent =new Intent(ClienteEdActivity.this,ClienteActivity.class);
                startActivity(intent);
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ClienteEdActivity.this,ClienteActivity.class);
                startActivity(intent);
            }
        });
    }
    public void addPersona(Cliente p){
        Call<Cliente>call= RetrofitClient.getInstance().getAPI().addCliente(p);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response!=null){
                    Toast.makeText(ClienteEdActivity.this,"Se agrego con éxito el cliente",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ClienteEdActivity.this,"Ocurrio un incoveniente",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent=new Intent(ClienteEdActivity.this,ClienteActivity.class);
        startActivity(intent);
    }
    public void updatePersona(Cliente cliente, int id){
        Call<Cliente>call=RetrofitClient.getInstance().getAPI().updateCliente(cliente,id);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ClienteEdActivity.this,"Se Actualizó con éxito el cliente",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent=new Intent(ClienteEdActivity.this,ClienteActivity.class);
        startActivity(intent);
    }
    public void deletePersona(int id){
        Call<Cliente>call=RetrofitClient.getInstance().getAPI().deleteCliente(id);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ClienteEdActivity.this,"Se Elimino con éxito el cliente",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
        Intent intent=new Intent(ClienteEdActivity.this,ClienteActivity.class);
        startActivity(intent);
    }
}