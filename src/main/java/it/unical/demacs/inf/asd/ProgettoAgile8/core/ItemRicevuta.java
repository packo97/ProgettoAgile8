package it.unical.demacs.inf.asd.ProgettoAgile8.core;

public class ItemRicevuta {

    private Long codice;
    private String descrizione;
    private int quantita;
    private int prezzo;
    private int totale;

    public Long getCodice() {
        return codice;
    }

    public void setCodice(Long codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }


    @Override
    public String toString() {
        return "ItemRicevuta{" +
                "codice=" + codice +
                ", descrizione='" + descrizione + '\'' +
                ", quantita=" + quantita +
                ", prezzo=" + prezzo +
                ", totale=" + totale +
                '}';
    }
}
