package com.example.alumne.descarregatexte;

public class Usuari {
    long codiUsuari;
    String nom;
    String password;
    String email;
    String darrerAcces;
    int darrerMissatge;
    String token;
    String tokenLimit;
    String foto;

    public Usuari(){
        this.codiUsuari = -1;
    }

    public long getCodiUsuari() {
        return codiUsuari;
    }

    public void setCodiUsuari(long codiUsuari) {
        this.codiUsuari = codiUsuari;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDarrerAcces() {
        return darrerAcces;
    }

    public void setDarrerAcces(String darrerAcces) {
        this.darrerAcces = darrerAcces;
    }

    public int getDarrerMissatge() {
        return darrerMissatge;
    }

    public void setDarrerMissatge(int darrerMissatge) {
        this.darrerMissatge = darrerMissatge;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenLimit() {
        return tokenLimit;
    }

    public void setTokenLimit(String tokenLimit) {
        this.tokenLimit = tokenLimit;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
