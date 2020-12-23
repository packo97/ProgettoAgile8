package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;

@Entity
@Table(name = "NOTIFICA")
public class Notifica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "testo")
    private String testo;

    @Column(name = "vista")
    private Boolean vista;

    @Column(name = "segretaria")
    private Boolean segretaria;

    @ManyToOne
    @JoinColumn(name="PAZIENTE_ID",referencedColumnName = "ID")
    private Paziente paziente;

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
        return "Notifica{" +
                "id=" + id +
                ", testo='" + testo + '\'' +
                ", vista=" + vista +
                ", paziente=" + paziente +
                '}';
    }
}
