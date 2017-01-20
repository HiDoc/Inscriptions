/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Flo
 */
public class Connect {

    public static void bConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://localhost/M2lJava";
            String user = "root";
            String passwd = "";

            java.sql.Connection c = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");
            String req = "select * from users";
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next())
            {
             System.out.println(rs.getInt(1) + " : " + rs.getString(2));
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[]args){
        bConnect();
    }
}
