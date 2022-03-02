/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_ops.Entity;

/**
 *
 * @author Khalil
 */
public class Super {
    private int id_admin;
    private String mail_admin;
    private String password_admin;
    private int grade;
    private int NewPass;
    
    
    public Super (){}

    public Super(int id_admin, String mail_admin, String password_admin, int grade, int NewPass) {
        this.id_admin = id_admin;
        this.mail_admin = mail_admin;
        this.password_admin = password_admin;
        this.grade = grade;
        this.NewPass = NewPass;
    }

    public Super(int id_admin, String mail_admin, String password_admin, int grade) {
        this.id_admin = id_admin;
        this.mail_admin = mail_admin;
        this.password_admin = password_admin;
        this.grade = grade;
    }

    public int getId_admin() {
        return id_admin;
    }

    public String getMail_admin() {
        return mail_admin;
    }

    public String getPassword_admin() {
        return password_admin;
    }

    public int getGrade() {
        return grade;
    }

    public int getNewPass() {
        return NewPass;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setMail_admin(String mail_admin) {
        this.mail_admin = mail_admin;
    }

    public void setPassword_admin(String password_admin) {
        this.password_admin = password_admin;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setNewPass(int NewPass) {
        this.NewPass = NewPass;
    }

    @Override
    public String toString() {
        return "Admin{" + "id_admin=" + id_admin + ", mail_admin=" + mail_admin + ", password_admin=" + password_admin + ", grade=" + grade + ", NewPass=" + NewPass + '}';
    }
    
}
