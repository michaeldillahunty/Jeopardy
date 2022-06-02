/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import static com.oracle.jrockit.jfr.Transition.To;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* 
    References: 
    https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
*/


/**
 *
 * @author dillahuntym
 */
public class DataBaseConnection{
    public String userName = "jeopardydb2";
    public String password = "jeopardydb2";
    
    public Properties connectionProps = new Properties();
    
    public String connectionURL = "jdbc:mariadb://jeopardydb2.cpqqceh4p3x6.us-east-2.rds.amazonaws.com:3306/jeopardydb2";
    
                       
    public Connection getConnection() throws SQLException {
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
          } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
          }
        
//        try{
//             try {
//                 Class.forName("com.mysql.jdbc.Driver").newInstance();
//             } catch (InstantiationException ex) {
//                 Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
//             } catch (IllegalAccessException ex) {
//                 Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
//             }
//             System.out.println("Driver loaded.");
//         } catch (ClassNotFoundException e){
//             System.out.println("Error loading the driver.");
//             System.out.println("DatabaseSupport.uploadRecordObject returning false");
//         }
        
        Connection conn = null;
        
        conn = DriverManager.getConnection(connectionURL, connectionProps);
        

        

//        if (this.dbms.equals("mysql")) {
//            conn = DriverManager.getConnection(
//                       "jdbc:" + this.dbms + "://" +
//                       this.serverName +
//                       ":" + this.portNumber + "/",
//                       connectionProps);
//        } else if (this.dbms.equals("derby")) {
//            conn = DriverManager.getConnection(
//                       "jdbc:" + this.dbms + ":" +
//                       this.dbName +
//                       ";create=true",
//                       connectionProps);
//        }
        System.out.println("Connected to database");
        return conn;
        }   
    
    public String constructUploadQueryString(String name, Integer score){
        String scoreString = score.toString();
        String constructUploadQueryString = "";
        
        constructUploadQueryString = "INSERT INTO UserScoreTable(UserID, Score)"+"\nVALUES ('"+name+"',"+scoreString+")";

        return constructUploadQueryString;
    }
    
    public boolean uploadingNameScore(String constructUploadQueryString){
        Statement statement = null;
        Connection currentConnection = null;

        try {
            currentConnection = getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            statement = currentConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            statement.executeUpdate(constructUploadQueryString);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public ArrayList<String> viewTable(Connection con) throws SQLException {
        String query = "select UserID, Score from UserScoreTable";
        ArrayList<String> tableContent = new ArrayList<>();
        try (Statement stmt = con.createStatement()) {
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
          String name = rs.getString("UserID");
          Integer score = rs.getInt("Score");
          System.out.println(name + ", " + score + ", ");
          
          tableContent.add(name);
          tableContent.add(score.toString());
        }
        return tableContent;
      } catch (SQLException e) {
  //      getConnection().printSQLException(e);
      }
        return tableContent;
    }
    
}
