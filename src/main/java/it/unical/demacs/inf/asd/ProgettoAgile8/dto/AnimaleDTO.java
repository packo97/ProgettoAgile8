package it.unical.demacs.inf.asd.ProgettoAgile8.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AnimaleDTO implements Serializable {

    private Long id;

    private String nome;

    private PazienteDTO paziente;

    private String tipo;

    private LocalDate data_nascita;

    private int peso;

    private int altezza;

    private String genere;

    private List<PrescrizioneDTO> prescrizioni;

    private List<RicevutaDTO> ricevute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PazienteDTO getPaziente() {
        return paziente;
    }

    public void setPaziente(PazienteDTO paziente) {
        this.paziente = paziente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(LocalDate data_nascita) {
        this.data_nascita = data_nascita;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public List<PrescrizioneDTO> getPrescrizioni() {
        return prescrizioni;
    }

    public void setPrescrizioni(List<PrescrizioneDTO> prescrizioni) {
        this.prescrizioni = prescrizioni;
    }

    public List<RicevutaDTO> getRicevute() {
        return ricevute;
    }

    public void setRicevute(List<RicevutaDTO> ricevute) {
        this.ricevute = ricevute;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
