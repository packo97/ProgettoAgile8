package it.unical.demacs.inf.asd.ProgettoAgile8.core;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

import java.util.List;

public class ListaItemRicevuta {

    PazienteDTO paziente;
    DottoreDTO dottore;
    List<ItemRicevuta> lista_item_ricevuta;
    Double importo_pagato;

    public PazienteDTO getPaziente() {
        return paziente;
    }

    public void setPaziente(PazienteDTO paziente) {
        this.paziente = paziente;
    }

    public DottoreDTO getDottore() {
        return dottore;
    }

    public void setDottore(DottoreDTO dottore) {
        this.dottore = dottore;
    }

    public List<ItemRicevuta> getLista_item_ricevuta() {
        return lista_item_ricevuta;
    }

    public void setLista_item_ricevuta(List<ItemRicevuta> lista_item_ricevuta) {
        this.lista_item_ricevuta = lista_item_ricevuta;
    }

    public Double getImporto_pagato() {
        return importo_pagato;
    }

    public void setImporto_pagato(Double importo_pagato) {
        this.importo_pagato = importo_pagato;
    }

    @Override
    public String toString() {
        return "ListaItemRicevuta{" +
                "paziente=" + paziente +
                ", dottore=" + dottore +
                ", lista_item_ricevuta=" + lista_item_ricevuta +
                '}';
    }
}
