/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.ClientRequest;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Glumac;
import model.Glumi;
import model.Koreograf;
import model.Korisnik;
import model.Kostimograf;
import model.Predstava;
import model.Repertoar;
import model.Reziser;
import model.Scenograf;
import operations.Operations;

/**
 *
 * @author Ana
 */
public class Controller {
    
    private static Controller instance;

    public static Controller getInstance(){
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    public Controller() {

    }
    public List<Korisnik> vratiListuKorisnika() {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuKorisnika, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Korisnik>) Communication.getInstance().getResponse1().getResponse();
    }

    public int ubaciKorisnika(Korisnik k) {
        ClientRequest clientRequest = new ClientRequest(Operations.ubaciKorisnika, k);
        Communication.getInstance().sendRequest(clientRequest);
        return (int) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Predstava> vratiListuPredstava() {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuPredstava, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Predstava>) Communication.getInstance().getResponse1().getResponse();
    }

    public boolean obrisiPredstavu(Predstava p) {
        ClientRequest clientRequest = new ClientRequest(Operations.obrisiPredstavu, p);
        Communication.getInstance().sendRequest(clientRequest);
        return (boolean) Communication.getInstance().getResponse1().getResponse();
    }

    
//    public void azurirajPredstavu(long id, String naziv, int trajanje, String datumStr, Zanr zanr, Date datum, Korisnik korisnik) {
//        dbb.azurirajPredstavu(id,naziv,trajanje,  datumStr,  zanr, datum, korisnik);
//    }

    public List<Reziser> vratiListuRezisera() {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuRezisera, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Reziser>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Glumac> vratiListuGlumaca() {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuGlumaca, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Glumac>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Repertoar> vratiListuRepertoara() {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuRepertoara, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Repertoar>) Communication.getInstance().getResponse1().getResponse();
    }

    public boolean obrisiPredstavuURepertoaru(Repertoar r) {
        ClientRequest clientRequest = new ClientRequest(Operations.obrisiPredstavuURepertoaru, r);
        Communication.getInstance().sendRequest(clientRequest);
        return (boolean) Communication.getInstance().getResponse1().getResponse();
    }

    public long ubaciRepertoar(Repertoar p) {
        ClientRequest clientRequest = new ClientRequest(Operations.ubaciRepertoar, p);
        Communication.getInstance().sendRequest(clientRequest);
        return (long) Communication.getInstance().getResponse1().getResponse();
    }

    public boolean azurirajRepertoar(HashMap<String, List<Object>> repertoar) {
        ClientRequest clientRequest = new ClientRequest(Operations.azurirajRepertoar, repertoar);
        Communication.getInstance().sendRequest(clientRequest);
        return (boolean) Communication.getInstance().getResponse1().getResponse();
    }
    

    public List<Repertoar> vratiListuRepertoaraOdgovarajucePredstave(Predstava p) {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuRepertoaraOdgovarajucePredstave, p);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Repertoar>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Repertoar> vratiListuRepertoaraDatum(String datum) throws ParseException {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuRepertoaraDatum, datum);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Repertoar>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Repertoar> vratiListuRepertoaraOba(HashMap<String, List<Object>> objekti) {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuRepertoaraOba, objekti);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Repertoar>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Glumac> vratiListuGlumacaUPredstavi(Predstava predstava) {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuGlumacaUPredstavi, predstava);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Glumac>) Communication.getInstance().getResponse1().getResponse();
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
        ClientRequest clientRequest = new ClientRequest(Operations.vratiEmailove, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<String>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Glumi> vratiListuGlumi(Predstava p) {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuGlumi, p);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Glumi>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Scenograf> vratiListuScenografa() {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuScenografa, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Scenograf>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Kostimograf> vratiListuKostimografa() {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuKostimografa, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Kostimograf>) Communication.getInstance().getResponse1().getResponse();
    }

    public List<Koreograf> vratiListuKoreografa() {
        ClientRequest clientRequest = new ClientRequest(Operations.vratiListuKoreografa, null);
        Communication.getInstance().sendRequest(clientRequest);
        return (List<Koreograf>) Communication.getInstance().getResponse1().getResponse();
    }

    public boolean dodajPredstavu2(HashMap<String, List<Object>> naziv) {
        ClientRequest clientRequest = new ClientRequest(Operations.dodajPredstavu2, naziv);
        Communication.getInstance().sendRequest(clientRequest);
        return (boolean) Communication.getInstance().getResponse1().getResponse();
    }

    public int dodajGlumca(Glumac g) {
        ClientRequest clientRequest = new ClientRequest(Operations.dodajGlumca, g);
        Communication.getInstance().sendRequest(clientRequest);
        return (int) Communication.getInstance().getResponse1().getResponse();
    }

    public boolean azurirajGlumca(HashMap<String, List<Object>> objekti) {
        ClientRequest clientRequest = new ClientRequest(Operations.azurirajGlumca, objekti);
        Communication.getInstance().sendRequest(clientRequest);
        return (boolean) Communication.getInstance().getResponse1().getResponse();
    }

    public void prijaviKorisnikaNaServer(Korisnik korisnik) {
         ClientRequest clientRequest = new ClientRequest(Operations.ulogujKorisnika, korisnik);
         Communication.getInstance().sendRequest(clientRequest);
         Communication.getInstance().getResponse1();
    }

  

   
}
