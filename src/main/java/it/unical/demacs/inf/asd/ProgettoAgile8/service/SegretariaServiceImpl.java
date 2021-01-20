package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.SegretariaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
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
        String password= dto.getPassword();
        byte[] salt = Sicurezza.getSalt();
        String saltString = salt.toString();
        String passwordSicura = Sicurezza.getSecurePassword(password, saltString.getBytes(StandardCharsets.UTF_8));
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
        if(d==null) {
            System.out.println("dio");
            return false;
        }
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
        segretariaDAO.updatePassword2(password,saltString,recuperaPasswordDTO.getEmail());
        SendEmail.getInstance().sendMailPasswordCambiata(recuperaPasswordDTO.getEmail());
    }

    @Override
    @Transactional
    public SegretariaDTO updateSegretaria(SegretariaDTO dto) {
        Segretaria s = modelMapper.map(dto, Segretaria.class);
        segretariaDAO.updateSegretaria(s.getNome(), s.getCognome(), s.getCodice_fiscale(), s.getNumero_telefono(), s.getId());
        return dto;
    }

    @Override
    public boolean controllaPassword(String passwordVecchia, SegretariaDTO dto) {
        Segretaria d = segretariaDAO.findAllByEmail(dto.getEmail());
        String salt = d.getSalt();
        String hashPasswordDB = d.getPassword();
        String hashPasswordInserita = Sicurezza.getSecurePassword(passwordVecchia, salt.getBytes(StandardCharsets.UTF_8));
        return hashPasswordDB.equals(hashPasswordInserita);
    }

    @Override
    @Transactional
    public void updatePassword(String passwordNuova, SegretariaDTO dto) {
        Segretaria d = segretariaDAO.findAllByEmail(dto.getEmail());
        String salt = d.getSalt();
        String hashPasswordInserita = Sicurezza.getSecurePassword(passwordNuova, salt.getBytes(StandardCharsets.UTF_8));
        segretariaDAO.updatePassword1(hashPasswordInserita, d.getId());
    }
    @Override
    @Transactional
    public void updateImg(byte[] img, Long segretariaID) {
        segretariaDAO.updateImg(img,segretariaID);
    }

}
