package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.Sicurezza;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DottoreServiceImpl  implements DottoreService{


    @Autowired
    private DottoreDAO dottoreDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DottoreDTO addDottore(DottoreDTO dto) throws NoSuchAlgorithmException {
        String password= dto.getPassword();
        byte[] salt = Sicurezza.getSalt();
        String saltString = salt.toString();
        String passwordSicura = Sicurezza.getSecurePassword(password, saltString.getBytes(StandardCharsets.UTF_8));
        dto.setPassword(passwordSicura);
        dto.setSalt(saltString);
        Dottore dottore = modelMapper.map(dto, Dottore.class);
        Dottore saved = dottoreDAO.save(dottore);
        return modelMapper.map(saved, DottoreDTO.class);
    }
    @Override
    public Boolean login(String email, String password) {
        Dottore d1 = dottoreDAO.findAllByEmail(email);
        if(d1==null)
            return false;
        String salt= d1.getSalt();

        Dottore d = dottoreDAO.findAllByEmailAndPassword(email,Sicurezza.getSecurePassword(password,salt.getBytes(StandardCharsets.UTF_8)));
        if(d==null)
            return false;
        else return true;
    }

    @Override
    public List<DottoreDTO> getAll() {
        return dottoreDAO.findAll().stream().map(dottore -> modelMapper.map(dottore, DottoreDTO.class)).collect(Collectors.toList());
    }

    @Override
    public DottoreDTO getDottoreByEmail(String email) {
        Dottore dottore = dottoreDAO.findAllByEmail(email);
        return modelMapper.map(dottore, DottoreDTO.class);
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
        dottoreDAO.updatePassword2(password,saltString,recuperaPasswordDTO.getEmail());
        SendEmail.getInstance().sendMailPasswordCambiata(recuperaPasswordDTO.getEmail());
    }

    @Override
    @Transactional
    public DottoreDTO updateDottore(DottoreDTO dto) {
        Dottore d = modelMapper.map(dto, Dottore.class);
        dottoreDAO.updateDottore(d.getNome(), d.getCognome(), d.getCodice_fiscale(), d.getNumero_telefono(), d.getId());
        return dto;
    }

    @Override
    public boolean controllaPassword(String passwordVecchia, DottoreDTO dto) {
        Dottore d = dottoreDAO.findAllByEmail(dto.getEmail());
        String salt = d.getSalt();
        String hashPasswordDB = d.getPassword();
        String hashPasswordInserita = Sicurezza.getSecurePassword(passwordVecchia, salt.getBytes(StandardCharsets.UTF_8));
        return hashPasswordDB.equals(hashPasswordInserita);
    }

    @Override
    @Transactional
    public void updatePassword(String passwordNuova, DottoreDTO dto) {
        Dottore d = dottoreDAO.findAllByEmail(dto.getEmail());
        String salt = d.getSalt();
        String hashPasswordInserita = Sicurezza.getSecurePassword(passwordNuova, salt.getBytes(StandardCharsets.UTF_8));
        dottoreDAO.updatePassword1(hashPasswordInserita, d.getId());
    }

    @Override
    @Transactional
    public void updateImg(byte[] img, Long dottoreID) {
        dottoreDAO.updateImg(img,dottoreID);
    }
}
