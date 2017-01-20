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

    public static ResultSet bConnect() {
        ResultSet r = null ;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost/M2lJava";
            String user = "root";
            String pass = "";

            java.sql.Connection c = DriverManager.getConnection(url, user, pass);
            System.out.println("Connexion effective !");
            r = Query(c);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return r;
    }
    private static ResultSet Query (java.sql.Connection c) throws SQLException{
        Queries q = new Queries("select", "users");
        Statement s = c.createStatement();
        return s.executeQuery(q.getQuery());
    }
}