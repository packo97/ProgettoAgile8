package it.unical.demacs.inf.asd.ProgettoAgile8.core;

public class RecuperaPasswordDTO {

    public String email;
    public String tipo_login;
    public String codice;
    public String nuovaPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo_login() {
        return tipo_login;
    }

    public void setTipo_login(String tipo_login) {
        this.tipo_login = tipo_login;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNuovaPassword() {
        return nuovaPassword;
    }

    public void setNuovaPassword(String nuovaPassword) {
        this.nuovaPassword = nuovaPassword;
    }

    @Override
    public String toString() {
        return "RecuperaPasswordDTO{" +
                "email='" + email + '\'' +
                ", tipo_login='" + tipo_login + '\'' +
                ", codice='" + codice + '\'' +
                ", nuovaPassword='" + nuovaPassword + '\'' +
                '}';
    }
}
