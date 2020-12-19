package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;

@Entity
@Table(name = "PRESCRIZIONE")
public class Prescrizione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="animale_id",referencedColumnName = "ID")
    private Animale animale;

    @ManyToOne
    @JoinColumn(name="dottore_id",referencedColumnName = "ID")
    private Dottore dottore;



    //byte di array per salvare il pdf


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
}
