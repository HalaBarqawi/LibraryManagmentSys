package javafxdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class mySqlConnect {
     public static Connection ConnectDb(){
     try{
          DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           String str_url="jdbc:oracle:thin:@localhost:1521:orcl";
           Connection con =DriverManager.getConnection(str_url,"project0","123456789");
           Statement stmt=con.createStatement();
           return con;
            }
     catch(Exception e){
         JOptionPane.showMessageDialog(null, e.toString()); 
         return null;
     }
      
    }
     
}