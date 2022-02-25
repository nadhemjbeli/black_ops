/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;

import java.sql.Date;

/**
 *
 * @author Khalil
 */
public class Client {

    public Client() {
       
    }

    @Override
    public String toString() {
        return "client{" + "id_Cl=" + id_Cl + ", Pseaudo_Cl=" + Pseaudo_Cl + ", Photo_Cl=" + Photo_Cl + ", mail_Cl=" + mail_Cl + ", pass_Cl=" + pass_Cl + ", DateNaissance_Cl=" + DateNaissance_Cl + ", Statut_Cl=" + Statut_Cl + ", NewPass_Cl=" + NewPass_Cl + '}';
    }
    private int id_Cl;

    public void setId_Cl(int id_Cl) {
        this.id_Cl = id_Cl;
    }

    public void setPseaudo_Cl(String Pseaudo_Cl) {
        this.Pseaudo_Cl = Pseaudo_Cl;
    }

    public void setPhoto_Cl(String Photo_Cl) {
        this.Photo_Cl = Photo_Cl;
    }

    public void setMail_Cl(String mail_Cl) {
        this.mail_Cl = mail_Cl;
    }

    public void setPass_Cl(String pass_Cl) {
        this.pass_Cl = pass_Cl;
    }

    public void setDateNaissance_Cl(Date DateNaissance_Cl) {
        this.DateNaissance_Cl = DateNaissance_Cl;
    }

    public void setStatut_Cl(String Statut_Cl) {
        this.Statut_Cl = Statut_Cl;
    }

    public void setNewPass_Cl(int NewPass_Cl) {
        this.NewPass_Cl = NewPass_Cl;
    }

    public int getId_Cl() {
        return id_Cl;
    }

    public String getPseaudo_Cl() {
        return Pseaudo_Cl;
    }

    public String getPhoto_Cl() {
        return Photo_Cl;
    }

    public String getMail_Cl() {
        return mail_Cl;
    }

    public String getPass_Cl() {
        return pass_Cl;
    }

    public Date getDateNaissance_Cl() {
        return DateNaissance_Cl;
    }

    public String getStatut_Cl() {
        return Statut_Cl;
    }

    public int getNewPass_Cl() {
        return NewPass_Cl;
    }

    public Client(int id_Cl, String Pseaudo_Cl, String Photo_Cl, String mail_Cl, String pass_Cl, Date DateNaissance_Cl, String Statut_Cl, int NewPass_Cl) {
        this.id_Cl = id_Cl;
        this.Pseaudo_Cl = Pseaudo_Cl;
        this.Photo_Cl = Photo_Cl;
        this.mail_Cl = mail_Cl;
        this.pass_Cl = pass_Cl;
        this.DateNaissance_Cl = DateNaissance_Cl;
        this.Statut_Cl = Statut_Cl;
        this.NewPass_Cl = NewPass_Cl;
    }
    private String Pseaudo_Cl;
    private String Photo_Cl;
    private String mail_Cl;
    private String pass_Cl;
    private Date DateNaissance_Cl;
    private String Statut_Cl;
    private int NewPass_Cl;
}
