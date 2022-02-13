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
 * @author medaz
 */
public class DataBase {
    public String url="jdbc:mysql://localhost:3306/Black_ops";
    public String user="root";
    public String pwd="";
    public static DataBase cn;
    private Connection cnx;
    private DataBase(){
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static DataBase getInstance(){
        if(cn==null)
            cn= new DataBase();
            return cn;
      
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
