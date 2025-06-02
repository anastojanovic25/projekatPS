/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import communication.ClientRequest;
import communication.ServerResponse;
import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Glumac;
import model.Glumi;
import model.Koreograf;
import model.Korisnik;
import model.Kostimograf;
import model.Predstava;
import model.Repertoar;
import model.Reziser;
import model.Scenograf;
import static operations.Operations.azurirajRepertoar;

/**
 *
 * @author Ana
 */
public class ProcessingClientRequest extends Thread{
     private Korisnik ulogovaniKorisnik;
   
    private Socket s;
    private StartServer server;

    public ProcessingClientRequest(Socket s, StartServer server) {
        this.s = s;
        this.server = server;
    }

    @Override
    public void run() {
         while (true) {
             
             try {
                 ClientRequest clientRequest = getRequest();
                 ServerResponse serverResponse = new ServerResponse();
                 switch (clientRequest.getOperation()) {
                     case ulogujKorisnika:
                         Korisnik aktivniKorisnik = (Korisnik) clientRequest.getParam();
                         Controller.getInstance().dodajAktivnogKorisnika(aktivniKorisnik);
                         ulogovaniKorisnik = aktivniKorisnik; 
                         break;
                     case vratiListuKorisnika:
                         List<Korisnik> lista=Controller.getInstance().vratiListuKorisnika();
                         serverResponse.setResponse(lista);
                         break;
                     case ubaciKorisnika:
                         Korisnik k = (Korisnik) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().ubaciKorisnika(k));
                         break;
                     case vratiListuPredstava:
                         List<Predstava> listaPr=Controller.getInstance().vratiListuPredstava();
                         serverResponse.setResponse(listaPr);
                         break;
                     case obrisiPredstavu:
                         Predstava predstava=(Predstava) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().obrisiPredstavu(predstava));
                         break;
                     case vratiListuRezisera:
                         List<Reziser> listaRez=Controller.getInstance().vratiListuRezisera();
                         serverResponse.setResponse(listaRez);
                         break;
                     case vratiListuGlumaca:
                         List<Glumac> lista1=Controller.getInstance().vratiListuGlumaca();
                         serverResponse.setResponse(lista1);
                         break;
                     case vratiListuRepertoara:
                         List<Repertoar> listaR=Controller.getInstance().vratiListuRepertoara();
                         serverResponse.setResponse(listaR);
                         break;
                     case obrisiPredstavuURepertoaru:
                         Repertoar r=(Repertoar) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().obrisiPredstavuURepertoaru(r));
                         break;
                     case ubaciRepertoar:
                           try {
                                Repertoar r1 = (Repertoar) clientRequest.getParam();
                                serverResponse.setResponse(Controller.getInstance().ubaciRepertoar(r1));
                            } catch (Exception e) {
                                serverResponse.setException(e);
                            }
                            break;
                     case azurirajRepertoar:
                         //Repertoar r2=(Repertoar) clientRequest.getParam();
                         HashMap<String, List<Object>> listaObjekti1=(HashMap<String, List<Object>>) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().azurirajRepertoar(listaObjekti1));
                         break;
                   
                     case vratiListuRepertoaraOdgovarajucePredstave:
                         Predstava predstava1=(Predstava) clientRequest.getParam();
                         List<Repertoar> listaRep1=Controller.getInstance().vratiListuRepertoaraOdgovarajucePredstave(predstava1);
                         serverResponse.setResponse(listaRep1);
                         break;
                     case vratiListuRepertoaraDatum:
                         String datum=(String) clientRequest.getParam();
                         List<Repertoar> listaRep2;
                         try {
                             listaRep2 = Controller.getInstance().vratiListuRepertoaraDatum(datum);
                             serverResponse.setResponse(listaRep2);
                         } catch (ParseException ex) {
                             Logger.getLogger(ProcessingClientRequest.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         break;
                     case vratiListuRepertoaraOba:
                         HashMap<String, List<Object>> listaObjekti=(HashMap<String, List<Object>>) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().vratiListuRepertoaraOba(listaObjekti));
                         break;
                     case vratiListuGlumacaUPredstavi:
                         Predstava predstava2=(Predstava) clientRequest.getParam();
                         List<Glumac> listaGlumaca=Controller.getInstance().vratiListuGlumacaUPredstavi(predstava2);
                         serverResponse.setResponse(listaGlumaca);
                         break;
                     case vratiListuGlumi:
                         Predstava predstava3=(Predstava) clientRequest.getParam();
                         List<Glumi> listaGlumi=Controller.getInstance().vratiListuGlumi(predstava3);
                         serverResponse.setResponse(listaGlumi);
                         break;
                     case vratiListuScenografa:
                         List<Scenograf> listaS=Controller.getInstance().vratiListuScenografa();
                         serverResponse.setResponse(listaS);
                         break;
                     case vratiListuKostimografa:
                         List<Kostimograf> listaKom=Controller.getInstance().vratiListuKostimografa();
                         serverResponse.setResponse(listaKom);
                         break;
                     case vratiListuKoreografa:
                         List<Koreograf> listaKor=Controller.getInstance().vratiListuKoreografa();
                         serverResponse.setResponse(listaKor);
                         break;
                     case dodajPredstavu2:
                         HashMap<String, List<Object>> lista4=(HashMap<String, List<Object>>) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().dodajPredstavu2(lista4));
                         break;
                     case azurirajPredstavu:
                         HashMap<String, List<Object>> listaPred=(HashMap<String, List<Object>>) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().azurirajPredstavu(listaPred));
                         break;
                     case dodajGlumca:
                         Glumac g=(Glumac) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().dodajGlumca(g));
                         break;
                     case azurirajGlumca:
                         HashMap<String, List<Object>> lista5=(HashMap<String, List<Object>>) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().azurirajGlumca(lista5));
                         break;
                     case azuirajGlumi:
                         HashMap<String, List<Object>> listaGlumi2=(HashMap<String, List<Object>>) clientRequest.getParam();
                         serverResponse.setResponse(Controller.getInstance().azurirajGlumi(listaGlumi2));
                         break;
                 }
                 
                 sendResponse(serverResponse);
             
        } catch (Exception ex) {
                 if (ulogovaniKorisnik != null) {
                    Controller.getInstance().ukloniAktivnogKorisnika(ulogovaniKorisnik);
                 }
                 Logger.getLogger(ProcessingClientRequest.class.getName()).log(Level.SEVERE, null, ex);
             }
             
        }
         
    }
     private ClientRequest getRequest() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
            return (ClientRequest) inputStream.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProcessingClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void sendResponse(ServerResponse response) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
            outputStream.writeObject(response);
            outputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(ProcessingClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
