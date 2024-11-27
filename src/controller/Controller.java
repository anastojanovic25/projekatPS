/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connection.DBBroker;
import java.util.Date;
import java.util.List;
import model.Korisnik;
import model.Predstava;
import model.Zanr;

/**
 *
 * @author Ana
 */
public class Controller {
    private static Controller instance;
    private DBBroker dbb;
    private Controller(){
        dbb=new DBBroker();
    }
    public static Controller getInstance(){
        if(instance==null)
            instance=new Controller();
        return instance;
    }

    public List<Korisnik> vratiListuKorisnika() {
        return dbb.vratiListuKorisnika();
    }

    public void ubaciKorisnika(String ime, String prezime, String broj, String email, String password, String pol) {
        dbb.ubaciKorisnika(ime, prezime, broj, email, password, pol);
    }

    public List<Predstava> vratiListuPredstava() {
        return dbb.vratiListuPredstava();
    }

    public void obrisiPredstavu(Predstava p) {
        dbb.obrisiPredstavu(p);
    }

    public void dodajPredstavu(String naziv, int trajanje, Date datum, Zanr zanr, Korisnik korisnik) {
        dbb.dodajPredstavu(naziv, trajanje, datum, zanr, korisnik);
    }

    public void azurirajPredstavu(long id, String naziv, int trajanje, String datumStr, Zanr zanr, Date datum, Korisnik korisnik) {
        dbb.azurirajPredstavu(id,naziv,trajanje,  datumStr,  zanr, datum, korisnik);
    }

   
    
}
