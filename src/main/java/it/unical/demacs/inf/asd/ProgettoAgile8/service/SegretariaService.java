package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;

import java.security.NoSuchAlgorithmException;

public interface SegretariaService {

    SegretariaDTO addSegretaria(SegretariaDTO paziente) throws NoSuchAlgorithmException;

    Boolean login(String email, String password);
    SegretariaDTO getSegretariaByEmail(String email);

    SegretariaDTO updateSegretaria(SegretariaDTO dto);
    boolean controllaPassword(String passwordVecchia, SegretariaDTO dto);
    void updatePassword(String passwordNuova, SegretariaDTO dto);
    void updateImg(byte[] img, Long segretariaID);
    void modificaPassword(RecuperaPasswordDTO recuperaPasswordDTO);
}
