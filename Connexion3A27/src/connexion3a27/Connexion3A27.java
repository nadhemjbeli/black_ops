//
package connexion3a27;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion3A27 {
    static Connection cnx;
    static String url ="jdbc:mysql://localhost:3306/test";
    static String user="root";
    static String pwd="";
    static Statement ste;

    public static void main(String[] args) {
        try {
            try {
                cnx=DriverManager.getConnection(url, user, pwd);
                System.out.println("Connexion etablie");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
           // String sql="INSERT INTO `personne`(`id`, `nom`, `prenom`) VALUES (1,'Ben foulen','foulen')";
            ste=cnx.createStatement();
           // ste.executeUpdate(sql); // L'ajout d'une personne
           // System.out.println("Personne Ajoutée");
           String sql="select * from personne";
           ResultSet result=ste.executeQuery(sql);
           while(result.next()){
               int id =result.getInt("id");
               String nom=result.getString(2);
               String prenom= result.getString("prenom");
               System.out.println("ID : "+id+"\nNom : "+nom+"\nPrenom : "+prenom);
               
           }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
