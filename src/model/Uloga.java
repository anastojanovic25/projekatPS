/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ana
 */
public class Uloga {
    private long idUloge;
    private String naziv;
    private Predstava predstava;

    public Uloga() {
    }

    public Uloga(long idUloge, String naziv, Predstava predstava) {
        this.idUloge = idUloge;
        this.naziv = naziv;
        this.predstava = predstava;
    }

    public long getIdUloge() {
        return idUloge;
    }

    public void setIdUloge(long idUloge) {
        this.idUloge = idUloge;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Predstava getPredstava() {
        return predstava;
    }

    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    @Override
    public String toString() {
        return  naziv;
    }
    
    
}
