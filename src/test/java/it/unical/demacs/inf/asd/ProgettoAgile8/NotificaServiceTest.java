package it.unical.demacs.inf.asd.ProgettoAgile8;


import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.NotificaService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificaServiceTest {


    @Autowired
    private NotificaService notificaService;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PazienteService pazienteService;
    @Autowired
    private DottoreDAO dottoreDAO;
    @Autowired
    private DottoreService dottoreService;

    @Test
    public void testFindAllPaziente() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testNotifica@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        PazienteDTO d = pazienteService.getPazienteByEmail("testNotifica@a.it");
        NotificaDTO notifica = new NotificaDTO();
        notifica.setData(LocalDateTime.now());
        notifica.setPaziente(d.getId());
        notifica.setTesto("test");
        notifica.setVista(false);
        notifica.setRicevitore("paziente");
        notificaService.save(notifica);
        Assert.assertTrue(notificaService.findAllByPaziente(d).size()>0);
    }

    @Test
    public void testFindAllSegretaria() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testNotifica2@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        PazienteDTO d = pazienteService.getPazienteByEmail("testNotifica2@a.it");
        NotificaDTO notifica = new NotificaDTO();
        notifica.setData(LocalDateTime.now());
        notifica.setPaziente(d.getId());
        notifica.setTesto("test");
        notifica.setVista(false);
        notifica.setRicevitore("segretaria");
        notificaService.save(notifica);
        Assert.assertTrue(notificaService.findAllBySegretaria().size()>0);
    }

    @Test
    public void testFindById() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testNotifica3@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        PazienteDTO d = pazienteService.getPazienteByEmail("testNotifica3@a.it");
        NotificaDTO notifica = new NotificaDTO();
        notifica.setData(LocalDateTime.now());
        notifica.setPaziente(d.getId());
        notifica.setTesto("test");
        notifica.setVista(false);
        notifica.setRicevitore("paziente");
        notificaService.save(notifica);
        List<NotificaDTO> lista = notificaService.findAllByPaziente(d);
        Optional<Notifica> risultato= notificaService.findById(lista.get(0).getId());
        Assert.assertEquals(lista.get(0).getId(), risultato.get().getId());
    }

    @Test
    public void testVisteByPaziente() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testNotifica4@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        PazienteDTO d = pazienteService.getPazienteByEmail("testNotifica4@a.it");
        NotificaDTO notifica = new NotificaDTO();
        notifica.setData(LocalDateTime.now());
        notifica.setPaziente(d.getId());
        notifica.setTesto("test");
        notifica.setVista(false);
        notifica.setRicevitore("paziente");
        notificaService.save(notifica);
        notificaService.setAllVista(d);
        List<NotificaDTO> lista = notificaService.findAllByPaziente(d);
        Assert.assertTrue(lista.get(0).getVista());
    }

    @Test
    public void testVisteBySegretaria() throws NoSuchAlgorithmException {
        /*
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testNotifica5@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        PazienteDTO d = pazienteService.getPazienteByEmail("testNotifica5@a.it");

         */
        NotificaDTO notifica = new NotificaDTO();
        notifica.setData(LocalDateTime.now());
        //notifica.setPaziente(d.getId());
        notifica.setTesto("test");
        notifica.setVista(false);
        notifica.setRicevitore("segretaria");
        notificaService.save(notifica);
        notificaService.setAllVistaBySegretaria();
        List<NotificaDTO> lista = notificaService.findAllBySegretaria();
        Assert.assertTrue(lista.get(0).getVista());
    }

    @Test
    public void testFindAllDottore() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testNotifica8@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        Dottore d = dottoreDAO.findAllByEmail("testNotifica8@a.it");
        NotificaDTO notifica = new NotificaDTO();
        notifica.setData(LocalDateTime.now());
        notifica.setTesto("test");
        notifica.setVista(false);
        notifica.setRicevitore("dottore");
        notifica.setDottoreId(d.getId());
        notificaService.save(notifica);
        List<NotificaDTO> lista = notificaService.findAllByDottore(d.getId());

        Assert.assertTrue(lista.size()>0);
    }


    @Test
    public void testVisteByDottore() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testNotifica6@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testNotifica6@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        Dottore d = dottoreDAO.findAllByEmail("testNotifica6@a.it");
        PazienteDTO p = pazienteService.getPazienteByEmail("testNotifica6@a.it");
        NotificaDTO notifica = new NotificaDTO();
        notifica.setData(LocalDateTime.now());
        notifica.setPaziente(p.getId());
        notifica.setTesto("test");
        notifica.setVista(false);
        notifica.setRicevitore("dottore");
        notifica.setDottoreId(d.getId());
        notificaService.save(notifica);
        notificaService.setAllVistaByDottore(d.getId());
        List<NotificaDTO> lista = notificaService.findAllByDottore(d.getId());

        Assert.assertTrue(lista.get(0).getVista());
    }


}
