package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DOTTORE")
public class Dottore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "cognome", length = 50, nullable = false)
    private String cognome;

    @Column(name = "codice_fiscale", length = 16, nullable = false)
    private String codice_fiscale;

    @Column(name="numero_telefono", length = 10, nullable = false)
    private String numero_telefono;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name= "codice_identificativo", length=20, nullable=false )
    private String codice_identificativo;

    @Column(name="descrizione", length=500)
    private String descrizione;

    @OneToMany(mappedBy = "dottore", fetch = FetchType.LAZY)
    private List<Prenotazione> prenotazioni;

    @Column(name = "salt", nullable = true)
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "img", length = 10000)
    private byte[] img;

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

    public String getCodice_identificativo() {
        return codice_identificativo;
    }

    public void setCodice_identificativo(String codice_identificativo) {
        this.codice_identificativo = codice_identificativo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Dottore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codice_fiscale='" + codice_fiscale + '\'' +
                ", numero_telefono='" + numero_telefono + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", codice_identificativo='" + codice_identificativo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prenotazioni=" + prenotazioni +
                '}';
    }
}
