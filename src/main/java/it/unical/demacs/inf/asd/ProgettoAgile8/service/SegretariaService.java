package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;

import java.security.NoSuchAlgorithmException;

public interface SegretariaService {

    SegretariaDTO addSegretaria(SegretariaDTO paziente) throws NoSuchAlgorithmException;

    Boolean login(String email, String password);
    SegretariaDTO getSegretariaByEmail(String email);
}
