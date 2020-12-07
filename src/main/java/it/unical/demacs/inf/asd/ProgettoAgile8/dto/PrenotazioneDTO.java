package it.unical.demacs.inf.asd.ProgettoAgile8.dto;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PrenotazioneDTO implements Serializable {

    private Long id;

    private LocalDateTime data_visita;

    private String descrizione;

    private Paziente paziente;

    private Dottore dottore;

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