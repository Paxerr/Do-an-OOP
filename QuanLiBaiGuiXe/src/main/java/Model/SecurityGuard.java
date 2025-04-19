/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Moderator
 */
public class SecurityGuard extends Person{
    private String ID, Password;
    public String getID() {
        return ID;
    }
    public String getPassword() {
        return Password;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
}