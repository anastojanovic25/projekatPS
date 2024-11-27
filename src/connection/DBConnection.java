/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    
    private DBConnection(){
        try {
            String url="jdbc:mysql://localhost:3307/bazaprojekat";
            String user="root";
            String pass="";
            connection=DriverManager.getConnection(url,user,pass);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DBConnection getInstance(){
        if(instance==null){
            instance=new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
