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
public class Glumac extends AbstractDomainObject{
    private long jmbg;
    private String ime;
    private String prezime;

    public Glumac() {
    }

    public Glumac(long jmbg, String ime, String prezime) {
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
    public String tableName() {
        return "glumci";
    }

    @Override
    public String alijas() {
        return "G";
    }

    @Override
    public String join() {
        return "JOIN GLUMI GL ON GL.JMBG=G.JMBG";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs, Object o) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
               long jmbg =rs.getLong("jmbg");
               String ime=rs.getString("ime");
               String prezime=rs.getString("prezime");
               
               Glumac glumac=new Glumac(jmbg, ime, prezime);
               lista.add(glumac);
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
        return "JMBG="+jmbg;
    }

    @Override
    public String valuesForInsert() {
        return jmbg+", '"+ime+"', '"+prezime+"'";
    }

    @Override
    public String valuesForUpdate(Object o) {
        return "ime='"+ime+"', prezime='"+prezime+"'" ;
    }

    @Override
    public String requirementForSelect(Object o) {
        if(o!=null){
            Predstava p=(Predstava) o;
            return "GL.IDPREDSTAVE="+p.getId();
        }else{
            return "";
        }
    }

    @Override
    public String atributPretrazivanja() {
        return "";
    }
    
}
