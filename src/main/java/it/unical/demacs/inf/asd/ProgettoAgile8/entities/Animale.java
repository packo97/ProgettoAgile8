package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ANIMALE")
public class Animale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name="PAZIENTE_ID",referencedColumnName = "ID")
    private Paziente paziente;

    @Column(name = "tipo", length = 16, nullable = true)
    private String tipo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_nascita", length = 16, nullable = true)
    private LocalDate data_nascita;

    @Column(name = "peso", length = 16, nullable = true)
    private int peso;

    @Column(name = "altezza", length = 16, nullable = true)
    private int altezza;

    @Column(name = "genere", length = 10)
    private String genere;

    @OneToMany(mappedBy = "animale", cascade = CascadeType.ALL)
    private List<Prescrizione> prescrizioni;

    @OneToMany(mappedBy = "animale", cascade = CascadeType.ALL)
    private List<Ricevuta> ricevute;

    @OneToMany(mappedBy = "animale", cascade = CascadeType.ALL)
    private List<Esame_medico> esami;

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

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(LocalDate data_nascita) {
        this.data_nascita = data_nascita;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public List<Prescrizione> getPrescrizioni() {
        return prescrizioni;
    }

    public void setPrescrizioni(List<Prescrizione> prescrizioni) {
        this.prescrizioni = prescrizioni;
    }

    public List<Ricevuta> getRicevute() {
        return ricevute;
    }

    public void setRicevute(List<Ricevuta> ricevute) {
        this.ricevute = ricevute;
    }
}
