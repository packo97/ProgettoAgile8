package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dao.AnimaleDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PrescrizioneDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrescrizioneServiceImpl implements PrescrizioneService{


    @Autowired
    private PrescrizioneDAO prescrizioneDAO;

    @Autowired
    private ModelMapper modelMapper;

}
