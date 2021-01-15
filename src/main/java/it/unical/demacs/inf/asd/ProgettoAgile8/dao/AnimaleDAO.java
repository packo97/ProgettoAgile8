package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnimaleDAO extends JpaRepository<Animale, Long>, JpaSpecificationExecutor<Animale> {

    List<Animale> findAll();

    List<Animale> findAllByPaziente(Paziente paziente);

    @Modifying
    @Query("update Animale a set a.nome = ?1, a.data_nascita = ?2, a.tipo= ?3, a.genere= ?4, a.peso= ?5, a.altezza= ?6 where a.paziente = ?7 and a.id=?8")
    void updateAnimale(String nome, LocalDate date, String tipo, String genere, int peso, int altezza, Paziente p, Long id);

}
