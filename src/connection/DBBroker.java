/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.util.ArrayList;
import java.util.List;
import model.Korisnik;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Glumac;
import model.Predstava;
import model.Repertoar;
import model.Reziser;
import model.Zanr;

/**
 *
 * @author Ana
 */
public class DBBroker {

    public List<Korisnik> vratiListuKorisnika() {
         List<Korisnik> lista=new ArrayList<>();
        try {
           
            String upit="SELECT * FROM KORISNICI";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long id=rs.getLong("id");
                String email=rs.getString("email");
                String pass=rs.getString("password");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                String num=rs.getString("number");
                String gender=rs.getString("gender");
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                lista.add(k);
                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lista;
    }

    public void ubaciKorisnika(String ime, String prezime, String broj, String email, String password, String pol) {
        String upit="INSERT INTO KORISNICI(EMAIL,PASSWORD, IME,PREZIME,NUMBER,GENDER) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, ime);
            ps.setString(4, prezime);
            ps.setString(5,broj);
            ps.setString(6,pol);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<Predstava> vratiListuPredstava() {
        List<Predstava> lista=new ArrayList<>();
          try {
           
            String upit="SELECT P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER,R.IME AS RI,R.PREZIME AS RPR FROM Predstava p JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER R ON P.REZISER=R.ID";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
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
                String ri=rs.getString("RI");
                String rpr=rs.getString("RPR");
                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
                
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                
               Reziser reziser=new Reziser(idR, ri, rpr);
               Predstava pr=new Predstava(idPr, naziv, reziser, tr, zanr, k);
               lista.add(pr);

                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }

    public void obrisiPredstavu(Predstava p) {
        String upit="DELETE FROM PREDSTAVA WHERE ID="+p.getId();
        try {
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            //PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
            //ps.setLong(1, p.getId());
           // ps.executeUpdate();
            
            s.executeUpdate(upit);
            System.out.println(upit);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void dodajPredstavu(String naziv, int trajanje, Zanr zanr, Reziser reziser, List<Glumac> selektovaniGlumci, Korisnik korisnik) {
         String upit="INSERT INTO PREDSTAVA(NAZIV,REZISER, TRAJANJE,ZANR, IDKORISNIKA) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, naziv);
            ps.setLong(2, reziser.getId());
            ps.setInt(3, trajanje);
            
            ps.setString(4, zanr+"");
         
            ps.setLong(5,korisnik.getId());
            
            ps.executeUpdate();
            ResultSet generatedKeys=ps.getGeneratedKeys();
            if(generatedKeys.next()){
                long id=generatedKeys.getLong(1);
                for (Glumac glumac : selektovaniGlumci) {
                    String upit2="INSERT INTO GLUMI(JMBG,IDPREDSTAVE) VALUES(?,?)";
                    PreparedStatement ps2=DBConnection.getInstance().getConnection().prepareStatement(upit2);
                    ps2.setLong(1, glumac.getJmbg());
                    ps2.setLong(2, id);
                    ps2.executeUpdate();
                    
                }
                
        
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    public void azurirajPredstavu(long id, String naziv, int trajanje, String datumStr, Zanr zanr, Date datum, Korisnik korisnik) {
        String upit="UPDATE PREDSTAVE SET naziv=?,korisnik=?,trajanje=?,datum=?,zanr=? WHERE ID=?;";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, naziv);
            ps.setInt(3, trajanje);
            ps.setLong(2, korisnik.getId());
            ps.setLong(6, id);
            java.sql.Date datumSql=new java.sql.Date(datum.getTime());
            ps.setDate(4, datumSql);
            ps.setString(5, zanr+"");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<Reziser> vratiListuRezisera() {
        List<Reziser> lista=new ArrayList();
          try {
           
            String upit="SELECT * FROM Reziser";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long id=rs.getLong("id");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
               Reziser r=new Reziser(id, ime, prezime);
               lista.add(r);

                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

    public List<Glumac> vratiListuGlumaca() {
        List<Glumac> lista=new ArrayList<>();
         try {
           
            String upit="SELECT * FROM Glumci";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long jmbg=rs.getLong("jmbg");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
               Glumac g=new Glumac(jmbg, ime, prezime);
               lista.add(g);

                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }

    public List<Repertoar> vratiListuRepertoara() {
        List<Repertoar> lista=new ArrayList<>();
         try {
          String upit="SELECT R.*,P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER,RE.IME AS RI,RE.PREZIME AS RPR FROM REPERTOAR R JOIN PREDSTAVA P ON R.IDPREDSTAVE=P.ID JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER RE ON P.REZISER=RE.ID ORDER BY DATUM ASC,VREME ASC";
          Statement s=DBConnection.getInstance().getConnection().createStatement();
          ResultSet rs=s.executeQuery(upit);
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
                String ri=rs.getString("RI");
                String rpr=rs.getString("RPR");
                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
                
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                
               Reziser reziser=new Reziser(idR, ri, rpr);
               Predstava pr=new Predstava(idPr, naziv, reziser, tr, zanr, k);
              
               String sala=rs.getString("sala");
               java.sql.Date datumSql=rs.getDate("datum");
               java.util.Date datum=new java.util.Date(datumSql.getTime());
               String vreme = rs.getString("vreme");
               
               Repertoar r=new Repertoar(pr, datum, vreme, sala);
               lista.add(r);
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void obrisiPredstavuURepertoaru(Repertoar r) {
        String upit="DELETE FROM REPERTOAR WHERE IDPREDSTAVE=? AND DATUM=? AND SALA=? AND VREME=?";
        try {
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
            
            ps.setLong(1, r.getPredstava().getId());
            java.sql.Date datumSQl= new java.sql.Date(r.getDatum().getTime());
            ps.setDate(2, datumSQl);
            ps.setString(3, r.getSala());
            ps.setString(4, r.getVreme());
            ps.executeUpdate();
         
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ubaciRepertoar(Predstava p, String sala, Date datum, String vreme) {
        try {
            String upit="INSERT INTO REPERTOAR VALUES(?,?,?,?)";
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, p.getId());
            java.sql.Date datumSql= new java.sql.Date(datum.getTime());
            ps.setDate(2,datumSql);
            ps.setString(3, sala);
            ps.setString(4,vreme);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void azurirajRepertoar(Predstava p, String sala, Date datum, String vreme) {
        try {
            String upit="INSERT INTO REPERTOAR VALUES(?,?,?,?)";
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, p.getId());
            java.sql.Date datumSql= new java.sql.Date(datum.getTime());
            ps.setDate(2,datumSql);
            ps.setString(3, sala);
            ps.setString(4,vreme);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Repertoar> vratiListuRepertoaraOdgovarajucePredstave(Predstava p) {
        List<Repertoar> lista=new ArrayList<>();
        String upit="SELECT * FROM REPERTOAR WHERE IDPREDSTAVE="+p.getId();
        try {
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                String sala=rs.getString("sala");
                String vreme=rs.getString("vreme");
                java.sql.Date datumSql=rs.getDate("datum");
                java.util.Date datum=new java.util.Date(datumSql.getTime());
                Repertoar r=new Repertoar(p, datum, vreme, sala);
                lista.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }

    public List<Repertoar> vratiListuRepertoaraDatum(String datum) throws ParseException {
        List<Repertoar> lista=new ArrayList<>();
         SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
              java.util.Date utilDate = formatter.parse(datum);
         java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
         String upit="SELECT R.*, P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER,RE.IME AS RI,RE.PREZIME AS RPR FROM REPERTOAR R JOIN PREDSTAVA P ON R.IDPREDSTAVE=P.ID JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER RE ON P.REZISER=RE.ID WHERE DATUM=?";
       
            PreparedStatement s=DBConnection.getInstance().getConnection().prepareStatement(upit);
            s.setDate(1, sqlDate);
            ResultSet rs=s.executeQuery();
            while(rs.next()){
                String sala=rs.getString("sala");
                String vreme=rs.getString("vreme");
                
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
                String ri=rs.getString("RI");
                String rpr=rs.getString("RPR");
                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
                
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                
                Reziser reziser=new Reziser(idR, ri, rpr);
                Predstava pr=new Predstava(idPr, naziv, reziser, tr, zanr, k);
                Repertoar rep=new Repertoar(pr, utilDate, vreme, sala);
                lista.add(rep);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
             
        
        return lista;
    }

    public List<Repertoar> vratiListuRepertoaraOba(String datum, Predstava p) {
        List<Repertoar> lista=new ArrayList<>();
         SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
              java.util.Date utilDate;
            try {
                utilDate = formatter.parse(datum);
                  java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
         String upit="SELECT * FROM REPERTOAR WHERE IDPREDSTAVE=? AND DATUM=?";
       
            PreparedStatement s=DBConnection.getInstance().getConnection().prepareStatement(upit);
            s.setLong(1, p.getId());
            s.setDate(2, sqlDate);
            ResultSet rs=s.executeQuery();
            while(rs.next()){
                String sala=rs.getString("sala");
                String vreme=rs.getString("vreme");
                
                Repertoar rep=new Repertoar(p, utilDate, vreme, sala);
                lista.add(rep);
            }
                
            } catch (ParseException ex) {
                Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
             
        
        return lista;
    }

    public List<Glumac> vratiListuGlumacaUPredstavi(Predstava predstava) {
        List<Glumac> lista=new ArrayList<>();
         try {
           
            String upit="SELECT G.* FROM GLUMI GL JOIN GLUMCI G ON GL.JMBG=G.JMBG WHERE GL.IDPREDSTAVE="+predstava.getId();
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long jmbg=rs.getLong("jmbg");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
               Glumac g=new Glumac(jmbg, ime, prezime);
               lista.add(g);

                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }

    public List<String> vratiEmailove() {
        List<String> lista=new ArrayList<>();
        String upit="SELECT EMAIL FROM KORISNICI";
        try {
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                String email=rs.getString("EMAIL");
                lista.add(email);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }
    
}
