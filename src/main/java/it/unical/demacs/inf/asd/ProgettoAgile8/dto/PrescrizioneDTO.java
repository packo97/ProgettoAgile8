package it.unical.demacs.inf.asd.ProgettoAgile8.dto;

import java.io.Serializable;
import java.util.Arrays;

public class PrescrizioneDTO implements Serializable {


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

    @Override
    public String toString() {
        return "PrescrizioneDTO{" +
                "id=" + id +
                ", animale=" + animale +
                ", dottore=" + dottore +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
