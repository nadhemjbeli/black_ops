/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Controller;

import black_ops.Entity.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import black_ops.config.MaConnexion;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Khalil
 */
public class Client_Controller {
    Connection mc;
    PreparedStatement ste;

    public Client_Controller() {
        mc=MaConnexion.getInstance().getCnx();
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
     public  void sendMail(String receveursList,String object,String corps) {
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "25"); 
        
        String MonEmail = "khalilkhemiri33@gmail.com";
        String password = "Khalil951753";
        




        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()
            {
                return new javax.mail.PasswordAuthentication(MonEmail, password);
            }
        
        });
        
        javax.mail.Message message = prepareMessage(session,MonEmail,receveursList,object,corps);
        
        try {
            javax.mail.Transport.send(message);
        } catch (javax.mail.MessagingException ex) {
            Logger.getLogger(Client_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.err.println("Message envoyÃ© avec succÃ¨s");
    }
    
    private static javax.mail.Message prepareMessage(Session session,String email,String receveursList,String object,String corps)
    {
        javax.mail.Message message = new MimeMessage(session);
        
        try {
            message.setFrom(new InternetAddress(email));
            
            message.setSubject(object);
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receveursList));
            message.setText(corps);
            
            return message;
        } catch (javax.mail.MessagingException ex) {
            Logger.getLogger(Client_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public String getAlphaNumericString(int n) {

        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    
    public boolean verifierEmailBd(String email) { //Controle De Saisie si mail existe
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            String sql = "SELECT * FROM client WHERE mail_Cl=?";
            stmt = mc.prepareStatement(sql);
            stmt.setString(1, email);
            rst = stmt.executeQuery();
            if (rst.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public boolean testEmail(String mail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.find();
    }
    
    public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
    
        public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);

    public boolean testPassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
    
    public String crypterPassword(String password) {
        String hashValue = "";
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

        } catch (Exception e) {
        }

        return hashValue;
    }
    
    public void modifierPassword(String mail, String password) {
        PreparedStatement stmt;
        try {

            String sql = "UPDATE client SET pass_Cl=? WHERE mail_Cl=?";
            stmt = mc.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, mail);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Client_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

