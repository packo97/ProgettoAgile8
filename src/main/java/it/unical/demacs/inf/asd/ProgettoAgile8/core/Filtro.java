package it.unical.demacs.inf.asd.ProgettoAgile8.core;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Filtro implements Serializable {

    private DottoreDTO dottore;
    private LocalDateTime data;

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
}
