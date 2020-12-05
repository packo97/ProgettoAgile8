package it.unical.demacs.inf.asd.ProgettoAgile8.entities;

import javax.persistence.*;

@Entity
@Table(name = "PAZIENTE")
public class Paziente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "cognome", length = 50, nullable = false)
    private String cognome;

    @Column(name = "codice_fiscale", length = 16, nullable = false)
    private String codice_fiscale;

    @Column(name="numero_telefono", length = 10, nullable = false)
    private String numero_telefono;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "animale", length = 50, nullable = false)
    private String animale;

}
