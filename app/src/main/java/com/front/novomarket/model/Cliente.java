package com.front.novomarket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cliente {

    @SerializedName("idcli")
    private int idcli;
    @SerializedName("nomcli")
    private String nomcli;
    @SerializedName("apecli")
    private String apecli;
    @SerializedName("numcli")
    private String numcli;

    public Cliente(){
    }

    public Cliente(int idcli,  String nomcli, String apecli,String numcli) {
        this.idcli = idcli;
        this.nomcli = nomcli;
        this.apecli = apecli;
        this.numcli = numcli;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public String getNomcli() {
        return nomcli;
    }

    public void setNomcli(String nomcli) {
        this.nomcli = nomcli;
    }

    public String getApecli() {
        return apecli;
    }

    public void setApecli(String apecli) {
        this.apecli = apecli;
    }



    public String getNumcli() {
        return numcli;
    }

    public void setNumcli(String numcli) {
        this.numcli = numcli;
    }

    @Override
    public String toString() {
        return this.nomcli;
    }
}
