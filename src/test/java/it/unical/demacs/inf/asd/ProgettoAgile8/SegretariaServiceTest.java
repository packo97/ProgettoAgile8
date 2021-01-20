package it.unical.demacs.inf.asd.ProgettoAgile8;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.SegretariaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Segretaria;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.SegretariaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SegretariaServiceTest {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SegretariaService segretariaService;
    @Autowired
    private SegretariaDAO segretariaDAO;


    @Test
    public void testAdd() throws NoSuchAlgorithmException {

        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegr@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");
        segretariaService.addSegretaria(segr);
        Segretaria d = segretariaDAO.findAllByEmail("testSegr@a.it");
        Assert.assertEquals("testSegr@a.it", d.getEmail());
    }

    @Test
    public void testLogin() throws NoSuchAlgorithmException {
        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegr@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");


        segr.setEmail("testSegr2@a.it");
        segretariaService.addSegretaria(segr);
        Assert.assertTrue(segretariaService.login("testSegr2@a.it","abc"));
    }

    @Test
    public void testLoginWrongCredentials(){
        Assert.assertFalse(segretariaService.login("testSegr2@a.it","passsbagliata"));
    }

    @Test
    public void testModificaPass() throws NoSuchAlgorithmException {
        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegr@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");
        segr.setEmail("testSegr3@a.it");
        segretariaService.addSegretaria(segr);
        RecuperaPasswordDTO rec=new RecuperaPasswordDTO();
        rec.setEmail("testSegr3@a.it");
        rec.setNuovaPassword("nuova");
        rec.setCodice("123");
        rec.setTipo_login("segretaria");
        segretariaService.modificaPassword(rec);
        Assert.assertFalse(segretariaService.login("testSegr3@a.it","abc"));

    }

    @Test
    public void testUpdate() throws NoSuchAlgorithmException {
        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegr@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");


        segr.setEmail("testSegr4@a.it");
        segretariaService.addSegretaria(segr);
        segr = modelMapper.map(segretariaDAO.findAllByEmail("testSegr4@a.it"), SegretariaDTO.class);
        segr.setNome("Nomeupdated");

        segretariaService.updateSegretaria(segr);

        Assert.assertEquals("Nomeupdated", segretariaDAO.findAllByEmail("testSegr4@a.it").getNome());

    }

    @Test
    public void testControllaPassword() throws NoSuchAlgorithmException {
        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegr@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");


        segr.setEmail("testSegr5@a.it");
        segretariaService.addSegretaria(segr);
        segr = modelMapper.map(segretariaDAO.findAllByEmail("testSegr5@a.it"), SegretariaDTO.class);

        Assert.assertTrue(segretariaService.controllaPassword("abc", segr));
    }

    @Test
    public void testControllaPasswordWrong() throws NoSuchAlgorithmException {
        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegr@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");


        segr.setEmail("testSegr7@a.it");
        segretariaService.addSegretaria(segr);
        segr = modelMapper.map(segretariaDAO.findAllByEmail("testSegr7@a.it"), SegretariaDTO.class);

        Assert.assertFalse(segretariaService.controllaPassword("wrong", segr));
    }

    @Test
    public void testGetByEmail() throws NoSuchAlgorithmException {
        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegrByE@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");

        segretariaService.addSegretaria(segr);
        segr = segretariaService.getSegretariaByEmail("testSegrByE@a.it");

        Assert.assertTrue(segr.getEmail().equals("testSegrByE@a.it"));
    }

    @Test
    public void testUpdatePass() throws NoSuchAlgorithmException {
        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegr@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");


        segr.setEmail("testSegr6@a.it");
        segretariaService.addSegretaria(segr);
        segr = modelMapper.map(segretariaDAO.findAllByEmail("testSegr6@a.it"), SegretariaDTO.class);

        segretariaService.updatePassword("nuova", segr);
        Assert.assertFalse(segretariaService.controllaPassword("abc", modelMapper.map(segretariaDAO.findAllByEmail("testSegr6@a.it"), SegretariaDTO.class)));
    }

    @Test
    public void testUpdateImg() throws NoSuchAlgorithmException {

        SegretariaDTO segr = new SegretariaDTO();
        segr.setCodice_fiscale("lvrtzn98c43h579z");
        segr.setCognome("Rossi");
        segr.setEmail("testSegrIMG@a.it");
        segr.setNome("Mario");
        segr.setNumero_telefono("212121212");
        segr.setPassword("abc");
        segr.setSalt("32");
        segretariaService.addSegretaria(segr);
        Segretaria d = segretariaDAO.findAllByEmail("testSegrIMG@a.it");

        try {
            File myObj = new File("img.jpg");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("img.jpg");
            myWriter.write("Test file");
            myWriter.close();
            byte[] bytes = Files.readAllBytes(myObj.toPath());
            segretariaService.updateImg(bytes,d.getId());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
