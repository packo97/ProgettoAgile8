package it.unical.demacs.inf.asd.ProgettoAgile8;



import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;
import org.junit.Assert;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PazienteServiceTest {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PazienteService pazienteService;
    @Autowired
    private PazienteDAO pazienteDAO;

    @Test
    public void testAdd() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        Paziente d = pazienteDAO.findAllByEmail("testPaz@a.it");
        Assert.assertEquals("testPaz@a.it", d.getEmail());
    }

    @Test
    public void testLogin() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz8@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        Assert.assertTrue(pazienteService.login("testPaz8@a.it","abc"));
    }

    @Test
    public void testGetAll() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz800@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        List<PazienteDTO> list = pazienteService.getPazienti();
        Assert.assertTrue(list.size()>0);

    }

    @Test
    public void testFindAllById() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPazbyid@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        Paziente d = pazienteDAO.findAllByEmail("testPazbyid@a.it");
        paziente = pazienteService.findAllById(d.getId());
        Assert.assertTrue(paziente.getEmail().equals("testPazbyid@a.it"));

    }

    @Test
    public void testRicerca() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz2@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        List<PazienteDTO> lista= pazienteService.ricerca("Mar");
        Assert.assertTrue(lista.size()>0);
    }

    @Test
    public void testModificaPass() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz3@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        RecuperaPasswordDTO rec=new RecuperaPasswordDTO();
        rec.setEmail("testPaz3@a.it");
        rec.setNuovaPassword("nuova");
        rec.setCodice("123");
        rec.setTipo_login("paziente");
        pazienteService.modificaPassword(rec);

        Paziente p=pazienteDAO.findAllByEmail("testPaz3@a.it");
        Assert.assertTrue(pazienteService.controllaPassword("nuova",modelMapper.map(p,PazienteDTO.class)));
    }

    @Test
    public void testUpdate() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz4@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        paziente = modelMapper.map(pazienteDAO.findAllByEmail("testPaz4@a.it"), PazienteDTO.class);
        paziente.setNome("Nomeupdated");

        pazienteService.updatePaziente(paziente);

        Assert.assertEquals("Nomeupdated", pazienteDAO.findAllByEmail("testPaz4@a.it").getNome());

    }

    @Test
    public void testControllaPassword() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz5@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        Assert.assertTrue(pazienteService.controllaPassword("abc", paziente));
    }

    @Test
    public void testControllaPasswordSbagliata() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz6@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        Assert.assertFalse(pazienteService.controllaPassword("wrong", paziente));
    }

    @Test
    public void testUpdatePass() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPaz7@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        pazienteService.updatePassword("nuova", paziente);
        Assert.assertTrue(pazienteService.controllaPassword("nuova", modelMapper.map(pazienteDAO.findAllByEmail("testPaz7@a.it"), PazienteDTO.class)));
    }

    @Test
    public void testUpdateIMG() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testPazIMG@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);
        paziente = pazienteService.getPazienteByEmail("testPazIMG@a.it");

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
            pazienteService.updateImg(bytes,paziente.getId());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
