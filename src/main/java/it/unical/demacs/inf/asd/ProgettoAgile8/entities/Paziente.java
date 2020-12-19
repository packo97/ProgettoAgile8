package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PAZIENTE")
public class Paziente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "cognome", length = 50, nullable = false)
    private String cognome;

    @Column(name = "codice_fiscale", length = 16, nullable = true)
    private String codice_fiscale;

    @Column(name="numero_telefono", length = 10, nullable = true)
    private String numero_telefono;

    @Column(name = "email", length = 50, nullable = true)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @OneToMany(mappedBy = "paziente")
    private List<Animale> animale;

    @OneToMany(mappedBy = "paziente")
    private List<Prenotazione> prenotazioni;

    @Column(name = "salt", nullable = true)
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
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



}
