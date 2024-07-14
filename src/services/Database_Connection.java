/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Common;

/**
 *
 * @author hassaan.siddique
 */
public class Database_Connection {
    Connection con=null;
    String url=null;
    final String DB_PATH = "DB_PATH"; 
    public Database_Connection(String url)
    {
        this.url=url;
    }
    public Database_Connection()
    {
        this.con=null;
        this.url=Common.getEnvVariable("DB_PATH");
    }
    public Connection db_connction()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+url);
            System.out.println("Connection Success");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public Connection db_connction(String url)
    {
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+url);
            System.out.println("Connection Success");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    Connection get_connection(Connection connection, String url)
    {
        try {
            if(connection == null || connection.isClosed())
            {
                connection = db_connction(url);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
