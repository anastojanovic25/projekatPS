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
public class Glumi extends AbstractDomainObject{
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

    @Override
    public String tableName() {
        return "glumi";
    }

    @Override
    public String alijas() {
        return "g";
    }

    @Override
    public String join() {
        return "JOIN ULOGA U ON G.ULOGA=U.ID JOIN GLUMCI GL ON GL.JMBG=G.JMBG";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs, Object o) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            long jmbg=rs.getLong("jmbg");
            String ime=rs.getString("ime");
            String prezime=rs.getString("prezime");
            long idUloge=rs.getLong("id");
            String nazivUloge=rs.getString("naziv");
            Uloga u=new Uloga(idUloge, nazivUloge, (Predstava) o);
            Glumac g=new Glumac(jmbg, ime, prezime);
            Long id=rs.getLong("idGlumi");
            Glumi gl =new Glumi(g, u,id);
            lista.add(gl);
        }

        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(JMBG,ULOGA)";
    }

    @Override
    public String uslov() {
        return "idGlumi= "+id;
    }

    @Override
    public String valuesForInsert() {
        return g.getJmbg()+","+uloga.getIdUloge();
    }

    @Override
    public String valuesForUpdate(Object o) {
        Glumac glumacNovi=(Glumac) o;
        return "jmbg="+glumacNovi.getJmbg();
    }

    @Override
    public String requirementForSelect(Object o) {
        Predstava p=(Predstava) o;
        return "WHERE U.IDPREDSTAVE="+p.getId();
    }

    @Override
    public String searchAttribute() {
        return "";
    }



    
}
