/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
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
        Properties properties = new Properties();
        properties.load(new FileInputStream("dbconfig.properties"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(true); // defaultno

    } catch (Exception e) {
        e.printStackTrace();
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
