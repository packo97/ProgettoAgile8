package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;

@Entity
@Table(name = "RICEVUTA")
public class Ricevuta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name="animale_id",referencedColumnName = "ID")
    private Animale animale;

    @ManyToOne
    @JoinColumn(name="dottore_id",referencedColumnName = "ID")
    private Dottore dottore;

    @Column(name="content")
    @Lob
    private byte[] content;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
