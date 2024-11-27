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
    private Korisnik korisnikUnos;
    private int trajanje;
    private Date datumOdrzavanja;
    private Zanr zanr;

    public Predstava() {
    }

    public Predstava(long id, String naziv, Korisnik korisnikUnos, int trajanje, Date datumOdrzavanja, Zanr zanr) {
        this.id = id;
        this.naziv = naziv;
        this.korisnikUnos = korisnikUnos;
        this.trajanje = trajanje;
        this.datumOdrzavanja = datumOdrzavanja;
        this.zanr = zanr;
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

    public Date getDatumOdrzavanja() {
        return datumOdrzavanja;
    }

    public void setDatumOdrzavanja(Date datumOdrzavanja) {
        this.datumOdrzavanja = datumOdrzavanja;
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
    
    
    
    
}
