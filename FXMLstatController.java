/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLstatController implements Initializable {

    @FXML
    private PieChart piechart;
private Connection con=null;
    private PreparedStatement pst;
        
    private ResultSet rs;
ObservableList<PieChart.Data>piechartdata;
ArrayList<Integer> quantite=new ArrayList<Integer>();
ArrayList<String> nom=new ArrayList<String>();
    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    public void loadData()
    {
        piechartdata=FXCollections.observableArrayList();
         try {
            con=DBC.DBConnection.productConnection();
            pst=con.prepareStatement("SELECT * FROM produit");
            rs = pst.executeQuery();
            while(rs.next())
            {piechartdata.add(new PieChart.Data(rs.getString("nom"),rs.getInt("quantite")));
                nom.add(rs.getString("nom"));
             quantite.add(rs.getInt("quantite"));

            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(FXMLstatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadData();
       piechart.setData(piechartdata);
       
    }    

    @FXML
    private void logout(ActionEvent event) {
        try {
            Stage Stage =(Stage) btn_logout.getScene().getWindow();
            Stage.close();
            
            
            Parent root = FXMLLoader.load(getClass().getResource("FXMLlogin.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage.setScene(scene);
            Stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Stage Stage =(Stage) btn_back.getScene().getWindow();
            Stage.close();
            
            
            Parent root = FXMLLoader.load(getClass().getResource("FXMLback.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage.setScene(scene);
            Stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
