package model;

import java.util.List;

public class Persona {
    private String nome;
    private String cognome;
    private Indirizzo indirizzo;
    private List<Cane> cani;

    public Persona(String nome, String cognome, Indirizzo indirizzo, List<Cane> cani) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.cani = cani;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Cane> getCani() {
        return cani;
    }

    public void setCani(List<Cane> cani) {
        this.cani = cani;
    }
}
