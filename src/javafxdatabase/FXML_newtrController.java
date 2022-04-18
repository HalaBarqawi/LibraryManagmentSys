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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author barqa
 */
public class FXML_newtrController implements Initializable {

   
        @FXML
    private Button add;
    @FXML
    private TextField memberID;
    @FXML
    private DatePicker DOI;
    @FXML
    private DatePicker DR;
    @FXML
    private TextField fine;
   
    @FXML
    private TextField isbn;
    @FXML
    private ComboBox<String> duration;
    @FXML
    private TextField action;
    @FXML
    private TextField trans_No;
    
    @FXML
    private TextField ssn;
    String Duration;
    ObservableList<String> options =  FXCollections.observableArrayList(
        "14",
        "20",
        "30");
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         duration.setItems(options);
    }    

    @FXML
    private void transAction(ActionEvent event) {
        if(event.getSource()==add){
        try{
           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           String str_url="jdbc:oracle:thin:@localhost:1521:orcl";
           Connection con =DriverManager.getConnection(str_url,"project0","123456789");
           Statement stmt=con.createStatement();
           PreparedStatement pts=null;
           PreparedStatement pt1=null;
           PreparedStatement pt2=null;
           PreparedStatement pt3=null;
            PreparedStatement pt4=null;
           String TransNo=trans_No.getText();
           String member_ID=memberID.getText();
           String fine_amount=fine.getText();
           String ISBN=isbn.getText();
           String Ssn=ssn.getText();
           LocalDate issue_date=DOI.getValue();
           LocalDate return_date=DR.getValue();
           String Action=action.getText();
           
           if(ISBN.isEmpty()||TransNo.isEmpty()||member_ID.isEmpty()||
                   fine_amount.isEmpty()||fine_amount.isEmpty()||Action.isEmpty()){
               System.out.println("please fill all fields");
           }
  else{String query ="INSERT INTO book_issue(TRANS_NO,BOOK_ISBN,MEMID,DATE_OF_ISSUE,THEDURATION_OF_THEBORROW,ACTUAL_RETURNDATE,ACTION,FINEAMOUNT)VALUES(?,?,?,?,?,?,?,?)";
  String qstmt="INSERT INTO MAKES_TRANS(SSN,TRANS_NO)VALUES(?,?)";
   String test="select idmem from Member_RecoRD";
   String test1="select ISBN from BOOK";
   boolean flag= false;
   boolean flagbook=false;
   pt1=con.prepareStatement(test);
    ResultSet rs=pt1.executeQuery();
while(rs.next()){
    if (rs.getString(1).equals(memberID.getText())){
      flag=true;
      break;
    }
   
    
}
   pt2=con.prepareStatement(test1);
    ResultSet rs1=pt2.executeQuery();
while(rs1.next()){
    if (rs1.getString(1).equals(isbn.getText())){
      flagbook=true;
      break;
    }
   }
  String test3="select Ssn from LIBRARIAN";
           boolean flag1= false;
             pt4=con.prepareStatement(test3);
              ResultSet rs3=pt4.executeQuery();
              while(rs3.next()){
    if (rs3.getString(1).equals(ssn.getText())){
      flag=true;
      break;
    }
 }
if (flag&&flagbook){
              pts=con.prepareStatement(query);
              pts.setString(1, trans_No.getText());
              pts.setString(2,isbn.getText());
              pts.setString(3, memberID.getText());
              pts.setDate(4,java.sql.Date.valueOf(issue_date));
              pts.setString(5,Duration);
              pts.setDate(6,java.sql.Date.valueOf(return_date));
              pts.setString(7,action.getText());
              pts.setString(8,fine.getText());
              pt3=con.prepareStatement(qstmt);
               pt3.setString(1, ssn.getText());
              pt3.setString(2, trans_No.getText());
              pts.execute();
                pt3.execute();
              con.commit();
              con.close();
              JOptionPane.showMessageDialog(null,"Added successfuly!!");}
              else{
   
    JOptionPane.showMessageDialog(null,"ENTERED ISBN OR ID OR ISBN ARE  NOT VALID!");
   
}
              trans_No.setText("");
              isbn.setText("");
              memberID.setText("");
              action.setText("");
              fine.setText("");
             
 }    
            
           }
         
          catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex.toString());
           }
        }
    }

    @FXML
    private void durationComboBox(ActionEvent event) {
    Duration=duration.getValue();
    }
    
}