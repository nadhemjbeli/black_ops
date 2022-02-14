/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Client;
import black_ops.Entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import black_ops.config.Maconnexion;

/**
 *
 * @author Khalil
 */
public class Client_Controller {
    Connection mc;
    PreparedStatement ste;

    public Client_Controller() {
        mc=Maconnexion.getInstance().getCnx();
    }
    
    
  
    public void ajouterclient(Client A){
        String sql ="insert into client(id_Cl,Pseaudo_Cl,Photo_Cl,mail_Cl,pass_Cl,DateNaissance_Cl,Statut_Cl,NewPass_Cl) Values(?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, A.getId_Cl());
            ste.setString(2, A.getPseaudo_Cl());
            ste.setString(3, A.getPhoto_Cl());
            ste.setString(4, A.getMail_Cl());
            ste.setString(5,A.getPass_Cl());
            ste.setDate(6,A.getDateNaissance_Cl());
            ste.setString(7,A.getStatut_Cl());
            ste.setInt(8,A.getNewPass_Cl());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     public void Updateclient(Client A){
        String sql ="update client set Pseaudo_Cl = ? , Photo_Cl = ? ,mail_Cl =?,pass_Cl =?,DateNaissance_Cl=?,Statut_Cl=?,NewPass_Cl=? where id_Cl = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, A.getPseaudo_Cl());
            ste.setString(2,A.getPhoto_Cl() );
            ste.setString(3, A.getMail_Cl());
            ste.setString(4, A.getPass_Cl());
            ste.setDate(5, A.getDateNaissance_Cl());
            ste.setString(6, A.getStatut_Cl());
            ste.setInt(7, A.getNewPass_Cl());
            ste.setInt(8, A.getId_Cl());
            ste.executeUpdate();
            System.out.println("Personne modifier");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public List<Client> afficherclient(){
        List<Client> client = new ArrayList<>();
        String sql="select * from client";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Client A = new Client();
                A.setId_Cl(rs.getInt("Id_Cl"));
                A.setPseaudo_Cl(rs.getString("Pseaudo_Cl"));
                A.setPhoto_Cl(rs.getString("Photo_Cl"));
                A.setMail_Cl(rs.getString("mail_Cl"));
                A.setPass_Cl(rs.getString("pass_Cl"));
                A.setDateNaissance_Cl(rs.getDate("DateNaissance_Cl"));
                A.setStatut_Cl(rs.getString("Statut_Cl"));
                A.setNewPass_Cl(rs.getInt("NewPass_Cl"));
                 
                client.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return client;
    }    
     public void Deletecontact(Client A ){
        
        String sql ="delete from client where id_Cl = ?";
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, A.getId_Cl() );
        ste.executeUpdate();
        System.out.println("Personne supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
