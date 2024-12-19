/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ana
 */
public class Glumi {
    private Glumac g;
    private Uloga uloga; 
    private long id;

    public Glumi() {
    }

    public Glumi(Glumac g, Uloga uloga,long id) {
        this.g = g;
        this.uloga = uloga;
        this.id=id;
    }

    public Glumac getG() {
        return g;
    }

    public void setG(Glumac g) {
        this.g = g;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    
}
