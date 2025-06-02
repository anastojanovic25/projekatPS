/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class Kostimograf extends AbstractDomainObject{
       private long jmbg;
    private String ime;
    private String prezime;

    public Kostimograf() {
    }

    public Kostimograf(long jmbg, String ime, String prezime) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
    }

  

    public long getJmbg() {
        return jmbg;
    }

    public void setJmbg(long jmbg) {
        this.jmbg = jmbg;
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

    @Override
    public String toString() {
        return ime+" "+prezime; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
       @Override
    public String tableName() {
        return "kostimograf";
    }

    @Override
    public String alijas() {
        return "KO";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs, Object o) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            long jmbg=rs.getLong("jmbg");
            String ime=rs.getString("imeKos");
              String prezime=rs.getString("prezimeKos");
              Kostimograf k=new Kostimograf(jmbg, ime, prezime);
            lista.add(k);  
        }  
        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return "jmbg= "+jmbg;
    }

    @Override
    public String valuesForInsert() {
        return "";
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
    public String searchAttribute() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
       @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kostimograf that = (Kostimograf) o;
        return jmbg == that.jmbg;
    }
}
