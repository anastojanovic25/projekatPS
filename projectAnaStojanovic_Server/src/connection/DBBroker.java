/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AbstractDomainObject;
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
    private static DBBroker instance;
    private Connection connection;
    
    //citanje iz fajla podatke o bazi
    private DBBroker() throws Exception{
        Properties properties=new Properties();
        properties.load(new FileInputStream("dbconfig.properties"));
        String url=properties.getProperty("url");
        String user=properties.getProperty("username");
        String pass=properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() throws Exception {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }
   
    public ArrayList<AbstractDomainObject> select(AbstractDomainObject odo, Object o) throws SQLException {
        String upit;
        if(o==null){
           upit = "SELECT * FROM " + odo.tableName() + " " + odo.alijas()
                + " " + odo.requirementForSelect(o); 
        }else{
            upit = "SELECT * FROM " + odo.tableName() + " " + odo.alijas()
                + " " + odo.join() + " " + odo.requirementForSelect(o);
        }
        
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return odo.getList(rs, o);
    }
    public ArrayList<AbstractDomainObject> select2(AbstractDomainObject odo, Object o) throws SQLException {
        String upit;
       upit = "SELECT "+odo.atributPretrazivanja() +" FROM " + odo.tableName() + " " + odo.alijas()
                + " " + odo.requirementForSelect(o); 
       
        
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return odo.getList(rs, o);
    }

    public int insert(AbstractDomainObject odo) throws SQLException {
        String upit = "INSERT INTO " + odo.tableName() + " "
                + odo.columnsForInsert() + " VALUES (" + odo.valuesForInsert() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            return -1;
        }
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            int newId = generatedKeys.getInt(1);
            return newId;
        }
        return -1;
    }

    public boolean update(AbstractDomainObject odo,Object o) throws SQLException {
        String upit = "UPDATE " + odo.tableName() + " SET "
                + odo.valuesForUpdate(o) + " WHERE " + odo.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        int result = s.executeUpdate(upit);
        return result == 1;
    }

    public boolean delete(AbstractDomainObject odo) throws SQLException {
        String upit = "DELETE FROM " + odo.tableName() + " WHERE " + odo.uslov();
        System.out.println(upit);
        Statement s = connection.createStatement();
        int result = s.executeUpdate(upit);
        return result == 1;
    }
    
//    public List<Korisnik> vratiListuKorisnika() {
//         List<Korisnik> lista=new ArrayList<>();
//        try {
//           
//            String upit="SELECT * FROM KORISNICI";
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long id=rs.getLong("id");
//                String email=rs.getString("email");
//                String pass=rs.getString("password");
//                String ime=rs.getString("ime");
//                String prezime=rs.getString("prezime");
//                String num=rs.getString("number");
//                String gender=rs.getString("gender");
//                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
//                lista.add(k);
//                
//            }
//            
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         return lista;
//    }

