package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Segretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegretariaDAO extends JpaRepository<Segretaria, Long> {


    Segretaria findAllByEmailAndPassword(String email, String password);

    Segretaria findAllByEmail(String email);
}
