package com.front.novomarket.model;

import java.util.Date;

public class Producto {
    private int idprod;
    private String nomprod;
    private Date fechavenc;
    private Double precio;
    private int stock_min;
    private int stock_act;

    public Producto(int idprod, String nomprod, Date fechavenc, Double precio, int stock_min, int stock_act) {
        this.idprod = idprod;
        this.nomprod = nomprod;
        this.fechavenc = fechavenc;
        this.precio = precio;
        this.stock_min = stock_min;
        this.stock_act = stock_act;
    }

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    public String getNomprod() {
        return nomprod;
    }

    public void setNomprod(String nomprod) {
        this.nomprod = nomprod;
    }

    public Date getFechavenc() {
        return fechavenc;
    }

    public void setFechavenc(Date fechavenc) {
        this.fechavenc = fechavenc;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock_min() {
        return stock_min;
    }

    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
    }

    public int getStock_act() {
        return stock_act;
    }

    public void setStock_act(int stock_act) {
        this.stock_act = stock_act;
    }
}