//    public boolean ubaciKorisnika(Korisnik k) {
//        String upit="INSERT INTO KORISNICI(EMAIL,PASSWORD, IME,PREZIME,NUMBER,GENDER) VALUES(?,?,?,?,?,?)";
//        try {
//            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
//            ps.setString(1, k.getEmail());
//            ps.setString(2, k.getPassword());
//            ps.setString(3, k.getIme());
//            ps.setString(4, k.getPrezime());
//            ps.setString(5,k.getBroj());
//            ps.setString(6,k.getPol());
//            
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return true;
//    }
//
//    public List<Predstava> vratiListuPredstava() {
//        List<Predstava> lista=new ArrayList<>();
//          try {
//           
//            String upit="SELECT P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER,R.IME AS RI,R.PREZIME AS RPR,S.IME AS SI, S.PREZIME AS SP, KR.IME AS KRI, KR.PREZIME AS KRP, KO.IME AS KOI, KO.PREZIME AS KOP FROM Predstava p JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER R ON P.REZISER=R.ID JOIN SCENOGRAF S ON P.SCENOGRAF=S.JMBG JOIN KOREOGRAF KR ON P.KOREOGRAF=KR.JMBG JOIN KOSTIMOGRAF KO ON P.KOSTIMOGRAF=KO.JMBG";
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long idPr=rs.getLong("id");
//                long idR=rs.getLong("reziser");
//                long jmbgS=rs.getLong("scenograf");
//                long jmbgKor=rs.getLong("koreograf");
//                long jmbgKos=rs.getLong("kostimograf");
//                String naziv=rs.getString("naziv");
//                int tr=rs.getInt("trajanje");
//                long id=rs.getLong("idKorisnika");
//                String email=rs.getString("EMAIL");
//                String pass=rs.getString("PASSWORD");
//                String ime=rs.getString("IME");
//                String prezime=rs.getString("PREZIME");
//                String num=rs.getString("NUMBER");
//                String gender=rs.getString("GENDER");
//                String ri=rs.getString("RI");
//                String rpr=rs.getString("RPR");
//                String si=rs.getString("SI");
//                String sp=rs.getString("SP");
//                String koi=rs.getString("KOI");
//                String kop=rs.getString("KOP");
//                String koreografime=rs.getString("KRI");
//                String krp=rs.getString("KRP");
//                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
//                
//                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
//                
//               Reziser reziser=new Reziser(idR, ri, rpr);
//               Scenograf scenograf=new Scenograf(jmbgS, si, sp);
//               Koreograf koreograf=new Koreograf(jmbgKor, koreografime, krp);
//               Kostimograf kosimograf=new Kostimograf(jmbgKos, koi, kop);
//               Predstava pr=new Predstava(idPr, naziv, reziser, scenograf, koreograf, kosimograf, tr, zanr, k);
//               lista.add(pr);
//            }
//            
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        return lista;
//    }
//
//    public boolean obrisiPredstavu(Predstava p) {
//        String upit="DELETE FROM PREDSTAVA WHERE ID="+p.getId();
//        try {
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            //PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
//            //ps.setLong(1, p.getId());
//           // ps.executeUpdate();
//            
//            s.executeUpdate(upit);
//            System.out.println(upit);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return true;
//    }

    
//    public void azurirajPredstavu(long id, String naziv, int trajanje, String datumStr, Zanr zanr, Date datum, Korisnik korisnik) {
//        String upit="UPDATE PREDSTAVE SET naziv=?,korisnik=?,trajanje=?,datum=?,zanr=? WHERE ID=?;";
//        PreparedStatement ps;
//        try {
//            ps = DBConnection.getInstance().getConnection().prepareStatement(upit);
//            ps.setString(1, naziv);
//            ps.setInt(3, trajanje);
//            ps.setLong(2, korisnik.getId());
//            ps.setLong(6, id);
//            java.sql.Date datumSql=new java.sql.Date(datum.getTime());
//            ps.setDate(4, datumSql);
//            ps.setString(5, zanr+"");
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }

//    public List<Reziser> vratiListuRezisera() {
//        List<Reziser> lista=new ArrayList();
//          try {
//           
//            String upit="SELECT * FROM Reziser";
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long id=rs.getLong("id");
//                String ime=rs.getString("ime");
//                String prezime=rs.getString("prezime");
//               Reziser r=new Reziser(id, ime, prezime);
//               lista.add(r);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return lista;
//    }

//    public List<Glumac> vratiListuGlumaca() {
//        List<Glumac> lista=new ArrayList<>();
//         try {
//           
//            String upit="SELECT * FROM Glumci";
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long jmbg=rs.getLong("jmbg");
//                String ime=rs.getString("ime");
//                String prezime=rs.getString("prezime");
//               Glumac g=new Glumac(jmbg, ime, prezime);
//               lista.add(g);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        return lista;
//    }

