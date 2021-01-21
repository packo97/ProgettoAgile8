package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NOTIFICA")
public class Notifica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "testo")
    private String testo;

    @Column(name = "oggetto")
    private String oggetto;

    @Column(name = "dottore")
    private String dottore;

    @Column(name = "vista")
    private Boolean vista;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "ricevitore")
    private String ricevitore;

    @Column(name = "dottoreId")
    private Long dottoreId;

    @Column(name = "paziente")
    private Long paziente;

    public Long getDottoreId() {
        return dottoreId;
    }

    public void setDottoreId(Long dottoreId) {
        this.dottoreId = dottoreId;
    }

    public String getDottore() {
        return dottore;
    }

    public void setDottore(String dottore) {
        this.dottore = dottore;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getRicevitore() {
        return ricevitore;
    }

    public void setRicevitore(String ricevitore) {
        this.ricevitore = ricevitore;
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

    public Long getPaziente() {
        return paziente;
    }

    public void setPaziente(Long paziente) {
        this.paziente = paziente;
    }

    @Override
    public String toString() {
        return "Notifica{" +
                "id=" + id +
                ", testo='" + testo + '\'' +
                ", vista=" + vista +
               // ", paziente=" + paziente +
                '}';
    }
}
