/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.*;
import View.*;
import DataBase.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

/**
 *
 * @author Moderator
 */
public class LoginScreenController {
       public static String Login(String ID, String Password) {
            ResultSet KetQuaTruyVan = null;
            Connection tmp = null;
            PreparedStatement state = null;
            try{
                tmp = JDBCUtil.getConnection();
                String sql = "SELECT * From User Where ID = ? AND Password = ?";
                state = tmp.prepareStatement(sql);
                state.setString(1,ID);
                state.setString(2,Password);
                KetQuaTruyVan = state.executeQuery();
                if(KetQuaTruyVan.next()){
                    String Role = KetQuaTruyVan.getString("Role");
                    if(KetQuaTruyVan != null) KetQuaTruyVan.close();
                    if(state != null) state.close();
                    if(tmp != null) tmp.close();
                    return Role;
                }
                else{
                    if(KetQuaTruyVan != null) KetQuaTruyVan.close();
                    if(state != null) state.close();
                    if(tmp != null) tmp.close();
                    return " ";
                }      
            } 
            catch(Exception e) {
                e.printStackTrace();
                return "error";
            } 
        }
}