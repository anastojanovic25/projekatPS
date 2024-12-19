/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Ana
 */
public class Predstava {
    private long id;
    private String naziv;
    private Reziser reziser;
    private Scenograf scenograf;
    private Koreograf koreograf;
    private Kostimograf kostimograf;
    private int trajanje;
    private Zanr zanr;
    private Korisnik korisnikUnos;
    
    public Predstava() {
    }

    public Predstava(long id, String naziv, Reziser reziser, Scenograf scenograf, Koreograf koreograf, Kostimograf kostimograf, int trajanje, Zanr zanr, Korisnik korisnikUnos) {
        this.id = id;
        this.naziv = naziv;
        this.reziser = reziser;
        this.scenograf = scenograf;
        this.koreograf = koreograf;
        this.kostimograf = kostimograf;
        this.trajanje = trajanje;
        this.zanr = zanr;
        this.korisnikUnos = korisnikUnos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Korisnik getKorisnikUnos() {
        return korisnikUnos;
    }

    public void setKorisnikUnos(Korisnik korisnikUnos) {
        this.korisnikUnos = korisnikUnos;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    @Override
    public String toString() {
        return naziv ;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Reziser getReziser() {
        return reziser;
    }

    public void setReziser(Reziser reziser) {
        this.reziser = reziser;
    }

    public Scenograf getScenograf() {
        return scenograf;
    }

    public void setScenograf(Scenograf scenograf) {
        this.scenograf = scenograf;
    }

    public Koreograf getKoreograf() {
        return koreograf;
    }

    public void setKoreograf(Koreograf koreograf) {
        this.koreograf = koreograf;
    }

    public Kostimograf getKostimograf() {
        return kostimograf;
    }

    public void setKostimograf(Kostimograf kostimograf) {
        this.kostimograf = kostimograf;
    }
    
    
    
    
}
