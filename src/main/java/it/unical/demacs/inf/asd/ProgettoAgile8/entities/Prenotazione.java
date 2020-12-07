package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "PRENOTAZIONE")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="data_visita")
    private LocalDateTime data_visita;

    @Column(name="descrizione", length=500, nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name="PAZIENTE_ID",referencedColumnName = "ID")
    private Paziente paziente;

    @ManyToOne
    @JoinColumn(name="DOTTORE_ID",referencedColumnName = "ID")
    private Dottore dottore;
}
