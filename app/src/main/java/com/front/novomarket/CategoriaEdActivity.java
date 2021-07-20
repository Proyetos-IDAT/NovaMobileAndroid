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

import com.front.novomarket.model.Categoria;
import com.front.novomarket.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaEdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_ed);

        TextView idcat = findViewById(R.id.IdCategoria);
        EditText txtId = findViewById(R.id.txtIdCategoria);

        TextView nomcat = findViewById(R.id.NomCategoria);
        final EditText txtNomCat = findViewById(R.id.txtNomCategoria);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnEliminar = findViewById(R.id.btnEliminar);

        //Estamos obteniendo los valores de una fila
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("ID CATEGORIA");
        String nom = bundle.getString("NOMBRE DE LA CATEGORIA");

        txtId.setText(id);
        txtNomCat.setText(nom);
        //cantidad de digitos sea igual a 0 se bloquea el id
        if (id.trim().length() == 0 || id.equals("")) {
            idcat.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*validación de campo*/
                String nomcat= txtNomCat.getText().toString().trim();
                if (nomcat.isEmpty()) {
                    txtNomCat.setError("Nombre de la categoria requerido");
                    txtNomCat.requestFocus();
                    return;
                }
                //validación si hay número
                if(nomcat.contains("0") || nomcat.contains("1") || nomcat.contains("2") || nomcat.contains("3") ||
                        nomcat.contains("4") || nomcat.contains("5") || nomcat.contains("6") ||
                        nomcat.contains("7") || nomcat.contains("8") || nomcat.contains("9")){
                    txtNomCat.setError("El nombre de la categoria no puede contener números");
                    txtNomCat.requestFocus();
                    return;
                }
                //validación si tiene caracteres
                if(nomcat.contains(".") || nomcat.contains("@") || nomcat.contains("$") || nomcat.contains("!") || nomcat.contains("¡") || nomcat.contains("#") || nomcat.contains(":") || nomcat.contains(";") || nomcat.contains("&") || nomcat.contains("_") || nomcat.contains("-") || nomcat.contains("(") || nomcat.contains(")") || nomcat.contains("'") || nomcat.contains(",") || nomcat.contains("?") || nomcat.contains("¿") || nomcat.contains("+") || nomcat.contains("*") || nomcat.contains("/") || nomcat.contains("=") || nomcat.contains("%") || nomcat.contains("<") || nomcat.contains(">") || nomcat.contains("|") || nomcat.contains("{") || nomcat.contains("}") || nomcat.contains("[") || nomcat.contains("]")){
                    txtNomCat.setError("El nombre de la categoria no puede contener caracteres espciales");
                    txtNomCat.requestFocus();
                    return;
                }

                Categoria c=new Categoria();
                //hemos seteado los nombres y apellidos enviandolo al metodo llevando después al webservice
                c.setNomcat(txtNomCat.getText().toString());
                //colocar condición para cuando guarde y actualize
                if(id.trim().length()==0|| id.equals("")){
                    addCategoria(c);
                    Intent intent =new Intent(CategoriaEdActivity.this,CategoriaActivity.class);
                    startActivity(intent);

                }else{
                    //Si la condición no es guardar se va a actualizar los datos de persona llamandolo por su ai
                    //En el cual luego se va de modificar se va a ir al activitu main para poder ir a la ubicadción
                    updateCategoria(c,Integer.valueOf(id));
                    Intent intent =new Intent(CategoriaEdActivity.this,CategoriaActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método que nos permite transformar en entero
                deleteCategoria(Integer.valueOf(id));
                Intent intent =new Intent(CategoriaEdActivity.this,CategoriaActivity.class);
                startActivity(intent);
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CategoriaEdActivity.this,CategoriaActivity.class);
                startActivity(intent);
            }
        });
    }
    public void addCategoria(Categoria c){

        Call<Categoria> call= RetrofitClient.getInstance().getAPI().addCategoria(c);
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if(response!=null){
                    Toast.makeText(CategoriaEdActivity.this,"Se agrego con éxito la categoría",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });


    }
    public void updateCategoria(Categoria categoria, int id){
        Call<Categoria>call=RetrofitClient.getInstance().getAPI().updateCategoria(categoria,id);
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CategoriaEdActivity.this,"Se actualizó con éxito la categoria",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent=new Intent(CategoriaEdActivity.this,CategoriaActivity.class);
        startActivity(intent);
    }
    public void deleteCategoria(int id){
        Call<Categoria>call=RetrofitClient.getInstance().getAPI().deleteCategoria(id);
        call.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CategoriaEdActivity.this,"Se eliminó con éxito la categoría",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
        Intent intent=new Intent(CategoriaEdActivity.this,CategoriaActivity.class);
        startActivity(intent);
    }
}