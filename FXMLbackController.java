/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import static com.itextpdf.text.pdf.BidiOrder.PDF;
import static com.itextpdf.text.pdf.PdfName.PDF;
import com.pdfjet.Letter;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLbackController implements Initializable {

    @FXML
    private TableColumn<?, ?> columnID;
    @FXML
    private TableColumn<?, ?> columnNAME;
    @FXML
    private TableColumn<?, ?> columnPRICE;
     private Connection con=null;
    private PreparedStatement pst;
    private ResultSet rs;
    private ObservableList<product> data;
    @FXML
    private TableView<product> tableproduct;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_quantite;
    @FXML
    private Button btn_insert;
    @FXML
    private Button btn_browser;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private int pid;
    private final Desktop deskTop=Desktop.getDesktop();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imageView;
private Image image;
private FileInputStream fis;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private TextField txt_search;
    @FXML
    private TableColumn<?, ?> columnQuantite;
    @FXML
    private TableColumn<?, ?> columnDESC;
    @FXML
    private TableColumn<?, ?> columnTYPE;
    @FXML
    private TableColumn<?, ?> columnACHAT;
    @FXML
    private TableColumn<?, ?> columnTaille;
    @FXML
    private TableColumn<?, ?> columnVIEWS;
    @FXML
    private TextField txt_type;
    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_taille;
    @FXML
    private TextField txt_views;
    @FXML
    private TextField txt_achats;
    @FXML
    private Button btn_stat;
    @FXML
    private TableColumn<?, ?> columnMARQUE;
    @FXML
    private TextField txt_marque;
    @FXML
    private Button btn_loguot;
    @FXML
    private Button btn_pdf;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               con=DBC.DBConnection.productConnection();
               data=FXCollections.observableArrayList();
                       setCellTable();
                      loadDataFromDatabase();
                      setCellValueFromTableToTextFied();
                      searchProduct();
                      fileChooser=new FileChooser();
                      fileChooser.getExtensionFilters().addAll(
                      new FileChooser.ExtensionFilter("All files", "* *"),
                       new FileChooser.ExtensionFilter("Images", "*png","*jpg","*gif"),
                    new FileChooser.ExtensionFilter("Text File", "*txt")


                      );
    }    