//    public List<Repertoar> vratiListuRepertoara() {
//        List<Repertoar> lista=new ArrayList<>();
//         try {
//          String upit="SELECT * FROM REPERTOAR R JOIN PREDSTAVA P ON R.IDPREDSTAVE=P.ID JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER RE ON P.REZISER=RE.ID JOIN SCENOGRAF S ON P.SCENOGRAF=S.JMBG JOIN KOREOGRAF KR ON P.KOREOGRAF=KR.JMBG JOIN KOSTIMOGRAF KO ON P.KOSTIMOGRAF=KO.JMBG ORDER BY DATUM ASC,VREME ASC";
//          Statement s=DBConnection.getInstance().getConnection().createStatement();
//          ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long idPr=rs.getLong("id");
//                long idR=rs.getLong("reziser");
//                String naziv=rs.getString("naziv");
//                int tr=rs.getInt("trajanje");
//                long id=rs.getLong("idKorisnika");
//                String email=rs.getString("EMAIL");
//                String pass=rs.getString("PASSWORD");
//                String ime=rs.getString("IME");
//                String prezime=rs.getString("PREZIME");
//                String num=rs.getString("NUMBER");
//                String gender=rs.getString("GENDER");
//                String ri=rs.getString("imeR");
//                String rpr=rs.getString("prezimeR");
//                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
//               
//                String si=rs.getString("imeS");
//                String sp=rs.getString("prezimeS");
//                String koi=rs.getString("imeKos");
//                String kop=rs.getString("prezimeKos");
//                String koreografime=rs.getString("imeKor");
//                String krp=rs.getString("prezimeKor");
//                long jmbgS=rs.getLong("scenograf");
//                long jmbgKor=rs.getLong("koreograf");
//                long jmbgKos=rs.getLong("kostimograf");
//                
//                
//                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
//                
//               Reziser reziser=new Reziser(idR, ri, rpr);
//               Scenograf scenograf=new Scenograf(jmbgS, si, sp);
//               Koreograf koreograf=new Koreograf(jmbgKor, koreografime, krp);
//               Kostimograf kosimograf=new Kostimograf(jmbgKos, koi, kop);
//               Predstava pr=new Predstava(idPr, naziv, reziser, scenograf, koreograf, kosimograf, tr, zanr, k);
//               
//              
//               String sala=rs.getString("sala");
//               java.sql.Date datumSql=rs.getDate("datum");
//               java.util.Date datum=new java.util.Date(datumSql.getTime());
//               String vreme = rs.getString("vreme");
//               
//               Repertoar r=new Repertoar(pr, datum, vreme, sala);
//               lista.add(r);
//            }
//            
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public boolean obrisiPredstavuURepertoaru(Repertoar r) {
//        String upit="DELETE FROM REPERTOAR WHERE IDPREDSTAVE=? AND DATUM=? AND SALA=? AND VREME=?";
//        try {
//            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
//            
//            ps.setLong(1, r.getPredstava().getId());
//            java.sql.Date datumSQl= new java.sql.Date(r.getDatum().getTime());
//            ps.setDate(2, datumSQl);
//            ps.setString(3, r.getSala());
//            ps.setString(4, r.getVreme());
//            ps.executeUpdate();
//         
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return true;
//    }
//
//    public boolean ubaciRepertoar(Repertoar r) {
//        try {
//            String upit="INSERT INTO REPERTOAR VALUES(?,?,?,?)";
//            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
//            ps.setLong(1, r.getPredstava().getId());
//            java.sql.Date datumSql= new java.sql.Date(r.getDatum().getTime());
//            ps.setDate(2,datumSql);
//            ps.setString(3, r.getSala());
//            ps.setString(4,r.getVreme());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return true;
//    }

    public boolean azurirajRepertoar(Repertoar r) {
        try {
            String upit="UPDATE REPERTOAR SET SALA=?, VREME= ? WHERE IDPREDSTAVE=? AND DATUM=? AND VREME=?";
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(upit);
            ps.setLong(1, r.getPredstava().getId());
            java.sql.Date datumSql= new java.sql.Date(r.getDatum().getTime());
            ps.setDate(2,datumSql);
            ps.setString(3, r.getSala());
            ps.setString(4,r.getVreme());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

//    public List<Repertoar> vratiListuRepertoaraOdgovarajucePredstave(Predstava p) {
//        List<Repertoar> lista=new ArrayList<>();
//        String upit="SELECT * FROM REPERTOAR WHERE IDPREDSTAVE="+p.getId();//order by
//        try {
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                String sala=rs.getString("sala");
//                String vreme=rs.getString("vreme");
//                java.sql.Date datumSql=rs.getDate("datum");
//                java.util.Date datum=new java.util.Date(datumSql.getTime());
//                Repertoar r=new Repertoar(p, datum, vreme, sala);
//                lista.add(r);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        return lista;
//    }

//    public List<Repertoar> vratiListuRepertoaraDatum(String datum) throws ParseException {
//       List<Repertoar> lista=new ArrayList<>();
//         SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
//        try {
//              java.util.Date utilDate = formatter.parse(datum);
//              java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//         String upit="SELECT RP.SALA, RP.VREME, P.*,K.EMAIL, K.PASSWORD, K.IME,K.PREZIME,K.NUMBER, K.GENDER,R.IME AS RI,R.PREZIME AS RPR,S.IME AS SI, S.PREZIME AS SP, KR.IME AS KRI, KR.PREZIME AS KRP, KO.IME AS KOI, KO.PREZIME AS KOP FROM REPERTOAR RP JOIN Predstava p ON RP.IDPREDSTAVE=P.ID JOIN korisniCI k ON P.IDKORISNIKA=K.ID JOIN REZISER R ON P.REZISER=R.ID JOIN SCENOGRAF S ON P.SCENOGRAF=S.JMBG JOIN KOREOGRAF KR ON P.KOREOGRAF=KR.JMBG JOIN KOSTIMOGRAF KO ON P.KOSTIMOGRAF=KO.JMBG WHERE RP.DATUM=?";
//       
//            PreparedStatement s=DBConnection.getInstance().getConnection().prepareStatement(upit);
//            s.setDate(1, sqlDate);
//            ResultSet rs=s.executeQuery();
//            while(rs.next()){
//                String sala=rs.getString("SALA");
//                String vreme=rs.getString("VREME");
//                
//                long idPr=rs.getLong("id");
//                long idR=rs.getLong("reziser");
//                String naziv=rs.getString("naziv");
//                int tr=rs.getInt("trajanje");
//                long id=rs.getLong("idKorisnika");
//                String email=rs.getString("EMAIL");
//                String pass=rs.getString("PASSWORD");
//                String ime=rs.getString("IME");
//                String prezime=rs.getString("PREZIME");
//                String num=rs.getString("NUMBER");
//                String gender=rs.getString("GENDER");
//                String ri=rs.getString("RI");
//                String rpr=rs.getString("RPR");
//                Zanr zanr=Zanr.valueOf(rs.getString("zanr"));
//                
//                String si=rs.getString("SI");
//                String sp=rs.getString("SP");
//                String koi=rs.getString("KOI");
//                String kop=rs.getString("KOP");
//                String koreografime=rs.getString("KRI");
//                String krp=rs.getString("KRP");
//                long jmbgS=rs.getLong("scenograf");
//                long jmbgKor=rs.getLong("koreograf");
//                long jmbgKos=rs.getLong("kostimograf");
//                
//                Korisnik k=new Korisnik(id, email, pass, ime, prezime, num, gender);
//                
//               Reziser reziser=new Reziser(idR, ri, rpr);
//               Scenograf scenograf=new Scenograf(jmbgS, si, sp);
//               Koreograf koreograf=new Koreograf(jmbgKor, koreografime, krp);
//               Kostimograf kosimograf=new Kostimograf(jmbgKos, koi, kop);
//               Predstava pr=new Predstava(idPr, naziv, reziser, scenograf, koreograf, kosimograf, tr, zanr, k);
//               
//                Repertoar rep=new Repertoar(pr, utilDate, vreme, sala);
//                lista.add(rep);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//        return lista;
//    }

    public List<Repertoar> vratiListuRepertoaraOba(HashMap<String, List<Object>> objekti) {
        if (objekti == null || objekti.isEmpty()) {
        throw new IllegalArgumentException("Prosleđena mapa objekata je prazna ili null.");
        }
        String key=objekti.keySet().iterator().next();
        List<Object> listaObjekata=objekti.get(key);
        Predstava p=(Predstava) listaObjekata.get(0);
        String datum=(String) listaObjekata.get(1);
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

//    public List<Glumac> vratiListuGlumacaUPredstavi(Predstava predstava) {
//        List<Glumac> lista=new ArrayList<>();
//         try {
//           
//            String upit="SELECT G.* FROM GLUMI GL JOIN GLUMCI G ON GL.JMBG=G.JMBG WHERE GL.IDPREDSTAVE="+predstava.getId();
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long jmbg=rs.getLong("jmbg");
//                String ime=rs.getString("ime");
//                String prezime=rs.getString("prezime");
//               Glumac g=new Glumac(jmbg, ime, prezime);
//               lista.add(g); 
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        return lista;
//    }

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

//    public List<Glumi> vratiListuGlumi(Predstava predstava) {
//          List<Glumi> lista=new ArrayList<>();
//         try {
//            String upit="SELECT * FROM ULOGA U JOIN GLUMI G ON G.ULOGA=U.ID JOIN GLUMCI GL ON GL.JMBG=G.JMBG WHERE U.IDPREDSTAVE="+predstava.getId();
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long jmbg=rs.getLong("jmbg");
//                String ime=rs.getString("ime");
//                String prezime=rs.getString("prezime");
//                long idUloge=rs.getLong("id");
//                String nazivUloge=rs.getString("naziv");
//                Uloga u=new Uloga(idUloge, nazivUloge, predstava);
//               Glumac g=new Glumac(jmbg, ime, prezime);
//               Long id=rs.getLong("idGlumi");
//               Glumi gl =new Glumi(g, u,id);
//               lista.add(gl);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//        return lista;
//    }

//    public List<Scenograf> vratiListuScenografa() {
//         List<Scenograf> lista=new ArrayList();
//          try {
//            String upit="SELECT * FROM Scenograf";
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long jmbg=rs.getLong("jmbg");
//                String ime=rs.getString("imeS");
//                String prezime=rs.getString("prezimeS");
//               Scenograf r=new Scenograf(jmbg, ime, prezime);
//               lista.add(r); 
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }

//    public List<Kostimograf> vratiListuKostimografa() {
//         List<Kostimograf> lista=new ArrayList();
//          try {
//            String upit="SELECT * FROM Kostimograf";
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long jmbg=rs.getLong("jmbg");
//                String ime=rs.getString("ime");
//                String prezime=rs.getString("prezime");
//               Kostimograf r=new Kostimograf(jmbg, ime, prezime);
//               lista.add(r);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public List<Koreograf> vratiListuKoreografa() {
//        List<Koreograf> lista=new ArrayList();
//          try {
//           
//            String upit="SELECT * FROM Koreograf";
//            Statement s=DBConnection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(upit);
//            while(rs.next()){
//                long jmbg=rs.getLong("jmbg");
//                String ime=rs.getString("ime");
//                String prezime=rs.getString("prezime");
//               Koreograf k=new Koreograf(jmbg, ime, prezime);
//               lista.add(k);  
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public boolean dodajPredstavu2(HashMap<String, List<Object>> naziv) {
//        
//        String upitPredstava = "INSERT INTO PREDSTAVA(NAZIV,REZISER,TRAJANJE,ZANR,IDKORISNIKA,SCENOGRAF,KOSTIMOGRAF,KOREOGRAF) VALUES (?,?,?,?,?,?,?,?)";
//        String upitUloga = "INSERT INTO ULOGA(NAZIV,IDPREDSTAVE) VALUES(?,?)";
//        String upitGlumi = "INSERT INTO GLUMI(JMBG,ULOGA) VALUES(?,?)";
//        String key=naziv.keySet().iterator().next();
//        List<Object> listaObjekata=naziv.get(key);
//        Predstava p=(Predstava) listaObjekata.get(0);
//        List<Glumac> glumci=(List<Glumac>) listaObjekata.get(1);
//        List<String> uloge=(List<String>) listaObjekata.get(2);
//        try {
//             Connection connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false); // Početak transakcije
//
//            // Dodavanje predstave
//             PreparedStatement psPredstava = connection.prepareStatement(upitPredstava, Statement.RETURN_GENERATED_KEYS);
//            psPredstava.setString(1, p.getNaziv());
//            psPredstava.setLong(2, p.getReziser().getId());
//            psPredstava.setInt(3, p.getTrajanje());
//            psPredstava.setString(4, p.getZanr() + "");
//            psPredstava.setLong(5, p.getKorisnikUnos().getId());
//            psPredstava.setLong(6, p.getScenograf().getJmbg());
//            psPredstava.setLong(7, p.getKostimograf().getJmbg());
//            psPredstava.setLong(8, p.getKoreograf().getJmbg());
//            psPredstava.executeUpdate();
//
//            ResultSet generatedKeysPredstava = psPredstava.getGeneratedKeys();
//            if (!generatedKeysPredstava.next()) {
//                throw new SQLException("Dodavanje predstave nije generisalo ID.");
//            }
//            long idPredstava = generatedKeysPredstava.getLong(1);
//
//            // Dodavanje uloga i povezivanje sa glumcima
//            PreparedStatement psUloga = connection.prepareStatement(upitUloga, Statement.RETURN_GENERATED_KEYS);
//            PreparedStatement psGlumi = connection.prepareStatement(upitGlumi);
//
//            for (int i = 0; i < uloge.size(); i++) {
//                // Dodavanje uloge
//                psUloga.setString(1, uloge.get(i));
//                psUloga.setLong(2, idPredstava);
//                psUloga.executeUpdate();
//
//                ResultSet generatedKeysUloga = psUloga.getGeneratedKeys();
//                if (!generatedKeysUloga.next()) {
//                    throw new SQLException("Dodavanje uloge nije generisalo ID.");
//                }
//                long idUloga = generatedKeysUloga.getLong(1);
//
//                // Dodavanje glumca za odgovarajuću ulogu
//                Glumac glumac = glumci.get(i); // Glumac odgovara ulogama po indeksu
//                psGlumi.setLong(1, glumac.getJmbg());
//                psGlumi.setLong(2, idUloga);
//                psGlumi.executeUpdate();
//            }
//
//                    connection.commit(); // Završi transakciju
//                    System.out.println("Podaci uspešno sačuvani.");
//                } catch (SQLException ex) {
//                    try {
//                        DBConnection.getInstance().getConnection().rollback(); // Vraćanje transakcije
//                        System.err.println("Greška u bazi, transakcija vraćena: " + ex.getMessage());
//                    } catch (SQLException rollbackEx) {
//                        System.err.println("Greška pri vraćanju transakcije: " + rollbackEx.getMessage());
//                    }
//                    Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//                } finally {
//                    try {
//                        DBConnection.getInstance().getConnection().setAutoCommit(true); // Vraćanje u automatski mod
//                    } catch (SQLException ex) {
//                        System.err.println("Greška pri vraćanju automatskog commit-a: " + ex.getMessage());
//                    }
//                }
//        return true;
//    }
//
//}
