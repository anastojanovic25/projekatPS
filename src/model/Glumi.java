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
    private Predstava p;
    private Glumac g;

    public Glumi() {
    }

    public Glumi(Predstava p, Glumac g) {
        this.p = p;
        this.g = g;
    }

    public Predstava getP() {
        return p;
    }

    public void setP(Predstava p) {
        this.p = p;
    }

    public Glumac getG() {
        return g;
    }

    public void setG(Glumac g) {
        this.g = g;
    }
    
    
}
