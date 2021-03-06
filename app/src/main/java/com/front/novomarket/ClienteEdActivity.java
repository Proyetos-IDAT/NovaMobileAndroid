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
        String ape = bundle.getString("APELLIDOS");
        String nom = bundle.getString("NOMBRE");
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
                 }if(num.length()!=9){
                    txtNumero.setError("El n??mero de tel??fono del cliente debe tener 9 digitos");
                    txtNumero.requestFocus();
                    return;
                    //validaci??n de ingreso de n??meros en plain text
                }if(nom.contains("0") || nom.contains("1") || nom.contains("2") || nom.contains("3")
                        || nom.contains("4") || nom.contains("5") || nom.contains("6") || nom.contains("7") ||
                        nom.contains("8") || nom.contains("9")){
                    txtNombres.setError("El nombre del cliente no puede contener n??meros");
                    txtNombres.requestFocus();
                    return;
                }if(ape.contains("0") || ape.contains("1") || ape.contains("2") || ape.contains("3") ||
                        ape.contains("4") || ape.contains("5") || ape.contains("6") || ape.contains("7") ||
                        ape.contains("8") || ape.contains("9")){
                    txtApellidos.setError("El apellido del cliente no puede contener n??meros");
                    txtApellidos.requestFocus();
                    return;
                }
                 //validaci??n de ingreso de caracteres en plain text
                if(nom.contains(".") || nom.contains("@") || nom.contains("$") || nom.contains("!") || nom.contains("??") || nom.contains("#") || nom.contains(":") || nom.contains(";") || nom.contains("&") || nom.contains("_") || nom.contains("-") || nom.contains("(") || nom.contains(")") || nom.contains("'") || nom.contains(",") || nom.contains("?") || nom.contains("??") || nom.contains("+") || nom.contains("*") || nom.contains("/") || nom.contains("=") || nom.contains("%") || nom.contains("<") || nom.contains(">") || nom.contains("|") || nom.contains("{") || nom.contains("}") || nom.contains("[") || nom.contains("]")){
                    txtNombres.setError("El nombre del cliente no puede contener caracteres especiales");
                    txtNombres.requestFocus();
                    return;
                }if(ape.contains(".") || ape.contains("@") || ape.contains("$") || ape.contains("!") || ape.contains("??") || ape.contains("#") || ape.contains(":") || ape.contains(";") || ape.contains("&") || ape.contains("_") || ape.contains("-") || ape.contains("(") || ape.contains(")") || ape.contains("'") || ape.contains(",") || ape.contains("?") || ape.contains("??") || ape.contains("+") || ape.contains("*") || ape.contains("/") || ape.contains("=") || ape.contains("%") || ape.contains("<") || ape.contains(">") || ape.contains("|") || ape.contains("{") || ape.contains("}") || ape.contains("[") || ape.contains("]")){
                    txtApellidos.setError("El apellido del cliente no puede contener caracteres especiales");
                    txtApellidos.requestFocus();
                    return;
                }

                Cliente c=new Cliente();
                //hemos seteado los nombres y apellidos enviandolo al metodo llevando despu??s al webservice
                c.setNomcli(txtNombres.getText().toString());
                c.setApecli(txtApellidos.getText().toString());
                c.setNumcli(txtNumero.getText().toString());
                //colocar condici??n para cuando guarde y actualize
                if(id.trim().length()==0|| id.equals("")){
                    addPersona(c);
                    Intent intent =new Intent(ClienteEdActivity.this,ClienteActivity.class);
                    startActivity(intent);
                }else{
                    //Si la condici??n no es guardar se va a actualizar los datos de persona llamandolo por su ai
                    //En el cual luego se va de modificar se va a ir al activitu main para poder ir a la ubicadci??n
                    updatePersona(c,Integer.valueOf(id) );
                    Intent intent =new Intent(ClienteEdActivity.this,ClienteActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //M??todo que nos permite transformar en entero
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
                    Toast.makeText(ClienteEdActivity.this,"Se agrego con ??xito el cliente",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(ClienteEdActivity.this,"Se Actualiz?? con ??xito el cliente",Toast.LENGTH_LONG).show();
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
                    Toast.makeText(ClienteEdActivity.this,"Se Elimino con ??xito el cliente",Toast.LENGTH_LONG).show();
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