/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author barqa
 */
public class FXML_SigninController implements Initializable {
   @FXML
    private Button loginButton;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Label label;

    @FXML
    void goAction(ActionEvent event) throws IOException {
      if(event.getSource()==loginButton){
      if(user.getText().equals("admin")&& password.getText().equals("12345")){
      Parent main_home_page =FXMLLoader.load(getClass().getResource("FXML_homepage.fxml"));
      Scene main_home_scene= new Scene (main_home_page);
       Stage app_stage0 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage0.setScene(main_home_scene);
        
        app_stage0.show();}
        
      else label.setVisible(true);}
    }
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        label.setVisible(false);
    }    
    
}
