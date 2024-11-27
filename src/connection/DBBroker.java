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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Predstava;
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
           
            String upit="SELECT P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER FROM Predstave p JOIN korisniCI k ON P.KORISNIK=K.ID";
            Statement s=DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(upit);
            while(rs.next()){
                long idPr=rs.getLong("id");
                long id=rs.getLong("korisnik");
                String naziv=rs.getString("naziv");
                int tr=rs.getInt("trajanje");
                java.sql.Date datumSql=rs.getDate("datum");
                String email=rs.getString("EMAIL");
                String pass=rs.getString("PASSWORD");
                String ime=rs.getString("IME");
                String prezime=rs.getString("PREZIME");
                String num=rs.getString("NUMBER");
                String gender=rs.getString("GENDER");
                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
                
                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
                
                java.util.Date datum=new java.util.Date(datumSql.getTime());
                Predstava pr=new Predstava(idPr, naziv, k, tr, datum,zanr);
                lista.add(pr);

                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }

    public void obrisiPredstavu(Predstava p) {
        String upit="DELETE FROM PREDSTAVE WHERE ID="+p.getId();
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

    public void dodajPredstavu(String naziv, int trajanje, Date datum, Zanr zanr, Korisnik korisnik) {
          String upit="INSERT INTO PREDSTAVE(NAZIV, KORISNIK,TRAJANJE,DATUM,ZANR) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, naziv);
            ps.setLong(2, korisnik.getId());
            ps.setInt(3, trajanje);
            java.sql.Date datumSql=new java.sql.Date(datum.getTime());
            
            ps.setDate(4, datumSql);
         
            ps.setString(5,zanr+"");
            
            ps.executeUpdate();
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
    
}
