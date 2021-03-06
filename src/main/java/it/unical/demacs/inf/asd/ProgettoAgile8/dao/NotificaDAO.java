package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificaDAO extends JpaRepository<Notifica, Long> {
    List<Notifica> findAllByPazienteAndRicevitoreOrderByDataDesc(Long paziente,String ricevitore);
    List<Notifica> findAllByRicevitoreOrderByDataDesc(String ricevitore);
    List<Notifica> findAllByDottoreIdAndRicevitoreOrderByDataDesc(Long dottoreId,String ricevitore);
    Notifica getAllById(Long id);
    @Modifying
    @Query("update Notifica n set n.vista = ?1 where n.paziente = ?2")
    void updateNotificheViste( Boolean value ,Long paziente);

    @Modifying
    @Query("update Notifica n set n.vista = true where n.ricevitore = 'segretaria'")
    void updateNotificheVisteBySegretaria();

    @Modifying
    @Query("update Notifica n set n.vista = true where n.dottoreId=?1 and n.ricevitore = 'dottore'")
    void updateNotificheVisteByDottoreId(Long dottoreid);
}
