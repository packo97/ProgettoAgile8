package it.unical.demacs.inf.asd.ProgettoAgile8.dto;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class NotificaDTO {
    private Long id;

    private String testo;

    private Boolean vista;

    private Paziente paziente;

    private Boolean segretaria;

    public Boolean getSegretaria() {
        return segretaria;
    }

    public void setSegretaria(Boolean segretaria) {
        this.segretaria = segretaria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Boolean getVista() {
        return vista;
    }

    public void setVista(Boolean vista) {
        this.vista = vista;
    }

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    @Override
    public String toString() {
        return "NotificaDTO{" +
                "id=" + id +
                ", testo='" + testo + '\'' +
                ", vista=" + vista +
                ", paziente=" + paziente +
                '}';
    }
}
