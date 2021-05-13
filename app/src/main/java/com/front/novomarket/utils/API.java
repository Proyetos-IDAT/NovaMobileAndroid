package com.front.novomarket.utils;

import com.front.novomarket.model.Cliente;
import com.front.novomarket.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @POST("register")
    Call<ResponseBody> createUser (
            @Body User user
    );

    @POST("login")
    Call<ResponseBody> checkUser (
            @Body User user
    );
    @GET("listar")
    Call<List<Cliente>>getCliente();

    @POST("agregar")
    Call<Cliente>addCliente(@Body Cliente cliente);

    @POST("actualizar/{id}")
    Call<Cliente>updateCliente(@Body Cliente cliente, @Path("id")int id);

    @POST("eliminar/{id}")
    Call<Cliente>deleteCliente(@Path("id") int id);
}
