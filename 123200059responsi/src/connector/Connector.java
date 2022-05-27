/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connector;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *
 * @author ASUS
 */
public class Connector {
    String DBurl = "jdbc:mysql://localhost/movie_db?useLegacyDatetimeCode=false&serverTimezone=UTC";
    String DBusername = "root";
    String DBpassword = "";
    public Connection conn;
    Statement statement;
    public Connector() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Connection Success");
        }catch(Exception ex){
            System.out.println("Connection Failed");
        }
    }
}
