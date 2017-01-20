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

    public static void bConnect(String mQuery) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://localhost/M2lJava";
            String user = "root";
            String pass = "";

            java.sql.Connection c = DriverManager.getConnection(url, user, pass);
            System.out.println("Connexion effective !");
            Query(mQuery, c);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    private static void Query (String Query, java.sql.Connection c) throws SQLException{
        String req = Query;
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(req);
        while (rs.next())
        {
            System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4));
        }
    }
    public static void main(String[] args){
        bConnect("select * from users");
    }
    
}
