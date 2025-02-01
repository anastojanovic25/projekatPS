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
public class Reziser extends AbstractDomainObject{
    private long id;
    private String ime;
    private String prezime;

    public Reziser() {
    }

    public Reziser(long id, String ime, String prezime) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return ime + " " + prezime;
    }
        @Override
    public String tableName() {
        return "reziser";
    }

    @Override
    public String alijas() {
        return "";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs, Object o) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            long id=rs.getLong("id");
            String ime=rs.getString("imeR");
            String prezime=rs.getString("prezimeR");
            Reziser k=new Reziser(id, ime, prezime);
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
        return "id= "+id;
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
    public String atributPretrazivanja() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
