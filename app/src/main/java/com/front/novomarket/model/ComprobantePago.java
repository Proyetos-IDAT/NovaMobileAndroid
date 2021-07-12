package com.front.novomarket.model;

import com.google.gson.annotations.SerializedName;

public class ComprobantePago {

    private long id;
    private String fecha;
    private int clienteId;
    private int productoId;
    private int metodoPagoId;
    private double precio;
    private  int cantidad;

    public ComprobantePago(long id, String fecha, int clienteId, int productoId, int metodoPagoId,double precio,int cantidad) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.metodoPagoId = metodoPagoId;
        this.precio=precio;
        this.cantidad=cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(int metodopagoId) {
        this.metodoPagoId = metodopagoId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ComprobantePago{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", clienteId=" + clienteId +
                ", productoId=" + productoId +
                ", metodoPagoId=" + metodoPagoId +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
