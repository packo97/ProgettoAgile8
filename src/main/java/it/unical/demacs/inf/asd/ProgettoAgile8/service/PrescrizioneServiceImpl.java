package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dao.AnimaleDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PrescrizioneDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrescrizioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prescrizione;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrescrizioneServiceImpl implements PrescrizioneService{


    @Autowired
    private PrescrizioneDAO prescrizioneDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean uploadFile(byte[] bytes, DottoreDTO dottore, AnimaleDTO animale) {
        PrescrizioneDTO p = new PrescrizioneDTO();
        p.setContent(bytes);
        p.setDottore(dottore);
        p.setAnimale(animale);
        Prescrizione prescrizione = modelMapper.map(p, Prescrizione.class);
        prescrizioneDAO.save(prescrizione);
        return true;
    }
}
