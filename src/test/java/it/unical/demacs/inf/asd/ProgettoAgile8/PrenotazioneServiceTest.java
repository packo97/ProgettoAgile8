package it.unical.demacs.inf.asd.ProgettoAgile8;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PrenotazioneService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrenotazioneServiceTest {

    @Autowired
    PrenotazioneService prenotazioneService;


    @Autowired
    PazienteService pazienteService;

    @Autowired
    DottoreService dottoreService;

    @Test
    public void testAdd() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione30@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione30@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(true);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        dottore=dottoreService.getDottoreByEmail("testPrenotazione30@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione30@a.it");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
    }

    @Test
    public void testGetUrgentiNonConfermate() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione31@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione31@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione31@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione31@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
        Assert.assertTrue(prenotazioneService.getUrgentiNonConfermate().size()>0);
    }

    @Test
    public void testGetUrgentiNonConfermateByDoctor() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione32@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione32@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione32@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione32@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
        Assert.assertTrue(prenotazioneService.getUrgentiNonConfermateByDoctor(dottore).size()>0);
    }

    @Test
    public void testGetAccettate() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione33@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione33@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione33@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione33@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(true);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(false);

        prenotazioneService.addPrenotazione(prenotazione);
        Assert.assertTrue(prenotazioneService.getAccettate().size()>0);
    }

    @Test
    public void testGetInAttesa() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione34@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione34@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione34@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione34@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(false);

        prenotazioneService.addPrenotazione(prenotazione);
        Assert.assertTrue(prenotazioneService.getInAttesa().size()>0);
    }

    @Test
    public void testGetByPaziente() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione35@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione35@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione35@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione35@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
        Assert.assertTrue(prenotazioneService.getPrenotazioniByPaziente(paziente).size()>0);
    }

    @Test
    public void testGetByDoctorAndDate() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione36@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione36@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione36@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione36@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        LocalDateTime data = LocalDateTime.of(2021,1,13,14,14);
        prenotazione.setData_visita(data);
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
        Assert.assertTrue(prenotazioneService.getPrenotazioniByDoctorAndDate(dottore,data,false).size()>0);
    }

    @Test
    public void testGetByDoctor() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione37@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione37@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione37@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione37@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
        Assert.assertTrue(prenotazioneService.getPrenotazioniByDoctor(dottore,false).size()>0);
    }

    @Test
    public void testGetRichiesteByDoctor() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione38@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione38@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione38@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione38@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(false);

        prenotazioneService.addPrenotazione(prenotazione);
        Assert.assertTrue(prenotazioneService.getRichiesteByDoctor(dottore,false).size()>0);
    }

    @Test
    public void testDelete() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione39@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione39@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione39@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione39@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
        List<PrenotazioneDTO> prenotazioni = prenotazioneService.getPrenotazioniByPaziente(paziente);
        int size = prenotazioni.size();
        prenotazioneService.deletePrenotazione(prenotazioni.get(0).getId());
        List<PrenotazioneDTO> prenotazioniAfter = prenotazioneService.getPrenotazioniByPaziente(paziente);
        int sizeAfter = prenotazioniAfter.size();
        size--;
        Assert.assertEquals(size,sizeAfter);
    }

    @Test
    public void testGetById() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione399@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione399@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione399@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione399@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
        List<PrenotazioneDTO> prenotazioni = prenotazioneService.getPrenotazioniByPaziente(paziente);
        Prenotazione risultato = prenotazioneService.getById(prenotazioni.get(0).getId());
        Assert.assertEquals(prenotazioni.get(0).getId(), risultato.getId());
    }

    @Test
    public void testUpdate() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione388@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione388@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        dottore=dottoreService.getDottoreByEmail("testPrenotazione388@a.it");
        paziente=pazienteService.getPazienteByEmail("testPrenotazione388@a.it");
        PrenotazioneDTO prenotazione = new PrenotazioneDTO();
        prenotazione.setConfermato(false);
        prenotazione.setData_visita(LocalDateTime.now());
        prenotazione.setDescrizione("test");
        prenotazione.setDottore(dottore);
        prenotazione.setPaziente(paziente);
        prenotazione.setUrgente(true);

        prenotazioneService.addPrenotazione(prenotazione);
        List<PrenotazioneDTO> prenotazioni = prenotazioneService.getPrenotazioniByPaziente(paziente);
        prenotazioni.get(0).setConfermato(true);
        prenotazioneService.updatePrenotazione(prenotazioni.get(0));

        Assert.assertTrue(prenotazioneService.getById(prenotazioni.get(0).getId()).isConfermato());
    }
}

