package it.unical.demacs.inf.asd.ProgettoAgile8.core;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Filtro implements Serializable {

    private PazienteDTO paziente;
    private DottoreDTO dottore;
    private SegretariaDTO segretaria;
    private LocalDateTime data;
    private String passwordVecchia;
    private String passwordNuova;

    public String getPasswordVecchia() {
        return passwordVecchia;
    }

    public void setPasswordVecchia(String passwordVecchia) {
        this.passwordVecchia = passwordVecchia;
    }

    public String getPasswordNuova() {
        return passwordNuova;
    }

    public void setPasswordNuova(String passwordNuova) {
        this.passwordNuova = passwordNuova;
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

    public SegretariaDTO getSegretaria() {
        return segretaria;
    }

    public void setSegretaria(SegretariaDTO segretaria) {
        this.segretaria = segretaria;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Filtro{" +
                "paziente=" + paziente +
                ", dottore=" + dottore +
                ", data=" + data +
                '}';
    }
}
