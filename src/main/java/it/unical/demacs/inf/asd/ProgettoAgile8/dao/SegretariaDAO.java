package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Segretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SegretariaDAO extends JpaRepository<Segretaria, Long> {


    Segretaria findAllByEmailAndPassword(String email, String password);

    Segretaria findAllByEmail(String email);

    @Modifying
    @Query("update Segretaria p set p.password = ?1, p.salt = ?2 where p.email = ?3")
    void updatePassword(String password, String salt, String email);

}
