package it.unical.demacs.inf.asd.ProgettoAgile8.dto;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;

import java.io.Serializable;
import java.util.List;

public class PazienteDTO implements Serializable {

    private Long id;

    private String nome;

    private String cognome;

    private String codice_fiscale;

    private String numero_telefono;

    private String email;

    private String password;

    private List<Animale> animale;

    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodice_fiscale() {
        return codice_fiscale;
    }

    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Animale> getAnimale() {
        return animale;
    }

    public void setAnimale(List<Animale> animale) {
        this.animale = animale;
    }

    @Override
    public String toString() {
        return "PazienteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codice_fiscale='" + codice_fiscale + '\'' +
                ", numero_telefono='" + numero_telefono + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", animale='" + animale + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
