package com.front.novomarket.model;

public class MetodoPago {
    private int idmetpago;
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
}
