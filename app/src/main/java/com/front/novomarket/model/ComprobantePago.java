package com.front.novomarket.model;

public class ComprobantePago {

    private long id;
    private String fecha;
    private int clienteId;
    private int productoId;
    private int metodopagoId;

    public ComprobantePago(long id, String fecha, int clienteId, int productoId, int metodopagoId) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.metodopagoId = metodopagoId;
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

    public int getMetodopagoId() {
        return metodopagoId;
    }

    public void setMetodopagoId(int metodopagoId) {
        this.metodopagoId = metodopagoId;
    }
}
