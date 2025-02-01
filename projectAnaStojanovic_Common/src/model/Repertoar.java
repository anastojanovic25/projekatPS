/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class Repertoar extends AbstractDomainObject{
    private Predstava predstava;
    private Date datum;
    private String vreme;
    private String sala;

    public Repertoar() {
    }

    public Repertoar(Predstava predstava, Date datum, String vreme, String sala) {
        this.predstava = predstava;
        this.datum = datum;
        this.vreme = vreme;
        this.sala = sala;
    }

    

    public Predstava getPredstava() {
        return predstava;
    }

    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }
    public static String formatDate(Date datum) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(datum);
    }
    @Override
    public String tableName() {
        return "repertoar";
    }

    @Override
    public String alijas() {
        return "R";
    }

    @Override
    public String join() {
        return "JOIN PREDSTAVA P ON R.IDPREDSTAVE=P.ID JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER RE ON P.REZISER=RE.ID JOIN SCENOGRAF S ON P.SCENOGRAF=S.JMBG JOIN KOREOGRAF KR ON P.KOREOGRAF=KR.JMBG JOIN KOSTIMOGRAF KO ON P.KOSTIMOGRAF=KO.JMBG";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs, Object o) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

           while(rs.next()){
                long idPr=rs.getLong("id");
                long idR=rs.getLong("reziser");
                String naziv=rs.getString("naziv");
                int tr=rs.getInt("trajanje");
                long id=rs.getLong("idKorisnika");
                String email=rs.getString("EMAIL");
                String pass=rs.getString("PASSWORD");
                String ime=rs.getString("IME");
                String prezime=rs.getString("PREZIME");
                String num=rs.getString("NUMBER");
                String gender=rs.getString("GENDER");
                String ri=rs.getString("imeR");
                String rpr=rs.getString("prezimeR");
                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
               
                String si=rs.getString("imeS");
                String sp=rs.getString("prezimeS");
                String koi=rs.getString("imeKos");
                String kop=rs.getString("prezimeKos");
                String koreografime=rs.getString("imeKor");
                String krp=rs.getString("prezimeKor");
                long jmbgS=rs.getLong("scenograf");
                long jmbgKor=rs.getLong("koreograf");
                long jmbgKos=rs.getLong("kostimograf");
                
                
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                
               Reziser reziser=new Reziser(idR, ri, rpr);
               Scenograf scenograf=new Scenograf(jmbgS, si, sp);
               Koreograf koreograf=new Koreograf(jmbgKor, koreografime, krp);
               Kostimograf kosimograf=new Kostimograf(jmbgKos, koi, kop);
               Predstava pr=new Predstava(idPr, naziv, reziser, scenograf, koreograf, kosimograf, tr, zanr, k);
               
              
               String sala=rs.getString("sala");
               java.sql.Date datumSql=rs.getDate("datum");
               java.util.Date datum=new java.util.Date(datumSql.getTime());
               String vreme = rs.getString("vreme");
               
               Repertoar r=new Repertoar(pr, datum, vreme, sala);
               lista.add(r);
            }
        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(IDPREDSTAVE,DATUM,SALA,VREME)";
    }

    @Override
    public String uslov() { 
        java.sql.Date datumSql= new java.sql.Date(datum.getTime());
        return "IDPREDSTAVE="+predstava.getId()+" AND DATUM='"+datumSql+"' AND SALA="+sala+" AND VREME='"+vreme+"'";
        
    }

    @Override
    public String valuesForInsert() {
        java.sql.Date datumSql= new java.sql.Date(datum.getTime());
        return predstava.getId() + ",'" + datumSql+ "'," + sala + ",'" + vreme+"'";
    }

    @Override
    public String valuesForUpdate(Object o) {
        Repertoar rep=(Repertoar) o;
        java.sql.Date datumSql= new java.sql.Date(rep.getDatum().getTime());
        return "SALA="+rep.getSala()+", VREME= '"+rep.getVreme()+"', DATUM='"+datumSql+"'";
    }

    @Override
    public String requirementForSelect(Object o) {//order by itd
        if(o instanceof Predstava){
            Predstava p=(Predstava) o;
            return " WHERE P.ID="+ p.getId();
        }else if(o instanceof String){
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String datum=(String) o;
            java.util.Date utilDate;
            try {
                utilDate = formatter.parse(datum);
                
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            return " WHERE R.DATUM='"+sqlDate+"'";
            } catch (ParseException ex) {
                Logger.getLogger(Repertoar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(o instanceof HashMap){
            HashMap<String, List<Object>> objekti=(HashMap<String, List<Object>>) o;
             String key=objekti.keySet().iterator().next();
            List<Object> listaObjekata=objekti.get(key);
            Predstava p=(Predstava) listaObjekata.get(0);
            String datum=(String) listaObjekata.get(1);
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            java.util.Date utilDate;
            try {
                utilDate = formatter.parse(datum);
                
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            return " WHERE P.ID="+p.getId()+" AND R.DATUM='"+sqlDate+"'";
            } catch (ParseException ex) {
                Logger.getLogger(Repertoar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return " ORDER BY DATUM ASC,VREME ASC";
    }

    @Override
    public String atributPretrazivanja() {
        return "";
    }
}
