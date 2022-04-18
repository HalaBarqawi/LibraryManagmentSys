/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdatabase;

import com.sun.javaws.Main;
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
public class FXML_bookpageController implements Initializable {
      @FXML
    private TextField search;
        @FXML
    private Button backhome;
     @FXML
    private Button addbook;
     @FXML
    private Button deletebtn;
 @FXML
    private Button load;
    @FXML
    private Button update;
    @FXML
    private TableColumn<Book,Integer> isbnColumn;
    @FXML
    private TableColumn<Book,String> nameColumn;
    @FXML
    private TableColumn<Book,String> authorColumn;
    @FXML
    private TableColumn<Book,String> publisherColumn;
    @FXML
    private TableColumn<Book, String> languageColumn;
    @FXML
    private TableColumn<Book, Integer> yearColumn;
    @FXML
    private TableColumn<Book,String> genreColumn;
    @FXML
    private TableColumn<Book,Integer> quantityColumn;
    @FXML
    private TableView<Book> BookTable;
    @FXML
    private TextField isbnText;
    @FXML
    private TextField authorText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField publisherText;
    @FXML
    private TextField yearText;
    @FXML
    private TextField genreText;
    @FXML
    private TextField languageText;
    @FXML
    private TextField quantityText;
    @FXML
    private TextField ssnText;
    
    private ResultSet rs=null;
    ObservableList <Book>listB =FXCollections.observableArrayList();;
    
    PreparedStatement pst=null;
    @FXML
    private TableColumn<Book,String> ssnColumn;
    
   public static class Book{
    private final StringProperty isbn;
    private final StringProperty bookName;
    private final StringProperty author;
    private final StringProperty year;
    private final StringProperty publisher;
    private final StringProperty genre;
    private final StringProperty quantity;
    private final StringProperty ssn;
    private final StringProperty language;
     Book(String isbn,String bookName,String author,String year,String language,String genre,String publisher,String quantity,String ssn){
         this.isbn=new SimpleStringProperty(isbn);
         this.bookName=new SimpleStringProperty(bookName);
         this.author = new SimpleStringProperty(author);
        this.year = new SimpleStringProperty(year);
        this.publisher = new SimpleStringProperty(publisher);
        this.genre = new SimpleStringProperty(genre);
        this.quantity = new SimpleStringProperty(quantity);
        this.ssn = new SimpleStringProperty(ssn);
        this.language = new SimpleStringProperty(language);
     }

       

        public String getIsbn() {
            return isbn.get();
        }

