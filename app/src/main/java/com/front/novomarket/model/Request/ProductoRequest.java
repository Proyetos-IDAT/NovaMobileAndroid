package com.front.novomarket.model.Request;

import com.front.novomarket.model.Categoria;
import com.front.novomarket.model.Proveedor;

import java.util.Date;

public class ProductoRequest {
    private String nomprod;
    private String fechavenc;
    private Double precio;
    private int stock_min;
    private int stock_act;
    private Categoria categoria;
    private Proveedor proveedor;

    public ProductoRequest(String nomprod, String fechavenc, Double precio, int stock_min,
                           int stock_act, Categoria categoria, Proveedor proveedor) {
        this.nomprod = nomprod;
        this.fechavenc = fechavenc;
        this.precio = precio;
        this.stock_min = stock_min;
        this.stock_act = stock_act;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }
}
