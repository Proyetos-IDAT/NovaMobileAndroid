package com.front.novomarket.model;

import com.google.gson.annotations.SerializedName;

public class ComprobantePago {

    private long id;
    private String fecha;
    private int clienteId;
    private int productoId;
    private int metodoPagoId;

    public ComprobantePago(long id, String fecha, int clienteId, int productoId, int metodoPagoId) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.metodoPagoId = metodoPagoId;
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

    public void setMetodopagoId(int metodopagoId) {
        this.metodoPagoId = metodopagoId;
    }

    @Override
    public String toString() {
        return "ComprobantePago{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", clienteId=" + clienteId +
                ", productoId=" + productoId +
                ", metodoPagoId=" + metodoPagoId +
                '}';
    }
}
