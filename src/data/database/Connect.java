/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Flo
 */
public class Connect {

    public static ResultSet bConnect(String mQuery) {
        ResultSet r = null ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://localhost/M2lJava";
            String user = "root";
            String pass = "";

            java.sql.Connection c = DriverManager.getConnection(url, user, pass);
            System.out.println("Connexion effective !");
            r = Query(mQuery, c);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return r;
    }
    private static ResultSet Query (String Query, java.sql.Connection c) throws SQLException{
        String req = Query;
        Statement s = c.createStatement();
        return s.executeQuery(req);
    }
    
}
