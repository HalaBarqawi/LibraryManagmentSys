/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class FXML_issuepageController implements Initializable {
  /* @FXML
    private TextField trText;

    @FXML
    private TextField isbnText;

    @FXML
    private TextField idText;

 @FXML
    private DatePicker dofissue;

    @FXML
    private ComboBox<String> duration;

    @FXML
    private DatePicker returndate;


    @FXML
    private TextField actionText;

    @FXML
    private TextField fineText;*/
 @FXML
    private TextField search;
    @FXML
    private Button ihome;
    
    /*@FXML
    private Button update;*/
    @FXML
    private Button newtr;
        @FXML
    private Button delete;
         @FXML
    private Button load;
 @FXML
    private TableView<Issue> IssueTable;

    @FXML
    private TableColumn<Issue, Integer> trnColumn;

    @FXML
    private TableColumn<Issue, Integer> isbnColumn;

    @FXML
    private TableColumn<Issue, Integer> idColumn;

    @FXML
    private TableColumn<Issue, String> dofiColumn;

    @FXML
    private TableColumn<Issue, Integer> durationColumn;

    @FXML
    private TableColumn<Issue, String> returndColumn;

    @FXML
    private TableColumn<Issue, String> actionColumn;

    @FXML
    private TableColumn<Issue, Integer> fineColumn;
    /*String Duration;
    ObservableList<String> options =  FXCollections.observableArrayList(
        "14",
        "20",
        "30");*/
 private ResultSet rs=null;
    ObservableList <FXML_issuepageController.Issue>listB =FXCollections.observableArrayList();;
    
    PreparedStatement pst=null;
   public static class Issue {

        private final StringProperty id;

        private final StringProperty fine;
        private final StringProperty duration;
        private final StringProperty returnb;
        private final StringProperty action;
        private final StringProperty issued;
        private final StringProperty tran;
        private final StringProperty isbn;

        Issue(String tran, String isbn, String id, String issued, String duration, String returnb, String action, String fine) {
            this.id = new SimpleStringProperty(id);
            this.isbn = new SimpleStringProperty(isbn);
            this.returnb = new SimpleStringProperty(returnb);
            this.duration = new SimpleStringProperty(duration);
            this.fine = new SimpleStringProperty(fine);
            this.action = new SimpleStringProperty(action);

            this.issued = new SimpleStringProperty(issued);
            this.tran = new SimpleStringProperty(tran);
        }

        public String getId() {
            return id.get();
        }

        public String getFine() {
            return fine.get();
        }

        public String getDuration() {
            return duration.get();
        }

        public String getReturnb() {
            return returnb.get();
        }

        public String getAction() {
            return action.get();
        }

        public String getIssued() {
            return issued.get();
        }

        public String getTran() {
            return tran.get();
        }

        public String getIsbn() {
            return isbn.get();
        }

    }

    @FXML
    void goAction(ActionEvent event) throws IOException {
        if (event.getSource() == ihome) {

            Parent main_home_page3 = FXMLLoader.load(getClass().getResource("FXML_homepage.fxml"));
            Scene main_home_scene3 = new Scene(main_home_page3);
            Stage app_stage3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage3.setScene(main_home_scene3);
            app_stage3.show();
        } else if (event.getSource() == newtr) {

            Parent main_root = FXMLLoader.load(getClass().getResource("FXML_newtr.fxml"));
            Scene main_home_scene = new Scene(main_root);
            Stage app_stage = new Stage();
            app_stage.setScene(main_home_scene);
            app_stage.initModality(APPLICATION_MODAL);

            app_stage.show();
        }
          if(event.getSource()==load){
     
      Parent main_home_page =FXMLLoader.load(getClass().getResource("FXML_issuepage.fxml"));
      Scene main_home_scene= new Scene (main_home_page);
       Stage app_stage0 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage0.setScene(main_home_scene);
        
        app_stage0.show();
        }
          
         /* if(event.getSource()==update){
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                String str_url="jdbc:oracle:thin:@localhost:1521:orcl";
                Connection con =DriverManager.getConnection(str_url,"project0","123456789");
                Statement stmt=con.createStatement();
           PreparedStatement pts=null;
                String sql="Update BOOK_ISSUE set TRANS_NO = ?,BOOK_ISBN=?,MEMID=?,DATE_OF_ISSUE=?,THEDURATION_OF_THEBORROW=?,ACTUAL_RETURNDATE=?,ACTION=?,FINEAMOUNT=?";
                String query ="INSERT INTO book_issue(DATE_OF_ISSUE,ACTUAL_RETURNDATE)VALUES(?,?)";
                String TransNo=trText.getText();
                String member_ID=idText.getText();
                String fine_amount=fineText.getText();
                String ISBN=isbnText.getText();
                 LocalDate issue_date=dofissue.getValue();
                 LocalDate return_date=returndate.getValue();
                String Action=actionText.getText();
                pst = con.prepareStatement(sql);
                pts=con.prepareStatement(query);
                pst.setString(1, TransNo);
              pst.setString(2,ISBN);
              pst.setString(3, member_ID);
              pts.setDate(4,java.sql.Date.valueOf(issue_date));
              pst.setString(5,Duration);
              pts.setDate(6,java.sql.Date.valueOf(return_date));
              pst.setString(7,Action);
              pst.setString(8,fine_amount);
              pts.execute();
              int i =pst.executeUpdate();
              if(i==1){
             JOptionPane.showMessageDialog(null,"Update Successfuly !");
             }
                
                
                         
            } catch (SQLException ex) {
                Logger.getLogger(FXML_issuepageController.class.getName()).log(Level.SEVERE, null, ex);
            }
              
          }*/
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
           trnColumn.setCellValueFactory(new PropertyValueFactory<>("tran"));
           isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
              idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        returndColumn.setCellValueFactory(new PropertyValueFactory<>("returnb"));
        fineColumn.setCellValueFactory(new PropertyValueFactory<>("fine"));
        
        dofiColumn.setCellValueFactory(new PropertyValueFactory<>("issued"));
     
        loadDataFromDatabase();
                   FilteredList<FXML_issuepageController.Issue> filteredData= new FilteredList<>(listB,b->true);
      search.textProperty().addListener((obsevable,oldValue,newValue)->{
                  
       filteredData.setPredicate(Issue->{
           if (newValue==null|| newValue.isEmpty()){
               return true;
           }
           String lowerCaseFilter =newValue.toLowerCase();
           if(Issue.getIsbn().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
           else if(Issue.getAction().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Issue.getIssued().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Issue.getReturnb().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Issue.getId().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
             else if(Issue.getDuration().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
             }
           else
           return false;
       });
          
      });
        
      SortedList<FXML_issuepageController.Issue> sortedData=new SortedList<>(filteredData); 
      sortedData.comparatorProperty().bind(IssueTable.comparatorProperty());
      IssueTable.setItems(sortedData);
       //setCellValueFromTableToTextField();
    }
    private void loadDataFromDatabase() {
        try {
            mySqlConnect handler=new mySqlConnect();
            Connection conn= ConnectDb();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM BOOK_ISSUE");
           
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
            listB.add(new FXML_issuepageController.Issue(rs.getString(1), rs.getString(2), rs.getString(3),  rs.getString(4),
                    rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8)));
                
            }
        } catch (SQLException ex) {
           System.err.println("Error"+ex);
             }
       IssueTable.getItems().setAll(listB);
  }
public void Delete(ActionEvent event){
    String name=null;
          try {
              FXML_issuepageController.Issue issue = (FXML_issuepageController.Issue)IssueTable.getSelectionModel().getSelectedItem();
               name= issue.getTran();
              mySqlConnect handler=new mySqlConnect();
              Connection conn= ConnectDb();
              String Sql="delete from BOOK_ISSUE where TRANS_NO =? ";
              PreparedStatement ps=conn.prepareStatement(Sql);
           ps.setString(1, issue.getTran());
                  ps.executeUpdate();
                      ps.close();
                   
              
          } catch (SQLException ex) {
              Logger.getLogger(FXML_bookpageController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
        JOptionPane.showMessageDialog(null," Action #'"+name+"' deleted successfuly!");
}
/*private void setCellValueFromTableToTextField(){
    IssueTable.setOnMouseClicked(e -> {
    FXML_issuepageController.Issue b1 = IssueTable.getItems().get(IssueTable.getSelectionModel().getSelectedIndex());
       trText.setText(b1.getTran());
    isbnText.setText(b1.getIsbn());
    Duration=b1.getDuration();
     ObservableList<String> option =  FXCollections.observableArrayList(
        b1.getDuration());
 duration.setItems(option);

   // returndate.setText(b1.getReturnb());
    
    // dofissue.setText(b1.getIssued());
    //.setText(b1.getPublisher());
    actionText.setText(b1.getAction());
    fineText.setText(b1.getFine());
    
    });
    }
/* @FXML
    private void durationComboBox(ActionEvent event) {
    Duration=duration.getValue();
    }*/
}