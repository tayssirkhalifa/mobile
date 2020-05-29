/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLfrontController implements Initializable {

    @FXML
    private Button btn_browser;
 private Connection con=null;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private ImageView imageView;
    private Image image;
        private ObservableList<product> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
private void showProductImage(String name)
{
        try {
                pst=con.prepareStatement("select image from produit where id=?");

            pst.setString(1, name);
            rs=pst.executeQuery();
            if(rs.next())
            {
            InputStream is=rs.getBinaryStream(2);
                OutputStream os =os=new FileOutputStream(new File("photo.jpg"));
                byte[]contents=new byte[1024];
                int size=0;
                while((size=is.read(contents))!=1){
                os.write(contents,0,size);
                }
                image=new Image("file:photo.jpg",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
                imageView.setImage(image);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
}   
    @FXML
    private void handleBrowser(ActionEvent event) {
        
    }
    
    
}
