/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ana
 */
public class Korisnik {
    private long id;
    private String email;
    private String password;
    private String ime;
    private String prezime;
    private String broj;
    private String pol;

    public Korisnik() {
    }

    public Korisnik(long id, String email, String password, String ime, String prezime, String broj, String pol) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.broj = broj;
        this.pol = pol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    @Override
    public String toString() {
        return email;
    }
    
    
}
