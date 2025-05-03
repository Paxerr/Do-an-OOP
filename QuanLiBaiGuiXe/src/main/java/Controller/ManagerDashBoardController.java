/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DataBase.JDBCUtil;
import Model.*;
import View.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
/**
 *
 * @author Moderator
 */
public class ManagerDashBoardController {
    public static String ThemXe(String LicenseNumber,String VehicleType,String EntryTime){
        String TicketType;
        ResultSet KetQuaTruyVan = null;
        Connection tmp = null;
        PreparedStatement state = null;
        String ticketType;
        try {
            tmp = JDBCUtil.getConnection();
            String ThemVeXe = "\"INSERT INTO parkingticket (LicenseNumber, VehicleType, TicketType, EntryTime) VALUES (?, ?, ?, ?)\"";
            String TimLoaiVe = "SELECT * From monthlycard Where LicenseNumber = ?";
            state = tmp.prepareStatement(TimLoaiVe);
            state.setString(1, LicenseNumber);
            KetQuaTruyVan = state.executeQuery();
            if (KetQuaTruyVan.next()) {
                TicketType = "Vé Tháng";
            } else {

                TicketType = "Vé Thường";
            }
            state = tmp.prepareStatement(ThemVeXe);
            state.setString(1, LicenseNumber);
            state.setString(2, VehicleType);
            state.setString(3, TicketType);
            state.setString(4, EntryTime);
            int rowsAffected = state.executeUpdate();
            if(!(rowsAffected > 0)) return "error";
            if(KetQuaTruyVan != null) KetQuaTruyVan.close();
            if(state != null) state.close();
            if(tmp != null) tmp.close();
            return TicketType;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    };
}