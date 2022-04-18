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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class FXML_newlibController implements Initializable {

    
    @FXML
    private Button cancelLib;
    @FXML
    private Button addLib;
    @FXML
    private TextField ssn;
    @FXML
    private DatePicker BD;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField salary;
    @FXML
    private TextField FN;
    @FXML
    private TextField MN;
    @FXML
    private TextField LN;
    @FXML
    private TextField city;
    @FXML
    private TextField street;
    @FXML
    private CheckBox FemaleCheck;
    @FXML
    private CheckBox maleCheck;
    String gender;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LibrarianAction(ActionEvent event) {
        if(event.getSource()==addLib){
         try{
           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           String str_url="jdbc:oracle:thin:@localhost:1521:orcl";
           Connection con =DriverManager.getConnection(str_url,"project0","123456789");
           Statement stmt=con.createStatement();
           PreparedStatement pts=null;
           PreparedStatement pt1=null;
           String SSN =this.ssn.getText();
           String firstName=this.FN.getText();
           String middleName=this.MN.getText();
           String lastName=this.LN.getText();
           String City=this.city.getText();
           String Street=this.street.getText();
           String emailAdd=this.email.getText();
           String phoneNo=this.phone.getText();
           String Salary=this.salary.getText();
           LocalDate value=BD.getValue();
           
           if(SSN.isEmpty()||firstName.isEmpty()||middleName.isEmpty()||lastName.isEmpty()||
                   City.isEmpty()||Street.isEmpty()||emailAdd.isEmpty()||phoneNo.isEmpty()||Salary.isEmpty()){
               System.out.println("please fill all fields");
           }
           else{ 
        String query ="INSERT INTO librarian(SSN,first_name,middle_name,last_name,st_name,city_name,gender,birthdate,salary,email_address,phone_no)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
      
              
              pts=con.prepareStatement(query);
              pts.setString(1, ssn.getText());
              pts.setString(2, FN.getText());
              pts.setString(3, MN.getText());
              pts.setString(4, LN.getText());
              pts.setString(5, street.getText());
              pts.setString(6, city.getText());
              pts.setString(7, gender);
              pts.setDate(8, java.sql.Date.valueOf(value));
              pts.setString(9, salary.getText());
              pts.setString(10, email.getText());
              pts.setString(11,phone.getText());
              pts.execute();
        
              con.commit();
              con.close();
              JOptionPane.showMessageDialog(null,"Added Successfuly!");}

              ssn.setText("");
              FN.setText("");
              MN.setText("");
              LN.setText("");
              street.setText("");
              city.setText("");
             
              salary.setText("");
              email.setText("");
              phone.setText("");
           
          
           }
           catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex.toString());
           }
       } 
        
        }

    @FXML
    private void checkGender(ActionEvent event) {
        if(this.FemaleCheck.isSelected())
            gender="Female";
        else 
            gender="male";
    }
    }
