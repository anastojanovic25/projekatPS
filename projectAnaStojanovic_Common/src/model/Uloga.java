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
public class Uloga extends AbstractDomainObject{
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
    
      @Override
    public String tableName() {
        return "uloga";
    }

    @Override
    public String alijas() {
        return "U";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs, Object o) throws SQLException {
         return null;
    }

    @Override
    public String columnsForInsert() {
        return "(NAZIV,IDPREDSTAVE)";
    }

    @Override
    public String uslov() {
        return "idUloge"+idUloge;
    }

    @Override
    public String valuesForInsert() {
        return "'"+naziv + "','" + predstava.getId() +"'";
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
        return "";
    }
}
