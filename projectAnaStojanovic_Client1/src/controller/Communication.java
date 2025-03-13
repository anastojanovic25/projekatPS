/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.ClientRequest;
import communication.ServerResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class Communication {
    private static Communication instance;
    private Socket s;
    
    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    private Communication() {
       try {
           s = new Socket("localhost",9000);
       } catch (IOException ex) {
           Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public ServerResponse getResponse1(){
       try {
           ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
           return (ServerResponse) inputStream.readObject();
       } catch (IOException | ClassNotFoundException ex) {
           Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
       }
        return null;
    }
   
    public void sendRequest(ClientRequest clientRequest){
       try {
           ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
           outputStream.writeObject(clientRequest);
       } catch (IOException ex) {
           Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    
}
