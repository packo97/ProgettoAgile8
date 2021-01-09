package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface PazienteService {
    // usare dto

    PazienteDTO addPaziente(PazienteDTO paziente) throws NoSuchAlgorithmException;
    Boolean login(String email, String password);
    PazienteDTO getPazienteByEmail(String email);
    List<PazienteDTO> getPazienti();
    List<PazienteDTO> ricerca(String valoreRicerca);
    PazienteDTO findAllById(Long id);

    PazienteDTO updatePaziente(PazienteDTO dto);
    boolean controllaPassword(String passwordVecchia, PazienteDTO dto);
    void updatePassword(String passwordNuova, PazienteDTO dto);
    void updateImg(byte[] img, Long pazienteID);
    void modificaPassword(RecuperaPasswordDTO recuperaPasswordDTO) throws NoSuchAlgorithmException;
}
