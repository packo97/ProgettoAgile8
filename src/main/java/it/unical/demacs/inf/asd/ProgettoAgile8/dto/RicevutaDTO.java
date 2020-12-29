package it.unical.demacs.inf.asd.ProgettoAgile8.dto;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;

import java.io.Serializable;

public class RicevutaDTO implements Serializable {

    private Long id;

    private AnimaleDTO animale;

    private DottoreDTO dottore;

    private byte[] content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimaleDTO getAnimale() {
        return animale;
    }

    public void setAnimale(AnimaleDTO animale) {
        this.animale = animale;
    }

    public DottoreDTO getDottore() {
        return dottore;
    }

    public void setDottore(DottoreDTO dottore) {
        this.dottore = dottore;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
