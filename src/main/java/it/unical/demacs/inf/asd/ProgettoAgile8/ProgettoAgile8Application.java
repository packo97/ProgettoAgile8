package it.unical.demacs.inf.asd.ProgettoAgile8;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PrenotazioneDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProgettoAgile8Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProgettoAgile8Application.class, args);

		PrenotazioneDAO prenotazioneDao = context.getBean(PrenotazioneDAO.class);
		ArrayList<Prenotazione> all = (ArrayList<Prenotazione>) prenotazioneDao.getConfermate(LocalDateTime.now());
		//ArrayList<Prenotazione> all = (ArrayList<Prenotazione>) prenotazioneDao.findByConfermatoTrueAndUrgenteFalseAndData_visitaGreaterThan(LocalDate.now());
		System.out.println(all.size());
		for(Prenotazione p: all){
			System.out.println((p.getData_visita()));
		}

	}

}
