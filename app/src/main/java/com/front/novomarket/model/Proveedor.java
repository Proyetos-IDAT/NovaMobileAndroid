package com.front.novomarket.model;

public class Proveedor {

    private int idprove;
    private String nomprove;
    private String ruc;
    private String nomcontacto;
    private String direccion;
    private String telefono;

    public Proveedor(){

    }

    public Proveedor(int idprove, String nomprove, String ruc, String nomcontacto, String direccion, String telefono) {
        this.idprove = idprove;
        this.nomprove = nomprove;
        this.ruc = ruc;
        this.nomcontacto = nomcontacto;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdprove() {
        return idprove;
    }

    public void setIdprove(int idprove) {
        this.idprove = idprove;
    }

    public String getNomprove() {
        return nomprove;
    }

    public void setNomprove(String nomprove) {
        this.nomprove = nomprove;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNomcontacto() {
        return nomcontacto;
    }

    public void setNomcontacto(String nomcontacto) {
        this.nomcontacto = nomcontacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
