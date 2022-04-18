/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author barqa
 */
public class FXML_addbooksController implements Initializable {
        @FXML
    private TextField isbn;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private TextField genre;

    @FXML
    private TextField ssn;

    @FXML
    private TextField bookname;

    @FXML
   private TextField year1;

    @FXML
    private TextField language;

    @FXML
    private TextField quantity;

    @FXML
    private Button add;

    @FXML
    void okAction(ActionEvent event) {
 if(event.getSource()==add){
           try{
           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           String str_url="jdbc:oracle:thin:@localhost:1521:orcl";
           Connection con =DriverManager.getConnection(str_url,"project0","123456789");
               java.sql.Statement stmt=con.createStatement();
               PreparedStatement pt1=null;
           String isbn =this.isbn.getText();
           String author=this.author.getText();
           String publisher=this.publisher.getText(); 
           String Genre=this.genre.getText();
           String BookName=this.bookname.getText();
      String year=this.year1.getText();
           String language=this.language.getText();
           String quantity=this.quantity.getText();
           String SSN=this.ssn.getText();
           if(isbn.isEmpty()||author.isEmpty()||publisher.isEmpty()||Genre.isEmpty()||
                   BookName.isEmpty()||year.isEmpty()||language.isEmpty()||quantity.isEmpty()||SSN.isEmpty()){
               System.out.println("please enter all textfield");
           }
           else{ 
              String test="select Ssn from LIBRARIAN";
           boolean flag= false;
           
             pt1=con.prepareStatement(test);
              ResultSet rs=pt1.executeQuery();
              while(rs.next()){
    if (rs.getString(1).equals(SSN)){
      flag=true;
      break;
    }
   
 }
              if(flag){
           String str_stmt="insert into Book values('"+isbn+"','"+BookName+"','"+author+"','"+year+"','"+language+"','"
                   +Genre+"','"+publisher+"','"+quantity+"','"+SSN+"')";
           System.out.println(str_stmt);
           stmt.executeUpdate(str_stmt);
           con.commit();
           con.close();
           JOptionPane.showMessageDialog(null,"Added successfuly!!");}
                              else
            JOptionPane.showMessageDialog(null,"ENTERED SSN is  NOT VALID");
           this.isbn.setText("");
           this.author.setText("");
           this.publisher.setText("");
           this.genre.setText("");
           this.language.setText("");
           this.ssn.setText("");
           this.bookname.setText("");
           this.year1.setText("");
           this.quantity.setText("");
           }}
       
           
           catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex.toString());
           }
       } 
        
        
        
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    }    
    
}
