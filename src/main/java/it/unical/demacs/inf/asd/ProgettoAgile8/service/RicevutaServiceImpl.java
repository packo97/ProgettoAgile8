package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dao.RicevutaDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RicevutaServiceImpl implements RicevutaService{


    @Autowired
    private RicevutaDAO ricevutaDAO;

    @Autowired
    private ModelMapper modelMapper;

}
