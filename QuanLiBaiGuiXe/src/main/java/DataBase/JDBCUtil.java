/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moderator
 */
public class JDBCUtil {
    public static Connection GetConnection() throws SQLException {
        Connection tmp = null;
        try {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String url = "jdbc:mysql://localhost:3306/quanliguixe";
        String username = "root";
        String password = "quyet1110";
        tmp = DriverManager.getConnection(url,username,password);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return tmp; 
    }
    
    public static void CloseConnection(Connection tmp){
        try {
            if(tmp != null) tmp.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
