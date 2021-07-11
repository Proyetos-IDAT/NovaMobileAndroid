package com.front.novomarket.utils;

import com.front.novomarket.model.Categoria;
import com.front.novomarket.model.Cliente;
import com.front.novomarket.model.ComprobantePago;
import com.front.novomarket.model.MetodoPago;
import com.front.novomarket.model.Producto;
import com.front.novomarket.model.Proveedor;
import com.front.novomarket.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    //--------User Login
    @POST("register")
    Call<ResponseBody> createUser (@Body User user);

    @POST("login")
    Call<ResponseBody> checkUser (@Body User user);
    //---------------------------------------

    //------------Persona Service
    @GET("listar")
    Call<List<Cliente>>getCliente();

    @POST("agregar")
    Call<Cliente>addCliente(@Body Cliente cliente);

    @POST("actualizar/{id}")
    Call<Cliente>updateCliente(@Body Cliente cliente, @Path("id")int id);

    @POST("eliminar/{id}")
    Call<Cliente>deleteCliente(@Path("id") int id);
    //---------------------------------------------------------

    //-------------Proveedor Service
    @GET("listarpro")
    Call<List<Proveedor>>getProveedor();

    @POST("agregarpro")
    Call<Proveedor>addProveedor(@Body Proveedor proveedor);

    @POST("actualizarpro/{id}")
    Call<Proveedor>updateProveedor(@Body Proveedor proveedor, @Path("id") int id);

    @POST("eliminarpro/{id}")
    Call<Proveedor>deleteProveedor(@Path("id") int id);
    //---------------------------------------------------------

    //-------------Metodo Pago Service
    @GET("listarMetodoPago")
    Call<List<MetodoPago>>getMetodoPago();

    @POST("agregarMetodoPago")
    Call<MetodoPago>addMetodoPago(@Body MetodoPago metodopago);

    @POST("actualizarMetodoPago/{id}")
    Call<MetodoPago>updateMetodoPago(@Body MetodoPago metodopago, @Path("id") int id);

    @POST("eliminarMetodoPago/{id}")
    Call<MetodoPago>deleteMetodoPago(@Path("id") int id);
    //---------------------------------------------------------

    //-------------Categoria Service
    @GET("listarcat")
    Call<List<Categoria>>getCategoria();

    @POST("agregarcat")
    Call<Categoria>addCategoria(@Body Categoria categoria);

    @POST("actualizarcat/{id}")
    Call<Categoria>updateCategoria(@Body Categoria categoria, @Path("id") int id);

    @POST("eliminarcat/{id}")
    Call<Categoria>deleteCategoria(@Path("id") int id);
    //---------------------------------------------------------


    @GET("productos/all")
    Call<List<Producto>>getProducto();

    //comprobante
    @GET("listarcompro")
    Call<List<ComprobantePago>>getComprobante();
    @POST("agregarcompro")
    Call<ResponseBody>GenerarComprobante(@Body ComprobantePago c);
}
