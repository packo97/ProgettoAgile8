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
    void save(NotificaDTO notifica);
    void setAllVista(PazienteDTO pazienteDTO);
    void setAllVistaBySegretaria();
    void setAllVistaByDottore(Long dottoreId);
    void deletePrenotazione(Long id);
    List<NotificaDTO> findAllByDottore(Long pazienteId);
}
