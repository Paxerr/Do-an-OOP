/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DataBase.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Moderator
 */
public class User extends Person{
    private String ID, Password, Role;
    public String getID() {
        return ID;
    }
    public User(String ID,String Password,String Role){
        this.ID = ID;
        this.Password = Password;
        this.Role = Role;
    };
    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getRole() {
        return Role;
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