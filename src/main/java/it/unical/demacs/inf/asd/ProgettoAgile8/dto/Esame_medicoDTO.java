package it.unical.demacs.inf.asd.ProgettoAgile8.dto;

public class Esame_medicoDTO {

    private Long id;

    private AnimaleDTO animale;

    private DottoreDTO dottore;

    private String descrizione;

    private byte[] content;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
