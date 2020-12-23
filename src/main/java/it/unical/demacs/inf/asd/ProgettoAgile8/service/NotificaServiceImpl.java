package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.NotificaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificaServiceImpl implements NotificaService{

    @Autowired
    private NotificaDAO notificaDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NotificaDTO> findAllByPaziente(NotificaDTO notificaDTO) {
        return null;
    }

    @Override
    public void save(Notifica notifica) {
        notificaDAO.save(notifica);
    }
}
