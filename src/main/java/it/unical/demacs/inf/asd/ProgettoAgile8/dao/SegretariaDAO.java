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
    @Query("update Segretaria s set s.nome = ?1, s.cognome = ?2, s.codice_fiscale= ?3, s.numero_telefono= ?4 where s.id = ?5")
    void updateSegretaria(String nome, String cognome, String codice_fiscale, String numero_telefono, Long id);

    @Modifying
    @Query("update Segretaria s set s.password = ?1 where s.id = ?2")
    void updatePassword1(String passwordNuova, Long id);

    @Modifying
    @Query("update Segretaria s set s.img = ?1 where s.id = ?2")
    void updateImg(byte[] img, Long id);

    @Modifying
    @Query("update Segretaria p set p.password = ?1, p.salt = ?2 where p.email = ?3")
    void updatePassword2(String password, String salt, String email);

}
