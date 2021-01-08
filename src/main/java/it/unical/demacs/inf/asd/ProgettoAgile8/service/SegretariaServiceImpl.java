package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.SegretariaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Segretaria;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.Sicurezza;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

@Service
public class SegretariaServiceImpl implements SegretariaService{

    @Autowired
    private SegretariaDAO segretariaDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SegretariaDTO addSegretaria(SegretariaDTO dto) throws NoSuchAlgorithmException {
        System.out.println("Aggiunta Segretaria");
        String password= dto.getPassword();
        byte[] salt = Sicurezza.getSalt();
        String saltString = salt.toString();
        String passwordSicura = Sicurezza.getSecurePassword(password, saltString.getBytes(StandardCharsets.UTF_8));
        System.out.println(passwordSicura);
        dto.setPassword(passwordSicura);
        dto.setSalt(saltString);
        Segretaria segretaria = modelMapper.map(dto, Segretaria.class);
        Segretaria saved = segretariaDAO.save(segretaria);
        return modelMapper.map(saved, SegretariaDTO.class);
    }

    @Override
    public Boolean login(String email, String password) {
        Segretaria s1 = segretariaDAO.findAllByEmail(email);
        if(s1==null)
            return false;
        String salt= s1.getSalt();
        Segretaria d = segretariaDAO.findAllByEmailAndPassword(email,Sicurezza.getSecurePassword(password,salt.getBytes(StandardCharsets.UTF_8)));
        if(d==null)
            return false;
        else return true;
    }

    @Override
    public SegretariaDTO getSegretariaByEmail(String email) {
        Segretaria segretaria = segretariaDAO.findAllByEmail(email);
        return modelMapper.map(segretaria, SegretariaDTO.class);
    }


    @Override
    @Transactional
    public void modificaPassword(RecuperaPasswordDTO recuperaPasswordDTO) {
        byte[] salt = new byte[0];
        try {
            salt = Sicurezza.getSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String saltString = salt.toString();
        String password = Sicurezza.getSecurePassword(recuperaPasswordDTO.getNuovaPassword(),saltString.getBytes(StandardCharsets.UTF_8));
        segretariaDAO.updatePassword(password,saltString,recuperaPasswordDTO.getEmail());
        SendEmail.getInstance().sendMailPasswordCambiata(recuperaPasswordDTO.getEmail());
    }
}
