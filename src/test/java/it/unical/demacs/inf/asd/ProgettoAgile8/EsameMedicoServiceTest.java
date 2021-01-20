package it.unical.demacs.inf.asd.ProgettoAgile8;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.*;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.AnimaleService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.Esame_medicoService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;
import org.junit.Assert;
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
public class EsameMedicoServiceTest {

    @Autowired
    Esame_medicoService esame_medicoService;

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
        dottore.setEmail("testEsami@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testEsami@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testEsami@a.it"));
        animaleService.addAnimale(animale);

        paziente = pazienteService.getPazienteByEmail("testEsami@a.it");
        DottoreDTO d=dottoreService.getDottoreByEmail("testEsami@a.it");
        List<AnimaleDTO> lista= animaleService.findAllByPaziente(paziente);
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
            esame_medicoService.uploadFile(bytes,d,lista.get(0),"test");
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
        dottore.setEmail("testEsami2@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testEsami2@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testEsami2@a.it"));
        animaleService.addAnimale(animale);

        paziente = pazienteService.getPazienteByEmail("testEsami2@a.it");
        DottoreDTO d=dottoreService.getDottoreByEmail("testEsami2@a.it");
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
            esame_medicoService.uploadFile(bytes,d,lista.get(0), "test");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Assert.assertTrue(esame_medicoService.findAllByAnimale(lista.get(0)).size()>0);

    }

    @Test
    public void testFindAllById() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testEsami3@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testEsami3@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testEsami3@a.it"));
        animaleService.addAnimale(animale);

        paziente = pazienteService.getPazienteByEmail("testEsami3@a.it");
        DottoreDTO d=dottoreService.getDottoreByEmail("testEsami3@a.it");
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
            esame_medicoService.uploadFile(bytes,d,lista.get(0), "test");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        List<Esame_medicoDTO> esami = esame_medicoService.findAllByAnimale(lista.get(0));
        Esame_medicoDTO risultato = esame_medicoService.findAllById(esami.get(0).getId());
        //System.out.println(esami.get(0).getId() + " " + risultato.getId());
        Assert.assertEquals(esami.get(0).getId(), risultato.getId());

    }
}
