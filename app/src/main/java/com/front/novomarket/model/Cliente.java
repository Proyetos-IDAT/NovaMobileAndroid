package com.front.novomarket.model;

public class Cliente {
    private int idcli;
    private String apecli;
    private String nomcli;
    private String numcli;

    public Cliente(){
    }

    public Cliente(int idcli, String apecli, String nomcli, String numcli) {
        this.idcli = idcli;
        this.apecli = apecli;
        this.nomcli = nomcli;
        this.numcli = numcli;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public String getApecli() {
        return apecli;
    }

    public void setApecli(String apecli) {
        this.apecli = apecli;
    }

    public String getNomcli() {
        return nomcli;
    }

    public void setNomcli(String nomcli) {
        this.nomcli = nomcli;
    }

    public String getNumcli() {
        return numcli;
    }

    public void setNumcli(String numcli) {
        this.numcli = numcli;
    }
}
