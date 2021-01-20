package it.unical.demacs.inf.asd.ProgettoAgile8;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.AnimaleService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnimaleServiceTest {

    @Autowired
    private AnimaleService animaleService;

    @Autowired
    private PazienteService pazienteService;

    @Test
    public void testAdd() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testAn3@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);

        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testAn3@a.it"));
        animaleService.addAnimale(animale);
        Assert.assertTrue(animaleService.findAllByPaziente(pazienteService.getPazienteByEmail("testAn3@a.it")).size()>0);

    }

    @Test
    public void testFindAll() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testAn@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);

        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testAn@a.it"));
        animaleService.addAnimale(animale);
        Assert.assertTrue(animaleService.findAllByPaziente(pazienteService.getPazienteByEmail("testAn@a.it")).size()>0);
    }

    @Test
    public void testUpdate() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testAn2@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);

        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testAn2@a.it"));
        animaleService.addAnimale(animale);

        List<AnimaleDTO> a= animaleService.findAllByPaziente(pazienteService.getPazienteByEmail("testAn2@a.it"));
        a.get(0).setNome("billyModified");
        animaleService.updateAnimale(a.get(0));
        Assert.assertEquals("billyModified",animaleService.findAllByPaziente(pazienteService.getPazienteByEmail("testAn2@a.it")).get(0).getNome() );
    }

    @Test
    public void testDelete() throws NoSuchAlgorithmException {
        PazienteDTO paziente = new PazienteDTO();
        paziente.setCodice_fiscale("lvrtzn98c43h579z");
        paziente.setCognome("Rossi");
        paziente.setEmail("testAn21@a.it");
        paziente.setNome("Mario");
        paziente.setNumero_telefono("212121212");
        paziente.setPassword("abc");
        paziente.setSalt("32");

        pazienteService.addPaziente(paziente);

        AnimaleDTO animale= new AnimaleDTO();
        animale.setNome("billy");
        animale.setPaziente(pazienteService.getPazienteByEmail("testAn21@a.it"));
        animaleService.addAnimale(animale);

        List<AnimaleDTO> a= animaleService.findAllByPaziente(pazienteService.getPazienteByEmail("testAn21@a.it"));

        int sizebefore = a.size();
        animaleService.deleteAnimale(a.get(0).getId());
        int sizeafter = animaleService.findAllByPaziente(pazienteService.getPazienteByEmail("testAn21@a.it")).size();
        sizebefore--;
        Assert.assertEquals(sizebefore,sizeafter );
    }
}
