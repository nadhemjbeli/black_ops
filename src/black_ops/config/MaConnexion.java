/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aZiz
 */
public class MaConnexion {
    
    public String url="jdbc:mysql://localhost:3306/Black ops";
    public String user="root";
    public String pwd="";
    public static MaConnexion cn;

    private Connection cnx;
    
    private MaConnexion(){
       try {
            cnx=DriverManager.getConnection(url, user, pwd);
            
             if (cnx != null) {
            System.out.println("Connexion etablie");
    }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
      public static MaConnexion getInstance(){
        if(cn==null)
            cn= new MaConnexion();
            return cn;
      
    }
        public Connection getCnx() {
        return cnx;
    }
    
    
}
