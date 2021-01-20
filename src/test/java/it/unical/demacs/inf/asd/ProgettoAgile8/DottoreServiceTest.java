package it.unical.demacs.inf.asd.ProgettoAgile8;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import org.junit.Assert;
import org.junit.BeforeClass;
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
public class DottoreServiceTest {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DottoreService dottoreService;
    @Autowired
    private DottoreDAO dottoreDAO;

    @BeforeClass
    public static void testDB(){

        System.out.println("testdb");
    }

    @Test
    public void testAdd() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        Dottore d = dottoreDAO.findAllByEmail("testServ@a.it");
        Assert.assertEquals("testServ@a.it", d.getEmail());
    }

    @Test
    public void testGetAll() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ123@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        List<DottoreDTO> list = dottoreService.getAll();
        Assert.assertTrue(list.size()>0);

    }

    @Test
    public void testLogin() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ7@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        Dottore d = dottoreDAO.findAllByEmail("testServ7@a.it");
        Assert.assertTrue(dottoreService.login("testServ7@a.it","abc"));
    }

    @Test
    public void testLoginWrongCredentials() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ8@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        Dottore d = dottoreDAO.findAllByEmail("testServ8@a.it");
        Assert.assertFalse(dottoreService.login("testServ8@a.it","passsbagliata"));
    }

    @Test
    public void testModificaPass() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ2@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");
        dottoreService.addDottore(dottore);

        RecuperaPasswordDTO rec=new RecuperaPasswordDTO();
        rec.setEmail("testServ2@a.it");
        rec.setNuovaPassword("nuova");
        rec.setCodice("123");
        rec.setTipo_login("dottore");
        dottoreService.modificaPassword(rec);

        Dottore d=dottoreDAO.findAllByEmail("testServ2@a.it");
        Assert.assertNotEquals("abc", d.getPassword());

    }

    @Test
    public void testUpdate() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ3@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        dottore = modelMapper.map(dottoreDAO.findAllByEmail("testServ3@a.it"), DottoreDTO.class);
        dottore.setNome("Nomeupdated");

        dottoreService.updateDottore(dottore);

        Assert.assertEquals("Nomeupdated", dottoreDAO.findAllByEmail("testServ3@a.it").getNome());

    }

    @Test
    public void testControllaPassword() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ4@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        Assert.assertTrue(dottoreService.controllaPassword("abc", dottore));
    }

    @Test
    public void testControllaPasswordWrongPassword() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ5@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        Assert.assertFalse(dottoreService.controllaPassword("wrong", dottore));
    }

    @Test
    public void testUpdatePass() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServ6@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        dottoreService.updatePassword("nuova", dottore);
        Assert.assertFalse(dottoreService.controllaPassword("abc", modelMapper.map(dottoreDAO.findAllByEmail("testServ6@a.it"), DottoreDTO.class)));
    }

    @Test
    public void testUpdateIMG() throws NoSuchAlgorithmException {
        DottoreDTO dottore = new DottoreDTO();
        dottore.setCodice_fiscale("lvrtzn98c43h579z");
        dottore.setCodice_identificativo("3");
        dottore.setCognome("Rossi");
        dottore.setEmail("testServIMG@a.it");
        dottore.setNome("Mario");
        dottore.setNumero_telefono("212121212");
        dottore.setPassword("abc");
        dottore.setSalt("32");

        dottoreService.addDottore(dottore);
        dottore = dottoreService.getDottoreByEmail("testServIMG@a.it");

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
            dottoreService.updateImg(bytes,dottore.getId());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
