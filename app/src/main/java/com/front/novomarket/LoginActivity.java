package com.front.novomarket;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.front.novomarket.common.Constante;
import com.front.novomarket.common.SharedPreferencesManager;
import com.front.novomarket.model.User;
import com.front.novomarket.utils.RetrofitClient;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);


        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }

        });
        validarDatos();

        findViewById(R.id.tvRegisterLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void validarDatos() {
        if (!SharedPreferencesManager.getSomeStringValue(Constante.PREF_USERNAME).equals("")) {
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
        }
    }


    private void loginUser() {
        final String userName = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (userName.isEmpty()) {
            etUsername.setError("Usuario requerido");
            etUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            etPassword.setError("Password requerido");
            etPassword.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getAPI()
                .checkUser(new User(userName, password));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = "";
                try {
                    s = response.body().string();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s.equals(userName)) {
                    SharedPreferencesManager.setSomeStringValue(Constante.PREF_USERNAME,userName);
                    Toast.makeText(LoginActivity.this, "Usuario logeado", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Error en los datos ingresados", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                //Log.i("errorRetrofit",t.getMessage());
            }
        });
    }
}