//affichage dans le tableau
    
    private void setCellTable()
    {
    columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
    columnNAME.setCellValueFactory(new PropertyValueFactory<>("nom"));
   columnDESC.setCellValueFactory(new PropertyValueFactory<>("description"));
    columnPRICE.setCellValueFactory(new PropertyValueFactory<>("prix"));
    columnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
   columnTYPE.setCellValueFactory(new PropertyValueFactory<>("type"));
    columnTaille.setCellValueFactory(new PropertyValueFactory<>("taille"));
     columnVIEWS.setCellValueFactory(new PropertyValueFactory<>("views"));
   columnACHAT.setCellValueFactory(new PropertyValueFactory<>("nbrAchat"));
   columnMARQUE.setCellValueFactory(new PropertyValueFactory<>("marque"));

    }
    
  //load les données du database 
    private void loadDataFromDatabase()
    {txt_id.setEditable(false);

        data.clear();
        try {
             pst=con.prepareStatement("SELECT * FROM produit");
             rs = pst.executeQuery();
             while(rs.next())
            {
                     data.add(new product(rs.getInt("id"),
                     rs.getString("nom"),
                     rs.getString("description"),
                     Double.parseDouble(rs.getString("prix")),
                     rs.getInt("quantite"),
                     rs.getString("image")
                     ,rs.getString("type"),
                     rs.getString("taille"),
                     rs.getInt("views"),
                     rs.getInt("nbr_achat"), 
                     rs.getInt("categorie_id"),
                     rs.getString("marque"),
                     rs.getString("sexe")));
             
            }

          } catch (SQLException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableproduct.setItems(data);
    }
    
    
    
    
    //load les données du tableau vers les textfield
    
private void setCellValueFromTableToTextFied()
{tableproduct.setOnMouseClicked(e->{

        product p1=tableproduct.getItems().get(tableproduct.getSelectionModel().getSelectedIndex());
        txt_nom.setText(p1.getNom());
        txt_prix.setText(Double.toString(p1.getPrix()));
        txt_quantite.setText(Integer.toString(p1.getQuantite()));
        txt_taille.setText(p1.getTaille());
        txt_type.setText(p1.getType());
        txt_desc.setText(p1.getDescription());
        txt_achats.setText(Integer.toString(p1.getNbrAchat()));
        txt_views.setText(Integer.toString(p1.getViews()));
        txt_marque.setText(p1.getMarque());
        pid=p1.getId();
        showProductImage(p1.getNom());
    
    
});
}

//affichage de l'image dans l'imageviwer

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
// Check Input Fields
    public boolean checkInputs()
    {
        if(
              txt_nom.getText() == null
           || txt_desc.getText() == null
          || txt_prix.getText() == null

           || txt_quantite.getText() == null  
               || txt_type.getText() == null  
                || txt_taille.getText() == null   
                || txt_views.getText() == null
                                || txt_achats.getText() == null
                || txt_marque.getText() == null


                


          ){
            return false;
        }
        else{
            try{
                Double.parseDouble(txt_prix.getText());
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }
    }

//fonctier ajout
    @FXML
    private void btnInsert(ActionEvent event) throws SQLException, FileNotFoundException {
        if(checkInputs() && image != null)
        {
        String sql="INSERT INTO produit(nom,description,prix,quantite,image,type,taille,views,nbr_achat,marque) "
                + "values(?,?,?,?,?,?,?,?,?,?) ";
        String name=txt_nom.getText(); 
        String image=file.getName();

        
        
        String description=txt_desc.getText(); 
        Double prix=Double.valueOf(txt_prix.getText());
        Integer quantite=Integer.valueOf(txt_quantite.getText());
        String type=txt_type.getText();
        String taille=txt_taille.getText();
        Integer views=Integer.valueOf(txt_views.getText());
        Integer nbr_achat=Integer.valueOf(txt_achats.getText());
          String marque=txt_marque.getText();



        try {
            pst=con.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2, description);
            pst.setDouble(3,prix);
            pst.setInt(4,quantite);
            pst.setString(5, image);
            pst.setString(6, type);
           pst.setString(7, taille);
           pst.setInt(8,views);
          pst.setInt(9,nbr_achat);
                    pst.setString(10,marque);

           int i=pst.executeUpdate();
               
            if(i==1)
            
            {  System.out.println("inserted");
             setCellTable();
              loadDataFromDatabase();}
            



            

        } catch (SQLException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   finally {pst.close();}
         }else{
            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
        }
        }
        

    
    
//filechooser taa l'image
    @FXML
    private void handleBrowser(ActionEvent event) throws SQLException {
        stage=(Stage) anchorPane.getScene().getWindow();
        file=fileChooser.showOpenDialog(stage) ;
        
      /*  try {
            deskTop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      if(file!=null)
      {
          System.out.println(""+file.getAbsolutePath());
          image=new Image(file.getAbsoluteFile().toURI().toString(),imageView.getFitWidth(),imageView.getFitHeight(),true,true);
          imageView.setImage(image);
          saveToFileImageNormal(image);
          imageView.setPreserveRatio(true);
          
          
              }
    }

    //fonction update 
    @FXML
    private void btnupdate(ActionEvent event) {
                product pr = tableproduct.getSelectionModel().getSelectedItem();
                String sql="UPDATE produit SET nom = '"+txt_nom.getText()+
        "',description  = '"+txt_desc.getText()+
        "',prix = '"+Double.valueOf(txt_prix.getText())+
        "', quantite = '"+Integer.valueOf(txt_quantite.getText())+
        "',type  = '"+txt_type.getText()+
        "',taille  = '" + txt_taille.getText()+
        "', views = '"+Integer.valueOf(txt_views.getText())+
        "', nbr_achat = '"+Integer.valueOf(txt_achats.getText())+    
        "',marque  = '"+txt_marque.getText()+
        "' WHERE id = '"+pr.getId()+"'"; 
             try {
              pst=con.prepareStatement(sql);
              int i= pst.executeUpdate();
                if(i==1){     
               JOptionPane.showMessageDialog(null, "Product Updated");
                 loadDataFromDatabase();
}
                } catch (SQLException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    
  //donction delete  
    @FXML
    private void btndelete(ActionEvent event) {
        product pr = tableproduct.getSelectionModel().getSelectedItem();
         if(pr.getId()!=0)
        {
          
    String sql="DELETE FROM produit WHERE id = '"+pr.getId()+"'";
        try {
            pst=con.prepareStatement(sql);
           // pst.setString(1, pr.getId());
            int i= pst.executeUpdate();
            if(i==1)
            {
             JOptionPane.showMessageDialog(null, "Product Deleted");
            loadDataFromDatabase();

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
} else{
            JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id To Delete");
        }
}
    //recherche avancé
    
    private void searchProduct()
    { txt_search.setOnKeyReleased(e->{
        if(txt_search.getText().equals("")){loadDataFromDatabase();}
    else{
   data.clear();
        String sql="SELECT * FROM produit WHERE id LIKE '%"+Integer.parseInt(txt_search.getText())+"%'"
                +"UNION SELECT * FROM produit WHERE prix LIKE '%"+Double.valueOf(txt_search.getText())+"%'"
                
                ;
        try {
            pst=con.prepareStatement(sql);
           // pst.setString(1, txt_search.getText());
            rs=pst.executeQuery();
            while(rs.next())
            {System.out.println(""+rs.getString(2));
           data.add(new product(rs.getInt("id"),
         rs.getString("nom"), 
         Double.parseDouble(rs.getString("prix"))));
             
            }
            tableproduct.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    });
    }

    //statistique
    @FXML
    private void btnstat(ActionEvent event) {
        
        try {
            Stage Stage =(Stage) btn_stat.getScene().getWindow();
            Stage.close();
            
            
            Parent root = FXMLLoader.load(getClass().getResource("FXMLstat.fxml"));
            
            Scene scene = new Scene(root);
            
            Stage.setScene(scene);
            Stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    
public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\gestionProduit\\src\\gestionproduit\\images");
        String name;
        name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    } 

    @FXML
    private void logout(ActionEvent event) {
         try {
            Stage Stage =(Stage) btn_stat.getScene().getWindow();
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
    private void btnpdf(ActionEvent event) throws Exception {
fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
fileChooser.setInitialFileName("tt.pdf");
File file=fileChooser.showSaveDialog(stage);
if(file!=null)
{
String atr=file.getAbsolutePath();
    try {
        FileOutputStream fos=new FileOutputStream(atr);
        PDF pdf=new PDF(fos);
        Page page=new Page(pdf,Letter.LANDSCAPE);
        pdf.close();
        fos.flush();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(FXMLbackController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    }
}