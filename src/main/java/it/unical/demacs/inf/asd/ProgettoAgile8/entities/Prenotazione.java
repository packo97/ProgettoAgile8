package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "PRENOTAZIONE")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="data_visita")
    private LocalDateTime data_visita;

    @Column(name="descrizione", length=500, nullable = false)
    private String descrizione;

    @Column(name="urgente", nullable = false)
    private boolean urgente;

    @Column(name="confermato", nullable = false)
    private boolean confermato;

    @ManyToOne
    @JoinColumn(name="PAZIENTE_ID",referencedColumnName = "ID")
    private Paziente paziente;

    @ManyToOne
    @JoinColumn(name="DOTTORE_ID",referencedColumnName = "ID")
    private Dottore dottore;

    public boolean isConfermato() {
        return confermato;
    }

    public void setConfermato(boolean confermato) {
        this.confermato = confermato;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgenza) {
        this.urgente = urgenza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_visita() {
        return data_visita;
    }

    public void setData_visita(LocalDateTime data_visita) {
        this.data_visita = data_visita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public Dottore getDottore() {
        return dottore;
    }

    public void setDottore(Dottore dottore) {
        this.dottore = dottore;
    }
}
