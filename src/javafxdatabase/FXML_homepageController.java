/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import oracle.jdbc.pool.OracleDataSource;

/**
 * FXML Controller class
 *
 * @author barqa
 */
public class FXML_homepageController implements Initializable {

    @FXML
    private Button bookhome;

    @FXML
    private Button libhome;

    @FXML
    private Button memhome;

    @FXML
    private Button issuehome;

    @FXML
    private Button exithome;

    @FXML
    private Button report;
 

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
  if(event.getSource()==bookhome){
     
      Parent main_home_page =FXMLLoader.load(getClass().getResource("FXML_bookpage.fxml"));
      Scene main_home_scene= new Scene (main_home_page);
       Stage app_stage0 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage0.setScene(main_home_scene);
        app_stage0.show();
       
        }
  else if(event.getSource()==libhome){
     
      Parent main_home_page1 =FXMLLoader.load(getClass().getResource("FXML_libpage.fxml"));
      Scene main_home_scene1= new Scene (main_home_page1);
       Stage app_stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage1.setScene(main_home_scene1);
        
        app_stage1.show();
        }
  
    
    else if(event.getSource()== memhome){
     
      Parent main_home_page2 =FXMLLoader.load(getClass().getResource("FXML_memberpage.fxml"));
      Scene main_home_scene2= new Scene (main_home_page2);
       Stage app_stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage2.setScene(main_home_scene2);
        app_stage2.show();
        }
  else if(event.getSource()== issuehome){
     
      Parent main_home_page3 =FXMLLoader.load(getClass().getResource("FXML_issuepage.fxml"));
      Scene main_home_scene3= new Scene (main_home_page3);
       Stage app_stage3 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage3.setScene(main_home_scene3);
        app_stage3.show();
        }
   else if(event.getSource()== exithome){
     
      Parent main_home_page4 =FXMLLoader.load(getClass().getResource("FXML_Signin.fxml"));
      Scene main_home_scene4= new Scene (main_home_page4);
       Stage app_stage4 = (Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage4.setScene(main_home_scene4);
        
        app_stage4.show();
        }
  else if(event.getSource()== report){
      try{
            OracleDataSource ods=new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            ods.setUser("project0");
            ods.setPassword("123456789");
            Connection con=ods.getConnection();
            InputStream input=new FileInputStream(new File("reportl.jrxml"));
            JasperDesign jasperDesign;
            jasperDesign =JRXmlLoader.load(input);
            JasperReport jasperReport;
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,null,con);
            OutputStream output=new FileOutputStream(new File("report.pdf"));
            JasperExportManager.exportReportToPdfStream(jasperPrint, output);
            output.close();
            input.close();  
        }
        catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex.toString());
        }
  }
    }    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
