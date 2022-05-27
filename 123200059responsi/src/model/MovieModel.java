/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connector.Connector;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class MovieModel {
    protected String moviename;
    protected double plot,character,acting,score;
    
    Connector con;
    Statement stmt;
    
    public MovieModel(){
        con = new Connector();
    }
    
    public void createMovie(String title, double plot, double character, double acting){
       
       try{
           score = (int) ((plot + character + acting)/3);
           stmt = (Statement) con.conn.createStatement();
           String sql = "INSERT INTO movie VALUES('"+title+"','"+plot+"','"+character+"','"+acting+"','"+score+"')";     
           stmt.executeUpdate(sql);
           JOptionPane.showMessageDialog(null, "Input Success");
           
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
    }
    
    public String[][] readMovie(){   
        try{
            int totaldata = 0;
            
            stmt = (Statement) con.conn.createStatement();
            String data[][] = new String[getData()][5];
            String query = "select * from movie"; 
            ResultSet resultSet = stmt.executeQuery(query);
            
           
            while (resultSet.next()){
                data[totaldata][0] = resultSet.getString("title");
                data[totaldata][1] = String.valueOf(resultSet.getDouble("plot"));                
                data[totaldata][2] = String.valueOf(resultSet.getDouble("character"));
                data[totaldata][3] = String.valueOf(resultSet.getDouble("acting"));
                data[totaldata][4] = String.valueOf(resultSet.getDouble("score"));
                totaldata++;
            }
            
            JOptionPane.showMessageDialog(null, "Read Success");
            return data;
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Read Failed");
           return null;
       }
    }
    
    public void updateMovie(String title, double plot, double character, double acting){
        
        try{
            int totaldata = 0;
            
            score = (int) ((plot + character + acting)/3);
           
            stmt = (Statement) con.conn.createStatement();
            
            String query1 = "select * from movie where title = '" + title + "'";
            
            ResultSet res = stmt.executeQuery(query1);
            
            while(res.next()){
                totaldata++;
            }
          
            if (totaldata == 0) {
                JOptionPane.showMessageDialog(null, "Data not Found");
            }
            
            else{
                
                String query = "UPDATE movie SET plot='" + plot + "',character='" + character + "',acting = '" + acting + "',score='"+ score+"' WHERE title= '" + title + "'" ;  
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Update Success");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    
    }
    
    public int getData(){
        int totaldata = 0;
        try{
            stmt = con.conn.createStatement();
            String query = "Select * from movie";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){ 
                totaldata++;
            }
            return totaldata;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
    
    public void deleteMovie (String title) {
        try{
            String query = "DELETE FROM movie WHERE title = '"+title+"'";
            stmt = con.conn.createStatement();
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Delete Success");
            
        }catch(SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
    
    
}
