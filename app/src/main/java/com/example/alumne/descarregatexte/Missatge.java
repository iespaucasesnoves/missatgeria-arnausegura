package com.example.alumne.descarregatexte;

public class Missatge {
    long codi;
    String msg;
    String dataHora;
    long codiUsuari;
    String nom;

    public Missatge() {
        this.codi = -1;
    }

    public Missatge(long codi, String msg, String dataHora, long codiUsuari, String nom) {
        this.codi = codi;
        this.msg = msg;
        this.dataHora = dataHora;
        this.codiUsuari = codiUsuari;
        this.nom = nom;
    }

    public long getCodi() {
        return codi;
    }

    public void setCodi(long codi) {
        this.codi = codi;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public long getCodiUsuari() {
        return codiUsuari;
    }

    public void setCodiUsuari( long codiUsuari) {
        this.codiUsuari = codiUsuari;
    }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }
}
