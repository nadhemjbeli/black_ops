
package black_ops.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maconnexion {
    public String url="jdbc:mysql://localhost:3306/black_ops";
    public String user="root";
    public String pwd="";
    public static Maconnexion cn;
    private Connection cnx;
    private Maconnexion(){
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static Maconnexion getInstance(){
        if(cn==null)
            cn= new Maconnexion();
            return cn;
      
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}