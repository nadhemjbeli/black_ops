/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Super;
import black_ops.Entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import black_ops.config.MaConnexion;

/**
 *
 * @author Khalil
 */
public class Contact_Controller {
    Connection mc;
    PreparedStatement ste;

    public Contact_Controller() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
  
    public void ajoutercontact(Contact A){
        String sql ="insert into contact(np_contact,mail_contact,message,date) Values(?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, A.getNp_contact());
            ste.setString(2, A.getMail_contact());
            ste.setString(3, A.getMessage());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void Updatecontact(Contact A){
        String sql ="update contact set np_contact = ? , mail_contact = ? ,message =?,date =? where id_contact = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, A.getNp_contact());
            ste.setString(2,A.getMail_contact() );
            ste.setString(3, A.getMessage());
            ste.setInt(5, A.getId_contact());
            ste.executeUpdate();
            System.out.println("Personne modifier");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public List<Contact> affichercontact(){
        List<Contact> contact = new ArrayList<>();
        String sql="select * from contact";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Contact A = new Contact();
                A.setId_contact(rs.getInt("Id_contact"));
                A.setNp_contact(rs.getString("Np_contact"));
                A.setMail_contact(rs.getString("Mail_contact"));
                A.setMessage(rs.getString("Message"));
                A.setDate(rs.getTimestamp("Date"));
                contact.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return contact;
    }    
     public void Deletecontact(Contact A ){
        
        String sql ="delete from contact where Id_contact = ?";
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, A.getId_contact() );
        ste.executeUpdate();
        System.out.println("Personne supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
