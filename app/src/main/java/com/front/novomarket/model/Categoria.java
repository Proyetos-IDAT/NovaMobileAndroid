package com.front.novomarket.model;

public class Categoria {
    private int idcat;
    private String nomcat;

    public Categoria() {
    }

    public Categoria(int idcat, String nomcat) {
        this.idcat = idcat;
        this.nomcat = nomcat;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    @Override
    public String toString() {
        return this.nomcat;
    }
}
