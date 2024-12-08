/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Ana
 */
public class Repertoar {
    private Predstava predstava;
    private Date datum;
    private String vreme;
    private String sala;

    public Repertoar() {
    }

    public Repertoar(Predstava predstava, Date datum, String vreme, String sala) {
        this.predstava = predstava;
        this.datum = datum;
        this.vreme = vreme;
        this.sala = sala;
    }

    

    public Predstava getPredstava() {
        return predstava;
    }

    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }
    
}
