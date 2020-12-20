package it.unical.demacs.inf.asd.ProgettoAgile8.core;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Filtro implements Serializable {

    private PazienteDTO paziente;
    private DottoreDTO dottore;
    private LocalDateTime data;

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
