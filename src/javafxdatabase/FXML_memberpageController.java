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
public class FXML_memberpageController implements Initializable {
@FXML
    private TextField search;
    @FXML
    private Button homeb;
 @FXML
    private Button delete;
     @FXML
    private Button addm;
     
    @FXML
    private Button load;
    @FXML
    private TableView<Member> MemberTable;

    @FXML
    private TableColumn<Member, Integer> idColumn;

    @FXML
    private TableColumn<Member, String> fnameColumn;

    @FXML
    private TableColumn<Member, String> snameColumn;

    @FXML
    private TableColumn<Member, String> lnameColumn;

    @FXML
    private TableColumn<Member, String> genderColumn;

    @FXML
    private TableColumn<Member, String> birthColumn;

    @FXML
    private TableColumn<Member, String> datepColumn;

    @FXML
    private TableColumn<Member, Integer> numberColumn;

    @FXML
    private TableColumn<Member, Integer> priceColumn;

    @FXML
    private TableColumn<Member, String> ssnColumn;
 private ResultSet rs=null;
    ObservableList <FXML_memberpageController.Member>listB =FXCollections.observableArrayList();;
    
    PreparedStatement pst=null;
    
    public static class Member{
    private final StringProperty id;
    private final StringProperty fname;
    private final StringProperty sname;
    private final StringProperty lname;
    private final StringProperty gender;
    private final StringProperty birth;
    private final StringProperty datep;
    private final StringProperty ssn;
    private final StringProperty number;
     private final StringProperty price ;
     Member(String id,String fname,String sname,String lname,String birth,String number,String gender , String datep,String price,String ssn){
         this.id=new SimpleStringProperty(id);
         this.fname=new SimpleStringProperty(fname);
         this.sname = new SimpleStringProperty(sname);
        this.lname = new SimpleStringProperty(lname);
        this.birth = new SimpleStringProperty(birth);
        this.datep = new SimpleStringProperty(datep);
        this.price = new SimpleStringProperty(price);
        this.ssn = new SimpleStringProperty(ssn);
        this.gender = new SimpleStringProperty(gender);
         this.number = new SimpleStringProperty(number);
     }

        public String getId() {
            return id.get();
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

        public StringProperty getBirth() {
            return birth;
        }

        public String getDatep() {
            return datep.get();
        }

        public String getSsn() {
            return ssn.get();
        }

        public String getNumber() {
            return number.get();
        }

        public String getPrice() {
            return price.get();
        }
    
    }
    
    
    
    @FXML
    void BackActon(ActionEvent event) throws IOException {
if(event.getSource()== homeb){
     
      Parent main_home_page2 =FXMLLoader.load(getClass().getResource("FXML_homepage.fxml"));
      Scene main_home_scene2= new Scene (main_home_page2);
       Stage app_stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage2.setScene(main_home_scene2);
          app_stage2.resizableProperty().setValue(false);
        app_stage2.show();
        }
else if(event.getSource()==addm){
     
       Parent main_root =FXMLLoader.load(getClass().getResource("FXML_newmember.fxml"));
      Scene main_home_scene= new Scene (main_root);
       Stage app_stage = new Stage();
        app_stage.setScene(main_home_scene);
        app_stage.initModality(APPLICATION_MODAL);
      
       
          app_stage.show();
        }
   if(event.getSource()==load){
     
      Parent main_home_page =FXMLLoader.load(getClass().getResource("FXML_memberpage.fxml"));
      Scene main_home_scene= new Scene (main_home_page);
       Stage app_stage0 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage0.setScene(main_home_scene);
        
        app_stage0.show();
        }
    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("fname"));
        snameColumn.setCellValueFactory(new PropertyValueFactory<>("sname"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lname"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("birth"));
        datepColumn.setCellValueFactory(new PropertyValueFactory<>("datep"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        loadDataFromDatabase();
        
      FilteredList<Member> filteredData= new FilteredList<>(listB,b->true);
      search.textProperty().addListener((obsevable,oldValue,newValue)->{
                  
       filteredData.setPredicate(Member->{
           if (newValue==null|| newValue.isEmpty()){
               return true;
           }
           String lowerCaseFilter =newValue.toLowerCase();
           if(Member.getFname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
           else if(Member.getLname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Member.getSname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Member.getDatep().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Member.getNumber().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
           else
           return false;
       });
          
      });
        
      SortedList<Member> sortedData=new SortedList<>(filteredData); 
      sortedData.comparatorProperty().bind(MemberTable.comparatorProperty());
      MemberTable.setItems(sortedData);
      
        
        
        
    }    
    private void loadDataFromDatabase() {
        try {
            mySqlConnect handler=new mySqlConnect();
            Connection conn= ConnectDb();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM MEMBER_RECORD");
           
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
            listB.add(new FXML_memberpageController.Member(rs.getString(1), rs.getString(2), rs.getString(3),  rs.getString(4),
                    rs.getString(5), rs.getString(6),rs.getString(8), rs.getString(9),rs.getString(10), rs.getString(11)));
                
            }
        } catch (SQLException ex) {
           System.err.println("Error"+ex);
             }
       MemberTable.getItems().setAll(listB);
  }  
    public void Delete(ActionEvent event){
   String name=null;
          try {
              FXML_memberpageController.Member member= (FXML_memberpageController.Member)MemberTable.getSelectionModel().getSelectedItem();
               name= member.getFname()+" "+member.getSname()+" "+member.getLname();
              mySqlConnect handler=new mySqlConnect();
              Connection conn= ConnectDb();
              String Sql="delete from MEMBER_RECORD where IDMEM=?";
              PreparedStatement ps=conn.prepareStatement(Sql);
           ps.setString(1, member.getId());
                  ps.executeUpdate();
                       ps.close();
                   
              
          } catch (SQLException ex) {
              Logger.getLogger(FXML_bookpageController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
    JOptionPane.showMessageDialog(null," Member '"+name+"' deleted successfuly!");
}
}
