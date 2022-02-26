// nadhem jbeli
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Messagee;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Message_Controller{
    private Connection mc;
    private PreparedStatement ste, preparedStatement;
    private String querry;
    
    
    @FXML
    private TableColumn<Messagee, String> contenu;

    @FXML
    private TableColumn<Messagee, String> date;

    @FXML
    private TableColumn<Messagee, String> id;

    @FXML
    private TableColumn<Messagee, String> id_cl;

    @FXML
    private TableColumn<Messagee, String> id_sous_cat;
    
    @FXML
    private TableView<Messagee> message_table;

    public Message_Controller() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    
    
    public void ajouterMessage(Messagee message){
        String sql ="INSERT INTO `message`(`Contenu_message`, `id_cl`, `id_souscat`) VALUES (?, ?, ?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, message.getContenu_message());
            ste.setInt(2, message.getId_cl());
            ste.setInt(3, message.getId_sous_cat());
            ste.executeUpdate();
            System.out.println("Message Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void UpdateMessage(Messagee message){
        String sql ="UPDATE message SET contenu_message = ?, id_cl = ?, id_souscat = ? where id_message = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, message.getContenu_message());
            ste.setInt(2, message.getId_cl());
            ste.setInt(3, message.getId_sous_cat());
            ste.setInt(4, message.getId_message());
            ste.executeUpdate();
            System.out.println("Message modifiee");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public ObservableList<Messagee> afficherMessages(){

        ObservableList<Messagee> messages;
        messages = FXCollections.observableArrayList();
        String sql="select * from message";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Messagee message = new Messagee();
                message.setId_message(rs.getInt("id_message"));
                message.setContenu_message(rs.getString("contenu_message"));
                message.setDate_message(rs.getTimestamp("date_message"));
                message.setId_cl(rs.getInt("id_cl"));
                message.setId_sous_cat(rs.getInt("id_souscat"));
                messages.add(message);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return messages;
    } 
    
    public void DeleteMessage(int m){
//        String sql1 ="";
        String sql2 ="DELETE FROM message WHERE id_message = ?";
        try{
        ste=mc.prepareStatement(sql2);
        ste.setInt(1, m);
        ste.executeUpdate();
        System.out.println("Personne supprimee");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
}


// nadhem jbeli
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//
///**
// * FXML Controller class
// *
// * @author ASUS
// */
//public class MessageController implements Initializable{
//    private Connection mc;
//    private PreparedStatement ste, preparedStatement;
//    private String querry;
//    
//    
//    @FXML
//    private TableColumn<Message, String> contenu;
//
//    @FXML
//    private TableColumn<Message, String> date;
//
//    @FXML
//    private TableColumn<Message, String> id;
//
//    @FXML
//    private TableColumn<Message, String> id_cl;
//
//    @FXML
//    private TableColumn<Message, String> id_sous_cat;
//    
//    @FXML
//    private TableView<Message> message_table;
//
//    public MessageController() {
//        mc=MaConnexion.getInstance().getCnx();
//    }
//    
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        loadDate();
//        
//    }
//    
//    
//    
//    public void ajouterMessage(Message message){
//        String sql ="INSERT INTO `message`(`Contenu_message`, `id_cl`, `id_souscat`) VALUES (?, ?, ?)";
//        try {
//            ste=mc.prepareStatement(sql);
//            ste.setString(1, message.getContenu_message());
//            ste.setInt(2, message.getId_cl());
//            ste.setInt(3, message.getId_sous_cat());
//            ste.executeUpdate();
//            System.out.println("Message Ajoutée");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    
//    public void UpdatePersonne(Message message){
//        String sql ="UPDATE message SET contenu_message = ? where id_message = ? ";
//        try {
//            ste=mc.prepareStatement(sql);
//            ste.setString(1, message.getContenu_message());
//            ste.setInt(2, message.getId_message());
//            ste.executeUpdate();
//            System.out.println("Message modifiee");
//            ste.close();
//      
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//    }
//    
//    public List<Message> afficherMessages(){
//        List<Message> messages = new ArrayList<>();
//        String sql="select * from message";
//        try {
//            ste=mc.prepareStatement(sql);
//            ResultSet rs=ste.executeQuery();
//            while(rs.next()){
//                Message message = new Message();
//                message.setId_message(rs.getInt("id_message"));
//                message.setContenu_message(rs.getString("contenu_message"));
//                message.setDate_message(rs.getTimestamp("date_message"));
//                message.setId_cl(rs.getInt("id_cl"));
//                message.setId_sous_cat(rs.getInt("id_souscat"));
//                messages.add(message);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        return messages;
//    } 
//    
//    @FXML
//    public void refreshTable(){
//        messageList.clear();
//        
//        querry = "SELECT * FROM message";
//        
//        try {
//            preparedStatement=mc.prepareStatement(querry);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            Message message;
//            
//            while(resultSet.next()){
//                message = new Message(resultSet.getInt("id_message"),
//                                                resultSet.getTimestamp("date_message"),
//                                                resultSet.getString("contenu_message"),
//                                                resultSet.getInt("id_cl"),
//                                                resultSet.getInt("id_souscat"));
////                message.setId_message(resultSet.getInt("id_message"));
////                message.setContenu_message(resultSet.getString("contenu_message"));
////                message.setDate_message(resultSet.getTimestamp("date_message"));
////                message.setId_cl(resultSet.getInt("id_cl"));
////                message.setId_sous_cat(resultSet.getInt("id_souscat"));
//                messageList.add(message);
//                message_table.setItems(messageList);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//    }
//    
//    public void DeleteMessage(int m){
////        String sql1 ="";
//        String sql2 ="DELETE FROM message WHERE id_message = ?";
//        try{
//        ste=mc.prepareStatement(sql2);
//        ste.setInt(1, m);
//        ste.executeUpdate();
//        System.out.println("Personne supprimee");
//        }catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    
//    ObservableList<Message>messageList = FXCollections.observableArrayList();
//    
//    private void loadDate() {
//        MaConnexion m = MaConnexion.getInstance();
//        refreshTable();
//        id.setCellFactory(new PropertyValueFactory<>("id_message"));
//        date.setCellFactory(new PropertyValueFactory<>("date_message"));
//        contenu.setCellFactory(new PropertyValueFactory<>("contenu_message"));
//        id_cl.setCellFactory(new PropertyValueFactory<>("id_cl"));
//        id_sous_cat.setCellFactory(new PropertyValueFactory<>("id_sous_cat"));
//        message_table.setItems(messageList);
//    }
//    
//    
//    
//}
