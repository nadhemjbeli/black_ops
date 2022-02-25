/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.test.Gestion_utilisateur;
import black_ops.Entity.Super;
import black_ops.Controller.Admin_Controller;
import black_ops.config.Maconnexion;


public class test_admin {
    public static void main(String[] args) {
        Maconnexion m=Maconnexion.getInstance();
        Super A=new Super(2,"khalilkhemiri@gmail.om","987654",7,0);
        Admin_Controller AS=new Admin_Controller();
        //AS.ajouterAdmin(A);
        //AS.UpdateAdmin(A);
        //System.out.println(AS.afficherPersonne());
        AS.DeletePersonne(A);
    }
    
}
