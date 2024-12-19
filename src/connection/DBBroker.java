/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
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
import model.Glumi;
import model.Koreograf;
import model.Kostimograf;
import model.Predstava;
import model.Repertoar;
import model.Reziser;
import model.Scenograf;
import model.Uloga;
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
           
            String upit="SELECT P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER,R.IME AS RI,R.PREZIME AS RPR,S.IME AS SI, S.PREZIME AS SP, KR.IME AS KRI, KR.PREZIME AS KRP, KO.IME AS KOI, KO.PREZIME AS KOP FROM Predstava p JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER R ON P.REZISER=R.ID JOIN SCENOGRAF S ON P.SCENOGRAF=S.JMBG JOIN KOREOGRAF KR ON P.KOREOGRAF=KR.JMBG JOIN KOSTIMOGRAF KO ON P.KOSTIMOGRAF=KO.JMBG";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long idPr=rs.getLong("id");
                long idR=rs.getLong("reziser");
                long jmbgS=rs.getLong("scenograf");
                long jmbgKor=rs.getLong("koreograf");
                long jmbgKos=rs.getLong("kostimograf");
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
                String si=rs.getString("SI");
                String sp=rs.getString("SP");
                String koi=rs.getString("KOI");
                String kop=rs.getString("KOP");
                String koreografime=rs.getString("KRI");
                String krp=rs.getString("KRP");
                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
                
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                
               Reziser reziser=new Reziser(idR, ri, rpr);
               Scenograf scenograf=new Scenograf(jmbgS, si, sp);
               Koreograf koreograf=new Koreograf(jmbgKor, koreografime, krp);
               Kostimograf kosimograf=new Kostimograf(jmbgKos, koi, kop);
               Predstava pr=new Predstava(idPr, naziv, reziser, scenograf, koreograf, kosimograf, tr, zanr, k);
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

    
    public void dodajPredstavu(String naziv, int trajanje, Zanr zanr, Reziser reziser, List<Glumac> selektovaniGlumci, Korisnik korisnik, Scenograf scenograf, Koreograf kor, Kostimograf kom) {
         String upit="INSERT INTO PREDSTAVA(NAZIV,REZISER, TRAJANJE,ZANR, IDKORISNIKA,SCENOGRAF, KOSTIMOGRAF, KOREOGRAF) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, naziv);
            ps.setLong(2, reziser.getId());
            ps.setInt(3, trajanje);
            
            ps.setString(4, zanr+"");
         
            ps.setLong(5,korisnik.getId());
            ps.setLong(6, scenograf.getJmbg());
            ps.setLong(7, kom.getJmbg());
            ps.setLong(8, kor.getJmbg());
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
          String upit="SELECT R.*,P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER,RE.IME AS RI,RE.PREZIME AS RPR,S.IME AS SI, S.PREZIME AS SP, KR.IME AS KRI, KR.PREZIME AS KRP, KO.IME AS KOI, KO.PREZIME AS KOP FROM REPERTOAR R JOIN PREDSTAVA P ON R.IDPREDSTAVE=P.ID JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER RE ON P.REZISER=RE.ID JOIN SCENOGRAF S ON P.SCENOGRAF=S.JMBG JOIN KOREOGRAF KR ON P.KOREOGRAF=KR.JMBG JOIN KOSTIMOGRAF KO ON P.KOSTIMOGRAF=KO.JMBG ORDER BY DATUM ASC,VREME ASC";
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
               
                String si=rs.getString("SI");
                String sp=rs.getString("SP");
                String koi=rs.getString("KOI");
                String kop=rs.getString("KOP");
                String koreografime=rs.getString("KRI");
                String krp=rs.getString("KRP");
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
         String upit="SELECT P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER,R.IME AS RI,R.PREZIME AS RPR,S.IME AS SI, S.PREZIME AS SP, KR.IME AS KRI, KR.PREZIME AS KRP, KO.IME AS KOI, KO.PREZIME AS KOP FROM Predstava p JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER R ON P.REZISER=R.ID JOIN SCENOGRAF S ON P.SCENOGRAF=S.JMBG JOIN KOREOGRAF KR ON P.KOREOGRAF=KR.JMBG JOIN KOSTIMOGRAF KO ON P.KOSTIMOGRAF=KO.JMBG WHERE DATUM=?";
       
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
                
                String si=rs.getString("SI");
                String sp=rs.getString("SP");
                String koi=rs.getString("KOI");
                String kop=rs.getString("KOP");
                String koreografime=rs.getString("KRI");
                String krp=rs.getString("KRP");
                long jmbgS=rs.getLong("scenograf");
                long jmbgKor=rs.getLong("koreograf");
                long jmbgKos=rs.getLong("kostimograf");
                
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                
               Reziser reziser=new Reziser(idR, ri, rpr);
               Scenograf scenograf=new Scenograf(jmbgS, si, sp);
               Koreograf koreograf=new Koreograf(jmbgKor, koreografime, krp);
               Kostimograf kosimograf=new Kostimograf(jmbgKos, koi, kop);
               Predstava pr=new Predstava(idPr, naziv, reziser, scenograf, koreograf, kosimograf, tr, zanr, k);
               
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

    public List<Glumi> vratiListuGlumi(Predstava predstava) {
          List<Glumi> lista=new ArrayList<>();
         try {
            String upit="SELECT * FROM ULOGA U JOIN GLUMI G ON G.ULOGA=U.ID JOIN GLUMCI GL ON GL.JMBG=G.JMBG WHERE U.IDPREDSTAVE="+predstava.getId();
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long jmbg=rs.getLong("jmbg");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
                long idUloge=rs.getLong("id");
                String nazivUloge=rs.getString("naziv");
                Uloga u=new Uloga(idUloge, nazivUloge, predstava);
               Glumac g=new Glumac(jmbg, ime, prezime);
               Long id=rs.getLong("idGlumi");
               Glumi gl =new Glumi(g, u,id);
               lista.add(gl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return lista;
    }

    public List<Scenograf> vratiListuScenografa() {
         List<Scenograf> lista=new ArrayList();
          try {
            String upit="SELECT * FROM Scenograf";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long jmbg=rs.getLong("jmbg");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
               Scenograf r=new Scenograf(jmbg, ime, prezime);
               lista.add(r); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Kostimograf> vratiListuKostimografa() {
         List<Kostimograf> lista=new ArrayList();
          try {
            String upit="SELECT * FROM Kostimograf";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long jmbg=rs.getLong("jmbg");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
               Kostimograf r=new Kostimograf(jmbg, ime, prezime);
               lista.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Koreograf> vratiListuKoreografa() {
        List<Koreograf> lista=new ArrayList();
          try {
           
            String upit="SELECT * FROM Koreograf";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long jmbg=rs.getLong("jmbg");
                String ime=rs.getString("ime");
                String prezime=rs.getString("prezime");
               Koreograf k=new Koreograf(jmbg, ime, prezime);
               lista.add(k);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void dodajPredstavu2(String naziv, int trajanje, Zanr zanr, Reziser reziser, Scenograf scenograf1, Koreograf koreograf1, Kostimograf kostimograf1, List<Glumac> glumci, List<String> uloge, Korisnik korisnik) {
        String upitPredstava = "INSERT INTO PREDSTAVA(NAZIV,REZISER,TRAJANJE,ZANR,IDKORISNIKA,SCENOGRAF,KOSTIMOGRAF,KOREOGRAF) VALUES (?,?,?,?,?,?,?,?)";
        String upitUloga = "INSERT INTO ULOGA(NAZIV,IDPREDSTAVE) VALUES(?,?)";
        String upitGlumi = "INSERT INTO GLUMI(JMBG,ULOGA) VALUES(?,?)";

        try {
             Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Početak transakcije

    // Dodavanje predstave
     PreparedStatement psPredstava = connection.prepareStatement(upitPredstava, Statement.RETURN_GENERATED_KEYS);
    psPredstava.setString(1, naziv);
    psPredstava.setLong(2, reziser.getId());
    psPredstava.setInt(3, trajanje);
    psPredstava.setString(4, zanr + "");
    psPredstava.setLong(5, korisnik.getId());
    psPredstava.setLong(6, scenograf1.getJmbg());
    psPredstava.setLong(7, kostimograf1.getJmbg());
    psPredstava.setLong(8, koreograf1.getJmbg());
    psPredstava.executeUpdate();

    ResultSet generatedKeysPredstava = psPredstava.getGeneratedKeys();
    if (!generatedKeysPredstava.next()) {
        throw new SQLException("Dodavanje predstave nije generisalo ID.");
    }
    long idPredstava = generatedKeysPredstava.getLong(1);

    // Dodavanje uloga i povezivanje sa glumcima
    PreparedStatement psUloga = connection.prepareStatement(upitUloga, Statement.RETURN_GENERATED_KEYS);
    PreparedStatement psGlumi = connection.prepareStatement(upitGlumi);

    for (int i = 0; i < uloge.size(); i++) {
        // Dodavanje uloge
        psUloga.setString(1, uloge.get(i));
        psUloga.setLong(2, idPredstava);
        psUloga.executeUpdate();

        ResultSet generatedKeysUloga = psUloga.getGeneratedKeys();
        if (!generatedKeysUloga.next()) {
            throw new SQLException("Dodavanje uloge nije generisalo ID.");
        }
        long idUloga = generatedKeysUloga.getLong(1);

        // Dodavanje glumca za odgovarajuću ulogu
        Glumac glumac = glumci.get(i); // Glumac odgovara ulogama po indeksu
        psGlumi.setLong(1, glumac.getJmbg());
        psGlumi.setLong(2, idUloga);
        psGlumi.executeUpdate();
    }

            connection.commit(); // Završi transakciju
            System.out.println("Podaci uspešno sačuvani.");
        } catch (SQLException ex) {
            try {
                DBConnection.getInstance().getConnection().rollback(); // Vraćanje transakcije
                System.err.println("Greška u bazi, transakcija vraćena: " + ex.getMessage());
            } catch (SQLException rollbackEx) {
                System.err.println("Greška pri vraćanju transakcije: " + rollbackEx.getMessage());
            }
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true); // Vraćanje u automatski mod
            } catch (SQLException ex) {
                System.err.println("Greška pri vraćanju automatskog commit-a: " + ex.getMessage());
            }
        }
    }



//        String upit="INSERT INTO PREDSTAVA(NAZIV,REZISER, TRAJANJE,ZANR, IDKORISNIKA,SCENOGRAF, KOSTIMOGRAF, KOREOGRAF) VALUES (?,?,?,?,?,?,?,?)";
//        try {
//            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, naziv);
//            ps.setLong(2, reziser.getId());
//            ps.setInt(3, trajanje);
//            
//            ps.setString(4, zanr+"");
//         
//            ps.setLong(5,korisnik.getId());
//            ps.setLong(6, scenograf1.getJmbg());
//            ps.setLong(7, kostimograf1.getJmbg());
//            ps.setLong(8, koreograf1.getJmbg());
//            ps.executeUpdate();
//            ResultSet generatedKeys=ps.getGeneratedKeys();
//            if(generatedKeys.next()){
//                long idPredstava=generatedKeys.getLong(1);
//                System.out.println(idPredstava);
//                for (String string : uloge) {
//                    String upit3="INSERT INTO ULOGA(NAZIV,IDPREDSTAVE) VALUES(?,?)";
//                    PreparedStatement ps3=DBConnection.getInstance().getConnection().prepareStatement(upit3,Statement.RETURN_GENERATED_KEYS);
//                    ps3.setString(1, string);
//                    ps3.setLong(2, idPredstava);
//                    ps3.executeUpdate();
//                    ResultSet generatedKeys3=ps3.getGeneratedKeys(); 
//                    while(generatedKeys3.next()){
//                            long idUloge=generatedKeys3.getLong(1);
//                            System.out.println(idUloge);
//                            for (Glumac glumac : glumci) {
//                                String upit2="INSERT INTO GLUMI(JMBG,ULOGA) VALUES(?,?)";
//                                PreparedStatement ps2=DBConnection.getInstance().getConnection().prepareStatement(upit2);
//                                ps2.setLong(1, glumac.getJmbg());
//                                ps2.setLong(2, idUloge);
//                                ps2.executeUpdate();
//                        }
//                            generatedKeys3.next();
//                    }
//                    }
//                }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