        public String getBookName() {
            return bookName.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getYear() {
            return year.get();
        }

        public String getPublisher() {
            return publisher.get();
        }

        public String getGenre() {
            return genre.get();
        }

        public String getQuantity() {
            return quantity.get();
        }

        public String getSsn() {
            return ssn.get();
        }

        public String getLanguage() {
            return language.get();
        }
     
       }
    @FXML
 private void handleButtonhome(ActionEvent event) throws IOException {

   if(event.getSource()==backhome){
     
      Parent main_home_page =FXMLLoader.load(getClass().getResource("FXML_homepage.fxml"));
      Scene main_home_scene= new Scene (main_home_page);
       Stage app_stage0 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage0.setScene(main_home_scene);
        
        app_stage0.show();
        }
  
   else if(event.getSource()==addbook){
     
      Parent main_root =FXMLLoader.load(getClass().getResource("FXML_addbooks.fxml"));
      Scene main_home_scene= new Scene (main_root);
       Stage app_stage = new Stage();
        app_stage.setScene(main_home_scene);
        app_stage.initModality(APPLICATION_MODAL);
    
          app_stage.show();
        }
   if(event.getSource()==load){
     
      Parent main_home_page =FXMLLoader.load(getClass().getResource("FXML_bookpage.fxml"));
      Scene main_home_scene= new Scene (main_home_page);
       Stage app_stage0 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage0.setScene(main_home_scene);
        
        app_stage0.show();
        }
   else if(event.getSource()== update){
        String sql="Update BOOK set BOOK_NAME = ?,AUTHOR_NAME =?,PUBLICATION_YEAR = ?,WRITTEN_LANGUAGE = ?,GENRE = ?,PUBLISHER =?,QUANTITY = ?,SSN = ? where ISBN =?";
        try{ 
            String ISBN =isbnText.getText();
            String bookName = nameText.getText();
            String authorName = authorText.getText();
            String publicationYear=yearText.getText();
            String writtenLanguage=languageText.getText();
            String Genre =genreText.getText();
            String Publisher=publisherText.getText();
            String quantity =quantityText.getText();
            String SSNL=ssnText.getText();
            
            Connection con= ConnectDb();
            pst = con.prepareStatement(sql);
            
            pst.setString(1,bookName);
            pst.setString(2,authorName);
            pst.setString(3,publicationYear);
            pst.setString(4,writtenLanguage);
            pst.setString(5,Genre);
            pst.setString(6,Publisher);
            pst.setString(7,quantity);
            pst.setString(8,SSNL);
            pst.setString(9,ISBN);
            int i =pst.executeUpdate();
             if(i==1){
             JOptionPane.showMessageDialog(null,"Update Successfuly !");
             }
        } catch (SQLException ex) {  
           Logger.getLogger(FXML_bookpageController.class.getName()).log(Level.SEVERE, null, ex);
       }  
    }
  }
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        
        loadDataFromDatabase();
              FilteredList<Book> filteredData= new FilteredList<>(listB,b->true);
      search.textProperty().addListener((obsevable,oldValue,newValue)->{
                  
       filteredData.setPredicate(Book->{
           if (newValue==null|| newValue.isEmpty()){
               return true;
           }
           String lowerCaseFilter =newValue.toLowerCase();
           if(Book.getBookName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
           else if(Book.getAuthor().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Book.getYear().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Book.getPublisher().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
            else if(Book.getSsn().toLowerCase().indexOf(lowerCaseFilter)!=-1){
               return true;
           }
           else
           return false;
       });
          
      });
        
      SortedList<Book> sortedData=new SortedList<>(filteredData); 
      sortedData.comparatorProperty().bind(BookTable.comparatorProperty());
      BookTable.setItems(sortedData);
      setCellValueFromTableToTextField();
      
    }
     private void loadDataFromDatabase() {
        try {
            mySqlConnect handler=new mySqlConnect();
            Connection conn= ConnectDb();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM BOOK");
           
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
            listB.add(new Book(rs.getString("ISBN"), rs.getString("BOOK_NAME"), rs.getString("AUTHOR_NAME"),  rs.getString("PUBLICATION_YEAR"),
                    rs.getString("WRITTEN_LANGUAGE"), rs.getString("GENRE"),rs.getString("PUBLISHER"), rs.getString("QUANTITY"), rs.getString("SSN")));
                
            }
        } catch (SQLException ex) {
           System.err.println("Error"+ex);
             }
       BookTable.getItems().setAll(listB);
  }  
public void Delete(ActionEvent event){
    String name=null;
          try {
              Book book = (Book)BookTable.getSelectionModel().getSelectedItem();
               name= book.getBookName();
              mySqlConnect handler=new mySqlConnect();
              Connection conn= ConnectDb();
              String Sql="delete from book where ISBN=?";
              PreparedStatement ps=conn.prepareStatement(Sql);
           ps.setString(1, book.getIsbn());
                  ps.executeUpdate();
                      ps.close();
                   
              
          } catch (SQLException ex) {
              Logger.getLogger(FXML_bookpageController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
        JOptionPane.showMessageDialog(null," Book '"+name+"' deleted successfuly!");
}
private void setCellValueFromTableToTextField(){
    BookTable.setOnMouseClicked(e -> {
    Book b1 = BookTable.getItems().get(BookTable.getSelectionModel().getSelectedIndex());
    isbnText.setText(b1.getIsbn());
    nameText.setText(b1.getBookName());
    authorText.setText(b1.getAuthor());
    yearText.setText(b1.getYear());
    publisherText.setText(b1.getPublisher());
    languageText.setText(b1.getLanguage());
    genreText.setText(b1.getGenre());
    quantityText.setText(b1.getQuantity());
    ssnText.setText(b1.getSsn());
    });
    }

}