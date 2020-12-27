package it.unical.demacs.inf.asd.ProgettoAgile8.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PrenotazioneDTO implements Serializable {

    private Long id;

    private LocalDateTime data_visita;

    private String descrizione;

    private PazienteDTO paziente;

    private DottoreDTO dottore;

    private boolean urgente;

    private boolean confermato;

    public boolean isConfermato() {
        return confermato;
    }

    public void setConfermato(boolean confermato) {
        this.confermato = confermato;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
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

    public PazienteDTO getPaziente() {
        return paziente;
    }

    public void setPaziente(PazienteDTO paziente) {
        this.paziente = paziente;
    }

    public DottoreDTO getDottore() {
        return dottore;
    }

    public void setDottore(DottoreDTO dottore) {
        this.dottore = dottore;
    }

    @Override
    public String toString() {
        return "PrenotazioneDTO{" +
                "id=" + id +
                ", data_visita=" + data_visita +
                ", descrizione='" + descrizione + '\'' +
                ", paziente=" + paziente +
                ", dottore=" + dottore +
                ", urgente=" + urgente +
                ", confermato=" + confermato +
                '}';
    }
}
