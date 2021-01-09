package it.unical.demacs.inf.asd.ProgettoAgile8.core;

public class ItemPrescrizione {

    private String medicinale;
    private int quantita;
    private int dose_di_impiego;
    private int giorni_trattamento;
    private int giorni_sospensione;

    public String getMedicinale() {
        return medicinale;
    }

    public void setMedicinale(String medicinale) {
        this.medicinale = medicinale;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getDose_di_impiego() {
        return dose_di_impiego;
    }

    public void setDose_di_impiego(int dose_di_impiego) {
        this.dose_di_impiego = dose_di_impiego;
    }

    public int getGiorni_trattamento() {
        return giorni_trattamento;
    }

    public void setGiorni_trattamento(int giorni_trattamento) {
        this.giorni_trattamento = giorni_trattamento;
    }

    public int getGiorni_sospensione() {
        return giorni_sospensione;
    }

    public void setGiorni_sospensione(int giorni_sospensione) {
        this.giorni_sospensione = giorni_sospensione;
    }
}
