package it.unical.demacs.inf.asd.ProgettoAgile8.dto;

import java.time.LocalDateTime;

public class NotificaDTO {
    private Long id;

    private String testo;

    private String oggetto;

    private Boolean vista;

    private Long paziente;

    private String ricevitore;

    private LocalDateTime data;


    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getRicevitore() {
        return ricevitore;
    }

    public void setRicevitore(String ricevitore) {
        this.ricevitore = ricevitore;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
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

    private String dottore;

    private Long dottoreId;

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
        return "NotificaDTO{" +
                "id=" + id +
                ", testo='" + testo + '\'' +
                ", oggetto='" + oggetto + '\'' +
                ", vista=" + vista +
                ", paziente=" + paziente +
                ", ricevitore='" + ricevitore + '\'' +
                ", data=" + data +
                ", dottore='" + dottore + '\'' +
                ", dottoreId=" + dottoreId +
                '}';
    }
}
