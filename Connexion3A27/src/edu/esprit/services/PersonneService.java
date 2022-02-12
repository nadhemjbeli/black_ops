
package edu.esprit.services;

import edu.esprit.entities.Personne;
import edu.esprit.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonneService {
    Connection mc;
    PreparedStatement ste;

    public PersonneService() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
  
    public void ajouterPersonne(Personne p){
        String sql ="insert into personne(nom,prenom) Values(?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      public void UpdatePersonne(Personne p){
        String sql ="update personne set nom = ? , prenom = ? where id = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(3, p.getId());
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.executeUpdate();
            System.out.println("Personne modifier");
            ste.close();
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public List<Personne> afficherPersonne(){
        List<Personne> personnes = new ArrayList<>();
        String sql="select * from personne";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return personnes;
    }    
    public void DeletePersonne(Personne p ){
        
        String sql ="delete from personne where id = ?";
        try{
        ste=mc.prepareStatement(sql);
        ste.setInt(1, p.getId());
        ste.executeUpdate();
        System.out.println("Personne supprimer");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

