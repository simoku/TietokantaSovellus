/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.sikuutti.tietokantasovellus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Kannan tiedot ja ajurit
 * @author sikuutti
 */
public class ConnectionMan {

    static Connection conn;

    /**
     *
     * @return
     */
    public static Connection getConnection() {
        try {
      String url = "jdbc:mysql://localhost:18875/";
//      String url = "jdbc:mysql://localhost:3306/";
            String dbName = "tsoha";
            String uname = "root";
            String pwd = "jeejee";

            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(url + dbName, uname, pwd);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return conn;
    }
}
