package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;

public interface PrescrizioneService {

    boolean uploadFile(byte[] bytes, DottoreDTO dottore, AnimaleDTO animale);

}
