package com.front.novomarket.model;


import com.google.gson.annotations.SerializedName;

public class MetodoPago {
    @SerializedName("idmetpago")
    private int idmetpago;
    @SerializedName("tipopago")
    private String tipopago;

    public MetodoPago() {
    }

    public MetodoPago(int idmetpago, String tipopago) {
        this.idmetpago = idmetpago;
        this.tipopago = tipopago;
    }

    public int getIdmetpago() {
        return idmetpago;
    }

    public void setIdmetpago(int idmetpago) {
        this.idmetpago = idmetpago;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    @Override
    public String toString() {
        return this.tipopago;
    }
}
