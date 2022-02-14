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
public class Contact {
    private int id_contact ;
    private String np_contact;
    private String mail_contact;
    private String message;
    private Date date;

    public Contact(int id_contact, String np_contact, String mail_contact, String message, Date date) {
        this.id_contact = id_contact;
        this.np_contact = np_contact;
        this.mail_contact = mail_contact;
        this.message = message;
        this.date= date;
        
    }

    public Contact() {
        
    }

    @Override
    public String toString() {
        return "contact{" + "id_contact=" + id_contact + ", np_contact=" + np_contact + ", mail_contact=" + mail_contact + ", message=" + message + ", date=" + date + '}';
    }

    public void setId_contact(int id_contact) {
        this.id_contact = id_contact;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNp_contact(String np_contact) {
        this.np_contact = np_contact;
    }

    public void setMail_contact(String mail_contact) {
        this.mail_contact = mail_contact;
    }

    public void setMessage(String message) {
        this.message = message;
    }

  
    
    public int getId_contact() {
        return id_contact;
    }

    public String getNp_contact() {
        return np_contact;
    }

    public String getMail_contact() {
        return mail_contact;
    }

    public String getMessage() {
        return message;
    }

    
    
    
}
