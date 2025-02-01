/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ana
 */
public class Predstava extends AbstractDomainObject{
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
    
    @Override
    public String tableName() {
        return "predstava";
    }

    @Override
    public String alijas() {
        return "p";
    }

    @Override
    public String join() {
        return "JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER R ON P.REZISER=R.ID JOIN SCENOGRAF S ON P.SCENOGRAF=S.JMBG JOIN KOREOGRAF KR ON P.KOREOGRAF=KR.JMBG JOIN KOSTIMOGRAF KO ON P.KOSTIMOGRAF=KO.JMBG";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs, Object o) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

           while(rs.next()){
                long idPr=rs.getLong("id");
                long idR=rs.getLong("reziser");
                int tr=rs.getInt("trajanje");
                
                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
                long id=rs.getLong("idKorisnika");
                long jmbgS=rs.getLong("scenograf");
                long jmbgKor=rs.getLong("koreograf");
                long jmbgKos=rs.getLong("kostimograf");
                String naziv=rs.getString("naziv");
                String email=rs.getString("EMAIL");
                String pass=rs.getString("PASSWORD");
                String ime=rs.getString("IME");
                String prezime=rs.getString("PREZIME");
                String num=rs.getString("NUMBER");
                String gender=rs.getString("GENDER");
                String ri=rs.getString("imeR");
                String rpr=rs.getString("prezimeR");
                String si=rs.getString("imeS");
                String sp=rs.getString("prezimeS");
                String koi=rs.getString("imeKos");
                String kop=rs.getString("prezimeKos");
                String koreografime=rs.getString("imeKor");
                String krp=rs.getString("prezimeKor");
                
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                
               Reziser reziser=new Reziser(idR, ri, rpr);
               Scenograf scenograf=new Scenograf(jmbgS, si, sp);
               Koreograf koreograf=new Koreograf(jmbgKor, koreografime, krp);
               Kostimograf kosimograf=new Kostimograf(jmbgKos, koi, kop);
               Predstava pr=new Predstava(idPr, naziv, reziser, scenograf, koreograf, kosimograf, tr, zanr, k);
               lista.add(pr);
            }
        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(NAZIV,REZISER,TRAJANJE,ZANR,IDKORISNIKA,SCENOGRAF,KOSTIMOGRAF,KOREOGRAF)";
    }

    @Override
    public String uslov() {
        return "id= "+id;
    }

    @Override
    public String valuesForInsert() {
        return "'"+naziv + "','" + reziser.getId()+ "','" + trajanje + "','" + zanr  + "','" + korisnikUnos.getId() + "','"+ scenograf.getJmbg()+ "','"+kostimograf.getJmbg() + "','"+koreograf.getJmbg()+"'";
    }

    @Override
    public String valuesForUpdate(Object o) {
        return "";
    }

    @Override
    public String requirementForSelect(Object o) {
        return "";
    }

    @Override
    public String atributPretrazivanja() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
