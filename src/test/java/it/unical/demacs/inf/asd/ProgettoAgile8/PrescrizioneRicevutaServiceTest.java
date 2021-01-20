package it.unical.demacs.inf.asd.ProgettoAgile8;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.*;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrescrizioneRicevutaServiceTest {

    @Autowired
    PrescrizioneService prescrizioneService;

    @Autowired
    RicevutaService ricevutaService;

    @Autowired
    PazienteService pazienteService;

    @Autowired
    DottoreService dottoreService;

    @Autowired
    AnimaleService animaleService;


    @Test
    public void testUpdate() throws NoSuchAlgorithmException {

        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testPrenotazione@a.it"));
        animaleService.addAnimale(animale);


        /*
        PazienteDTO paziente = pazienteService.getPazienteByEmail("pazienteTest@a.it");
        DottoreDTO d=dottoreService.getDottoreByEmail("dottoreTest@a.it");

         */
        List<AnimaleDTO> lista= animaleService.findAllByPaziente(pazienteService.getPazienteByEmail("testPrenotazione@a.it"));


        try {
            File myObj = new File("filename.pdf");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("filename.pdf");
            myWriter.write("Test file");
            myWriter.close();
            byte[] bytes = Files.readAllBytes(myObj.toPath());
            prescrizioneService.uploadFile(bytes,dottoreService.getDottoreByEmail("testPrenotazione@a.it"),lista.get(0));
            ricevutaService.uploadFile(bytes,dottoreService.getDottoreByEmail("testPrenotazione@a.it"),lista.get(0));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Test
    public void testFindAllByAnimale() throws NoSuchAlgorithmException {

        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione3@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione3@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testPrenotazione3@a.it"));
        animaleService.addAnimale(animale);

        paziente = pazienteService.getPazienteByEmail("testPrenotazione3@a.it");
        DottoreDTO d=dottoreService.getDottoreByEmail("testPrenotazione3@a.it");
        List<AnimaleDTO> lista= animaleService.findAllByPaziente(paziente);

        try {
            File myObj = new File("filename2.pdf");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("filename2.pdf");
            myWriter.write("Test file 2");
            myWriter.close();
            byte[] bytes = Files.readAllBytes(myObj.toPath());
            prescrizioneService.uploadFile(bytes,d,lista.get(0));
            ricevutaService.uploadFile(bytes,d,lista.get(0));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Assert.assertTrue(prescrizioneService.findAllByAnimale(lista.get(0)).size()>0);
        Assert.assertTrue(ricevutaService.findAllByAnimale(lista.get(0)).size()>0);

    }

    @Test
    public void testFindAllById() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testPrenotazione2@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPrenotazione2@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testPrenotazione2@a.it"));
        animaleService.addAnimale(animale);

        paziente = pazienteService.getPazienteByEmail("testPrenotazione2@a.it");
        DottoreDTO d=dottoreService.getDottoreByEmail("testPrenotazione2@a.it");
        List<AnimaleDTO> lista= animaleService.findAllByPaziente(paziente);

        try {
            File myObj = new File("filename2.pdf");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("filename2.pdf");
            myWriter.write("Test file 2");
            myWriter.close();
            byte[] bytes = Files.readAllBytes(myObj.toPath());
            prescrizioneService.uploadFile(bytes,d,lista.get(0));
            ricevutaService.uploadFile(bytes,d,lista.get(0));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        List<PrescrizioneDTO> prescrizioni = prescrizioneService.findAllByAnimale(lista.get(0));
        PrescrizioneDTO risultato = prescrizioneService.findAllById(prescrizioni.get(0).getId());
        Assert.assertEquals(prescrizioni.get(0).getId(), risultato.getId());

        List<RicevutaDTO> ricevute = ricevutaService.findAllByAnimale(lista.get(0));
        RicevutaDTO risultato2 = ricevutaService.findAllById(ricevute.get(0).getId());
        Assert.assertEquals(ricevute.get(0).getId(), risultato2.getId());
    }
}
