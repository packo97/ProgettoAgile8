package it.unical.demacs.inf.asd.ProgettoAgile8.core;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

import java.util.List;

public class ListaItemPrescrizione {

    private DottoreDTO dottore;
    private PazienteDTO paziente;
    private List<ItemPrescrizione> lista_item_prescrizione;

    public DottoreDTO getDottore() {
        return dottore;
    }

    public void setDottore(DottoreDTO dottore) {
        this.dottore = dottore;
    }

    public PazienteDTO getPaziente() {
        return paziente;
    }

    public void setPaziente(PazienteDTO paziente) {
        this.paziente = paziente;
    }

    public List<ItemPrescrizione> getLista_item_prescrizione() {
        return lista_item_prescrizione;
    }

    public void setLista_item_prescrizione(List<ItemPrescrizione> lista_item_prescrizione) {
        this.lista_item_prescrizione = lista_item_prescrizione;
    }
}
