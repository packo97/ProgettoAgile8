package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.NotificaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        //System.out.println(notificaDAO.findAllByPazienteAndSegretariaIsFalse(paziente).stream().map(notifica -> modelMapper.map(notifica, NotificaDTO.class)).collect(Collectors.toList()).size());
        return notificaDAO.findAllByPazienteAndRicevitoreOrderByDataDesc(paziente.getId(),"paziente").stream().map(notifica -> modelMapper.map(notifica, NotificaDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<NotificaDTO> findAllBySegretaria() {
        return notificaDAO.findAllByRicevitoreOrderByDataDesc("segretaria").stream().map(notifica -> modelMapper.map(notifica, NotificaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<Notifica> findById(Long id) {
        return notificaDAO.findById(id);
    }

    @Override
    public void save(NotificaDTO notifica) {
        notificaDAO.save(modelMapper.map(notifica,Notifica.class));
    }

    @Override
    @Transactional
    public void setAllVista(PazienteDTO pazienteDTO) {
        notificaDAO.updateNotificheViste(true, pazienteDTO.getId());
    }

    @Override
    @Transactional
    public void setAllVistaBySegretaria() {
        notificaDAO.updateNotificheVisteBySegretaria();
    }

    @Override
    @Transactional
    public void setAllVistaByDottore(Long dottoreId) {
        notificaDAO.updateNotificheVisteByDottoreId(dottoreId);
    }


    @Override
    public void deletePrenotazione(Long id) {
        notificaDAO.deleteById(id);

    }

    @Override
    public List<NotificaDTO> findAllByDottore(Long pazienteId) {
       return notificaDAO.findAllByDottoreIdAndRicevitoreOrderByDataDesc(pazienteId,"dottore").stream().map(notifica -> modelMapper.map(notifica, NotificaDTO.class)).collect(Collectors.toList());
    }
}
