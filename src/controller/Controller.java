/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connection.DBBroker;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import model.Glumac;
import model.Korisnik;
import model.Predstava;
import model.Repertoar;
import model.Reziser;
import model.Zanr;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ana
 */
public class Controller {
    private static Controller instance;
    private DBBroker dbb;
    private Controller(){
        dbb=new DBBroker();
    }
    public static Controller getInstance(){
        if(instance==null)
            instance=new Controller();
        return instance;
    }

    public List<Korisnik> vratiListuKorisnika() {
        return dbb.vratiListuKorisnika();
    }

    public void ubaciKorisnika(String ime, String prezime, String broj, String email, String password, String pol) {
        dbb.ubaciKorisnika(ime, prezime, broj, email, password, pol);
    }

    public List<Predstava> vratiListuPredstava() {
        return dbb.vratiListuPredstava();
    }

    public void obrisiPredstavu(Predstava p) {
        dbb.obrisiPredstavu(p);
    }

    public void dodajPredstavu(String naziv, int trajanje, Zanr zanr, Reziser reziser, List<Glumac> selektovaniGlumci, Korisnik korisnik) {
        dbb.dodajPredstavu(naziv, trajanje, zanr, reziser, selektovaniGlumci, korisnik);
    }

    public void azurirajPredstavu(long id, String naziv, int trajanje, String datumStr, Zanr zanr, Date datum, Korisnik korisnik) {
        dbb.azurirajPredstavu(id,naziv,trajanje,  datumStr,  zanr, datum, korisnik);
    }

    public List<Reziser> vratiListuRezisera() {
        return dbb.vratiListuRezisera();
    }

    public List<Glumac> vratiListuGlumaca() {
        return dbb.vratiListuGlumaca();
    }

    public List<Repertoar> vratiListuRepertoara() {
        return dbb.vratiListuRepertoara();
    }

    public void obrisiPredstavuURepertoaru(Repertoar r) {
        dbb.obrisiPredstavuURepertoaru(r);
    }

    public void ubaciRepertoar(Predstava p, String sala, Date datum, String vreme) {
        dbb.ubaciRepertoar(p, sala, datum, vreme); 
    }

    public void azurirajRepertoar(Predstava p, String sala, Date datum, String vreme) {
        dbb.azurirajRepertoar( p, sala, datum,  vreme);
    }

    public List<Repertoar> vratiListuRepertoaraOdgovarajucePredstave(Predstava p) {
        return dbb.vratiListuRepertoaraOdgovarajucePredstave( p);
    }

    public List<Repertoar> vratiListuRepertoaraDatum(String datum) throws ParseException {
        return dbb.vratiListuRepertoaraDatum(datum);
    }

    public List<Repertoar> vratiListuRepertoaraOba(String datum, Predstava p) {
        return dbb.vratiListuRepertoaraOba( datum,p);
    }

    public List<Glumac> vratiListuGlumacaUPredstavi(Predstava predstava) {
        return dbb.vratiListuGlumacaUPredstavi(predstava);
    }

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

    public List<String> vratiEmailove() {
        return dbb.vratiEmailove();
    }
}
