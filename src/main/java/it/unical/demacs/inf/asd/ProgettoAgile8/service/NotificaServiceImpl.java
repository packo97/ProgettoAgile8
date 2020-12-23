package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.NotificaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificaServiceImpl implements NotificaService{

    @Autowired
    private NotificaDAO notificaDAO;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<NotificaDTO> findAllByPaziente(PazienteDTO pazienteDTO) {
        Paziente paziente= modelMapper.map(pazienteDTO, Paziente.class);
        System.out.println(notificaDAO.findAllByPazienteAndSegretariaIsFalse(paziente).stream().map(notifica -> modelMapper.map(notifica, NotificaDTO.class)).collect(Collectors.toList()).size());

        return notificaDAO.findAllByPazienteAndSegretariaIsFalse(paziente).stream().map(notifica -> modelMapper.map(notifica, NotificaDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<NotificaDTO> findAllBySegretaria() {
        return notificaDAO.findAllBySegretariaIsFalse().stream().map(notifica -> modelMapper.map(notifica, NotificaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void save(Notifica notifica) {
        notificaDAO.save(notifica);
    }
}
