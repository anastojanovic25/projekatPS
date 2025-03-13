/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.ClientRequest;
import connection.DBBroker;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import model.Glumac;
import model.Korisnik;
import model.Predstava;
import model.Repertoar;
import model.Reziser;
import model.Zanr;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Glumi;
import model.Koreograf;
import model.Kostimograf;
import model.Scenograf;
import model.Uloga;
import operations.Operations;
import soGlumac.AddGlumac;
import soGlumac.GetGlumac;
import soGlumac.UpdateGlumac;
import soGlumi.AddGlumi;
import soGlumi.GetGlumi;
import soKoreograf.GetKoreograf;
import soKorisnik.AddKorisnik;
import soKorisnik.GetKorisnik;
import soKostimograf.GetKostimograf;
import soPredstava.AddPredstava;
import soPredstava.DeletePredstava;
import soPredstava.GetPredstava;
import soRepertoar.AddRepertoar;
import soReziser.GetReziser;
import soScenograf.GetScenograf;
import soUloga.AddUloga;
import soRepertoar.DeleteRepertoar;
import soRepertoar.GetRepertoar;
import soRepertoar.UpdateRepertoar;
/**
 *
 * @author Ana
 */
public class Controller {
    private static Controller instance;
    private DBBroker dbb;
    public Controller(){
        try {
            dbb=DBBroker.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Controller getInstance(){
        if(instance==null)
            instance=new Controller();
        return instance;
    }
   public List<Glumac> vratiListuGlumaca() throws Exception {
        GetGlumac so=new GetGlumac();
        so.templateExecute(new Glumac(), null);
        return so.getList();
    }
    public List<Glumac> vratiListuGlumacaUPredstavi(Predstava predstava) throws Exception {
        GetGlumac so=new GetGlumac();
        so.templateExecute(new Glumac(), predstava);
        return so.getList();
     }
    public List<Korisnik> vratiListuKorisnika() throws Exception {
        GetKorisnik so=new GetKorisnik();
        so.templateExecute(new Korisnik(), null);
        return so.getList();
    }
    public int ubaciKorisnika(Korisnik k) throws Exception {
        AddKorisnik so=new AddKorisnik();
        so.templateExecute(k, null);
        return so.getId();
    }
      public int dodajGlumca(Glumac g) throws Exception {
          AddGlumac so=new AddGlumac();
          so.templateExecute(g, null);
          return so.getId();
    }
    
    public List<Predstava> vratiListuPredstava() throws Exception {
        GetPredstava so=new GetPredstava();
        so.templateExecute(new Predstava(), new Predstava());
        return so.getList();
    }

    public boolean obrisiPredstavu(Predstava p) throws Exception {
        DeletePredstava so = new DeletePredstava();
        so.templateExecute(p, null);
        return so.isDeleted();
    }
    
    
    public List<Reziser> vratiListuRezisera() throws Exception {
        GetReziser so=new GetReziser();
        so.templateExecute(new Reziser(),null);
        return so.getList();
    }
    public List<Scenograf> vratiListuScenografa() throws Exception {
        GetScenograf so=new GetScenograf();
        so.templateExecute(new Scenograf(), null);
        return so.getList();
    }

    public List<Kostimograf> vratiListuKostimografa() throws Exception {
        GetKostimograf so=new GetKostimograf();
        so.templateExecute(new Kostimograf(), null);
        return so.getList();
    }

    public List<Koreograf> vratiListuKoreografa() throws Exception {
        GetKoreograf so=new GetKoreograf();
        so.templateExecute(new Koreograf(), null);
        return so.getList();
    }
    
    public List<Glumi> vratiListuGlumi(Predstava p) throws Exception {
        GetGlumi so=new GetGlumi();
        so.templateExecute(new Glumi(), p);
        return so.getList();
    }
    public int dodajPredstavu(Predstava p) throws Exception{
         AddPredstava soPredstava = new AddPredstava();
        soPredstava.templateExecute(p, null);
        return soPredstava.getId();
    }
    public int dodajUlogu(String uloga, Predstava predstava) throws Exception{
        Uloga u=new Uloga(123, uloga, predstava);
        AddUloga so=new AddUloga();
        so.templateExecute(u, null);
        return so.getId();
    }
    public int dodajGlumi(Glumac glumac, Uloga uloga) throws Exception{
        Glumi g=new Glumi(glumac, uloga, 789);
        AddGlumi so=new AddGlumi();
        so.templateExecute(g, null);
        return so.getId();
    }
    public boolean dodajPredstavu2(HashMap<String, List<Object>> naziv) throws Exception {
        String key = naziv.keySet().iterator().next();
        List<Object> listaObjekata = naziv.get(key);
        Predstava p = (Predstava) listaObjekata.get(0);
        List<Glumac> glumci = (List<Glumac>) listaObjekata.get(1);
        List<String> uloge = (List<String>) listaObjekata.get(2);

        int idPredstava=dodajPredstavu(p);
        for (int i = 0; i < uloge.size(); i++) {
            p.setId(idPredstava);
            String uloga=uloge.get(i);

            int idUloga = dodajUlogu(uloga, p);
            Uloga u=new Uloga(idUloga, uloga, p);
          Glumac glumac = glumci.get(i);  
            dodajGlumi(glumac, u);
        }
        return true;
    }
    
    public long ubaciRepertoar(Repertoar p) throws Exception {
         AddRepertoar so = new AddRepertoar();
        so.templateExecute(p, null);
        return so.getId();
    }


    public List<Repertoar> vratiListuRepertoara() throws Exception {
        GetRepertoar so=new GetRepertoar();
        so.templateExecute(new Repertoar(), new Repertoar());
        return so.getList();
    }
    public List<Repertoar> vratiListuRepertoaraOdgovarajucePredstave(Predstava p) throws Exception {
        GetRepertoar so=new GetRepertoar();
        so.templateExecute(new Repertoar(), p);
        return so.getList();
    }

    public List<Repertoar> vratiListuRepertoaraDatum(String datum) throws Exception {
        GetRepertoar so=new GetRepertoar();
        so.templateExecute(new Repertoar(), datum);
        return so.getList();
    }

    public List<Repertoar> vratiListuRepertoaraOba(HashMap<String, List<Object>> objekti) throws Exception {
        GetRepertoar so=new GetRepertoar();
        so.templateExecute(new Repertoar(), objekti);
        return so.getList();
    }
    public boolean obrisiPredstavuURepertoaru(Repertoar r) throws Exception {
        DeleteRepertoar so=new DeleteRepertoar();
        so.templateExecute(r, null);
        return so.isDeleted();
    }
    public boolean azurirajRepertoar(HashMap<String, List<Object>> objekti) throws Exception {
         String key=objekti.keySet().iterator().next();
        List<Object> listaObjekata=objekti.get(key);
        Repertoar stari= (Repertoar) listaObjekata.get(0);
        Repertoar novi= (Repertoar) listaObjekata.get(1);
        
        UpdateRepertoar so=new UpdateRepertoar();
        so.templateExecute(stari, novi);
        return so.isUpdated();
    }
    public boolean azurirajGlumca(HashMap<String, List<Object>> objekti) throws Exception {
        String key=objekti.keySet().iterator().next();
        List<Object> listaObjekata=objekti.get(key);
        
        Glumac stari= (Glumac) listaObjekata.get(0);
        Glumac novi= (Glumac) listaObjekata.get(1);
        
        UpdateGlumac so=new UpdateGlumac();
        so.templateExecute(stari, novi);
        return so.isUpdated();
    }


//        public List<Reziser> vratiListuRezisera() {
//        return dbb.vratiListuRezisera();
//    }

//    public List<Glumac> vratiListuGlumaca() {
//        return dbb.vratiListuGlumaca();
//    }
 
//    public List<Repertoar> vratiListuRepertoara() {
//        return dbb.vratiListuRepertoara();
//    }
//
//    public boolean obrisiPredstavuURepertoaru(Repertoar r) {
//        return dbb.obrisiPredstavuURepertoaru(r);
//    }

//    public List<Korisnik> vratiListuKorisnika() {
//        return dbb.vratiListuKorisnika();
//    }

//    public boolean ubaciKorisnika(Korisnik k) {
//        return dbb.ubaciKorisnika(k);
//    }

//    public List<Predstava> vratiListuPredstava() {
//        return dbb.vratiListuPredstava();
//    }
//
//    public boolean obrisiPredstavu(Predstava p) {
//        return dbb.obrisiPredstavu(p);
//    }

    
//    public void azurirajPredstavu(long id, String naziv, int trajanje, String datumStr, Zanr zanr, Date datum, Korisnik korisnik) {
//        dbb.azurirajPredstavu(id,naziv,trajanje,  datumStr,  zanr, datum, korisnik);
//    }



//    public boolean ubaciRepertoar(Repertoar p) {
//        return dbb.ubaciRepertoar(p); 
//    }
//
//    public boolean azurirajRepertoar(Repertoar repertoar) {
//        return dbb.azurirajRepertoar(repertoar);
//    }
//
//    public List<Repertoar> vratiListuRepertoaraOdgovarajucePredstave(Predstava p) {
//        return dbb.vratiListuRepertoaraOdgovarajucePredstave( p);
//    }
//
//    public List<Repertoar> vratiListuRepertoaraDatum(String datum) throws ParseException {
//        return dbb.vratiListuRepertoaraDatum(datum);
//    }
//
//    public List<Repertoar> vratiListuRepertoaraOba(HashMap<String, List<Object>> objekti) {
//        return dbb.vratiListuRepertoaraOba(objekti);
//    }

//    public List<Glumac> vratiListuGlumacaUPredstavi(Predstava predstava) {
//        return dbb.vratiListuGlumacaUPredstavi(predstava);
//    }
     
   public void sendMail(String mail, String randomPass) {

        final String username = "pozoristeps@gmail.com"; 
        final String password = "jzva cuwt lfgp uznj";
        //

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

       
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }     
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Vaša privremena šifra");
            message.setText("Potrebno je da verifikujete Vašu email adresu unosenjem privremene sifre.\n"
                    + "\nPrivremena šifra: " + randomPass );

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public String generateRandomPassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";


        String allowedChars = upperCaseLetters + lowerCaseLetters + numbers;


        int passwordLength = 8;

 
        SecureRandom random = new SecureRandom();


        StringBuilder password = new StringBuilder();


        for (int i = 0; i < passwordLength; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }


//
//    public List<Glumi> vratiListuGlumi(Predstava p) {
//        return dbb.vratiListuGlumi(p);
//    }
//
//    public List<Scenograf> vratiListuScenografa() {
//        return dbb.vratiListuScenografa();
//    }
//
//    public List<Kostimograf> vratiListuKostimografa() {
//        return dbb.vratiListuKostimografa();
//    }
//
//    public List<Koreograf> vratiListuKoreografa() {
//        return dbb.vratiListuKoreografa();
//    }
//
//    public boolean dodajPredstavu2(HashMap<String, List<Object>> naziv) {
//        return dbb.dodajPredstavu2(naziv);
//    }

  


}
