/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfile2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class DBConnection {
  static Connection con = null;
    public Connection connect() {
        //Class.forName("com.mysql.jdbc.Driver");
       String ret = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/entries", "root", "titi");
             System.out.print("Connected");
        } catch (Exception e) {
            ret = e.getMessage();
           e.printStackTrace();
        }
        
        return con;
        
    }

    public static void main(String[] args) {
        Connection c = new DBConnection().connect();
    }
    
}

