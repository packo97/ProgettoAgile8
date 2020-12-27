package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;

import java.util.List;
import java.util.Optional;

public interface NotificaService {
    List<NotificaDTO> findAllByPaziente(PazienteDTO pazienteDTO);
    List<NotificaDTO> findAllBySegretaria();
    Optional<Notifica> findById(Long id);
    void save(Notifica notifica);
    void setAllVista(PazienteDTO pazienteDTO);

    void deletePrenotazione(Long id);
}
