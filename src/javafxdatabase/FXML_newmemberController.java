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

public class FXML_newmemberController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField FN;
    @FXML
    private TextField LN;
    @FXML
    private TextField emailAdd;
    @FXML
    private TextField phone;
    @FXML
    private CheckBox femaleCheck;
    @FXML
    private CheckBox maleCheck;
    @FXML
    private DatePicker DB;
    @FXML
    private DatePicker DP;
  
    @FXML
    private Button add;
   
    @FXML
    private TextField MN;
    String gender;
    @FXML
    private TextField ssn;

    @FXML
    private TextField paidp;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void genderCheck(ActionEvent event) {
        if(this.femaleCheck.isSelected())
            gender="Female";
        else 
            gender="male";
    }

    @FXML
    private void memberAction(ActionEvent event) {
        if(event.getSource()==add){
         try{
           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
           String str_url="jdbc:oracle:thin:@localhost:1521:orcl";
           Connection con =DriverManager.getConnection(str_url,"project0","123456789");
           Statement stmt=con.createStatement();
           PreparedStatement pts=null;
                PreparedStatement pt1=null;
           String ID=id.getText();
           String first_name=FN.getText();
           String middle_name=MN.getText();
           String last_name=LN.getText();
           String email=emailAdd.getText();
           String phoneNo=phone.getText();
           String price_paid=paidp.getText();
           String SSN=ssn.getText();
           LocalDate birth_date=DB.getValue();
           LocalDate parti_date=DP.getValue();
           
           if(ID.isEmpty()||first_name.isEmpty()||middle_name.isEmpty()||last_name.isEmpty()||
                   email.isEmpty()||phoneNo.isEmpty()||price_paid.isEmpty()||phoneNo.isEmpty()){
               System.out.println("please fill all fields");
           }
  else{String query ="INSERT INTO member_record(IDMEM,F_NAME,M_NAME,L_NAME,BIRTH_OF_DATE,PHONNUMBER,EMAILADD,GENDER,DATE_OF_PARTICIPATION,THEPRICEPAID,LSSN)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
       String test="select Ssn from LIBRARIAN";
           boolean flag= false;
             pt1=con.prepareStatement(test);
              ResultSet rs=pt1.executeQuery();
              while(rs.next()){
    if (rs.getString(1).equals(ssn.getText())){
      flag=true;
      break;
    }
 }
              if (flag){
              pts=con.prepareStatement(query);
              pts.setString(1, id.getText());
              pts.setString(2, FN.getText());
              pts.setString(3, MN.getText());
              pts.setString(4, LN.getText());
              pts.setDate(5,java.sql.Date.valueOf(birth_date));
              pts.setString(6,phone.getText());
              pts.setString(7,emailAdd.getText());
              pts.setString(8, gender);
              pts.setDate(9,java.sql.Date.valueOf(parti_date));
              pts.setString(10,paidp.getText());
              pts.setString(11,ssn.getText());
              
              pts.execute();
              con.commit();
              con.close();
              JOptionPane.showMessageDialog(null,"Added successfuly!!");}
                             else
            JOptionPane.showMessageDialog(null,"ENTERED SSN is  NOT FOUNDED!");
              id.setText("");
              FN.setText("");
              MN.setText("");
              LN.setText("");
              phone.setText("");
              emailAdd.setText("");
              paidp.setText("");
              ssn.setText("");
           } }
          catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex.toString());
           }
        
        }
    }
}
