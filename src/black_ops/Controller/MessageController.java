// nadhem jbeli
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Message;
import black_ops.config.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class MessageController {
    private Connection mc;
    private PreparedStatement ste;

    public MessageController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    
    public void ajouterMessage(Message message){
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
    
    public void UpdatePersonne(Message message){
        String sql ="UPDATE message SET contenu_message = ? where id_message = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, message.getContenu_message());
            ste.setInt(2, message.getId_message());
            ste.executeUpdate();
            System.out.println("Message modifiee");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public List<Message> afficherMessages(){
        List<Message> messages = new ArrayList<>();
        String sql="select * from message";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Message message = new Message();
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
    
    public void DeleteMessage(Message message ){
//        String sql1 ="";
        String sql2 ="DELETE FROM message WHERE id_message = ?";
        try{
        ste=mc.prepareStatement(sql2);
        ste.setInt(1, message.getId_message());
        ste.executeUpdate();
        System.out.println("Personne supprimee");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
