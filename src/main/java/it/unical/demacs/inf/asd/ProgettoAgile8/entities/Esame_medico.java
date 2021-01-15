package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;

@Entity
@Table(name = "ESAME_MEDICO")
public class Esame_medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="descrizione")
    private String descrizione;

    @ManyToOne
    @JoinColumn(name="animale_id",referencedColumnName = "ID")
    private Animale animale;

    @ManyToOne
    @JoinColumn(name="dottore_id",referencedColumnName = "ID")
    private Dottore dottore;

    @Column(name="content")
    @Basic
    private byte[] content;

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

    public Animale getAnimale() {
        return animale;
    }

    public void setAnimale(Animale animale) {
        this.animale = animale;
    }

    public Dottore getDottore() {
        return dottore;
    }

    public void setDottore(Dottore dottore) {
        this.dottore = dottore;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
