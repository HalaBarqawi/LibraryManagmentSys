/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.stage.Modality.APPLICATION_MODAL;
import javafx.stage.Stage;
import static javafxdatabase.mySqlConnect.ConnectDb;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author barqa
 */

public class FXML_libpageController implements Initializable {
@FXML
    private TextField search;
 @FXML
    private Button load;
 @FXML
    private Button delete;
    
    @FXML
    private Button Home;
    @FXML
    private Button addli;
 @FXML
    private TableView<Lib> LibTable;

    @FXML
    private TableColumn<Lib, Integer> ssnColumn;

    @FXML
    private TableColumn<Lib, String> fnameColumn;

    @FXML
    private TableColumn<Lib, String> snameColumn;

    @FXML
    private TableColumn<Lib, String> lnameColumn;

    @FXML
    private TableColumn<Lib, Integer> salaryColumn;

    @FXML
    private TableColumn<Lib, String> numberColumn;

    @FXML
    private TableColumn<Lib, String> cityColumn;

    @FXML
    private TableColumn<Lib, String> streetColumn;

    @FXML
    private TableColumn<Lib, String> emailColumn;

    @FXML
    private TableColumn<Lib, String> genderColumn;

    @FXML
    private TableColumn<Lib, String> birthColumn;

    private ResultSet rs=null;
    ObservableList <FXML_libpageController.Lib>listB =FXCollections.observableArrayList();;
    
    PreparedStatement pst=null;
     public static class Lib{
    private final StringProperty ssn;
    private final StringProperty fname;
    private final StringProperty sname;
    private final StringProperty lname;
    private final StringProperty gender;
    private final StringProperty birth;
    private final StringProperty city;
    private final StringProperty email;
    private final StringProperty number;
     private final StringProperty street ;
      private final StringProperty salary ;
     Lib(String ssn,String fname,String sname,String lname,String street ,String city,String gender , String birth,String salary,String email,String number){
         this.ssn=new SimpleStringProperty(ssn);
         this.fname=new SimpleStringProperty(fname);
         this.sname = new SimpleStringProperty(sname);
        this.lname = new SimpleStringProperty(lname);
        this.birth = new SimpleStringProperty(birth);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.salary = new SimpleStringProperty(salary);
        this.gender = new SimpleStringProperty(gender);
         this.number = new SimpleStringProperty(number);
                  this.email = new SimpleStringProperty(email);
     }

        public String getSsn() {
            return ssn.get();
        }

        public String getFname() {
            return fname.get();
        }

        public String getSname() {
            return sname.get();
        }

        public String getLname() {
            return lname.get();
        }

        public String getGender() {
            return gender.get();
        }

        public String getBirth() {
            return birth.get();
        }

        public String getCity() {
            return city.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getNumber() {
            return number.get();
        }

        public String getStreet() {
            return street.get();
        }

        public String getSalary() {
            return salary.get();
        }
     
     
     }
    
    @FXML
    void homeperformed(ActionEvent event) throws IOException {
if(event.getSource()==Home){
     
      Parent main_home_page1 =FXMLLoader.load(getClass().getResource("FXML_homepage.fxml"));
      Scene main_home_scene1= new Scene (main_home_page1);
       Stage app_stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage1.setScene(main_home_scene1);
        app_stage1.show();
        }
  else if(event.getSource()==addli){
     
      Parent main_root =FXMLLoader.load(getClass().getResource("FXML_newlib.fxml"));
      Scene main_home_scene= new Scene (main_root);
       Stage app_stage = new Stage();
        app_stage.setScene(main_home_scene);
        app_stage.initModality(APPLICATION_MODAL);
      
       
          app_stage.show();
        }
  else if(event.getSource()==load){
     
      Parent main_home_page =FXMLLoader.load(getClass().getResource("FXML_libpage.fxml"));
      Scene main_home_scene= new Scene (main_home_page);
       Stage app_stage0 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage0.setScene(main_home_scene);
        
        app_stage0.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           ssnColumn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
        snameColumn.setCellValueFactory(new PropertyValueFactory<>("sname"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("birth"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
         emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        loadDataFromDatabase();
           FilteredList<FXML_libpageController.Lib> filteredData= new FilteredList<>(listB,b->true);
      search.textProperty().addListener((obsevable,oldValue,newValue)->{
                  
       filteredData.setPredicate(Lib->{
           if (newValue==null|| newValue.isEmpty()){
               return true;
           }
           String lowerCaseFilter =newValue.toLowerCase();
           if(Lib.getFname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
           else if(Lib.getLname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Lib.getSname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Lib.getSalary().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Lib.getNumber().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
           else
           return false;
       });
          
      });
        
      SortedList<FXML_libpageController.Lib> sortedData=new SortedList<>(filteredData); 
      sortedData.comparatorProperty().bind(LibTable.comparatorProperty());
      LibTable.setItems(sortedData);
      
        
    }    
    
     private void loadDataFromDatabase() {
        try {
            mySqlConnect handler=new mySqlConnect();
            Connection conn= ConnectDb();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM LIBRARIAN");
           
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
            listB.add(new FXML_libpageController.Lib(rs.getString(1), rs.getString(2), rs.getString(3),  rs.getString(4),
                    rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9), rs.getString(10), rs.getString(11)));
                
            }
        } catch (SQLException ex) {
           System.err.println("Error"+ex);
             }
       LibTable.getItems().setAll(listB);
  }  
     public void Delete(ActionEvent event){
    String name=null;
          try {
              FXML_libpageController.Lib lib = (FXML_libpageController.Lib)LibTable.getSelectionModel().getSelectedItem();
               name= lib.getFname()+" "+lib.getSname()+" "+lib.getLname();
              mySqlConnect handler=new mySqlConnect();
              Connection conn= ConnectDb();
              String Sql="delete from LIBRARIAN where SSN=?";
              PreparedStatement ps=conn.prepareStatement(Sql);
           ps.setString(1, lib.getSsn());
                  ps.executeUpdate();
                 
                  ps.close();
                   
              
          } catch (SQLException ex) {
              Logger.getLogger(FXML_bookpageController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
        JOptionPane.showMessageDialog(null,"Librarian' "+name+"'Deleted successfuly!");
}
}
