/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ana
 */
public class GlumacUloga {
    private String uloga;
    private Glumac glumac;

    public GlumacUloga() {
    }

    public GlumacUloga(String uloga, Glumac glumac) {
        this.uloga = uloga;
        this.glumac = glumac;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public Glumac getGlumac() {
        return glumac;
    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }
    
}
