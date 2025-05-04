/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DataBase.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

public class ParkingTicket extends Vehicle {

    private int TicketID;
    private String TicketType;
    private String EntryTime;
    private String TimeOut;

    public void setTicketType(String TicketType) {
        this.TicketType = TicketType;
    }

    public String getTicketType() {
        return TicketType;
    }

    public int getTicketID() {
        return TicketID;
    }

    public String getEntryTime() {
        return EntryTime;
    }

    public String getTimeOut() {
        return TimeOut;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public int getCost() {
        return Cost;
    }

    public void setTicketID(int TicketID) {
        this.TicketID = TicketID;
    }

    public void setEntryTime(String entryTime) {
        this.EntryTime = entryTime;
    }

    public void setTimeOut(String timeOut) {
        this.TimeOut = timeOut;
    }

    public void setVehicleType(String VehicleType) {
        this.VehicleType = VehicleType;
    }

    public void setCost(int Cost) {
        this.Cost = Cost;
    }

//    public int Charge() {
//        if (EntryTime == null || TimeOut == null) {
//            System.out.println("Lỗi: Chưa có thời gian vào hoặc thời gian ra.");
//            return 0;
//        }
//
//        long durationInMinutes = java.time.Duration.between(EntryTime, TimeOut).toMinutes();
//
//        if (durationInMinutes <= 60) {
//            return 10000;
//        } else {
//            return 10000 + (int) Math.ceil((durationInMinutes - 60) / 30.0) * 5000;
//        }
//    }

    public void ParkTheVehicle() {
        ResultSet KetQuaTruyVan = null;
        Connection tmp = null;
        PreparedStatement state = null;
        try {
            tmp = JDBCUtil.getConnection();
            String LayID = "SELECT * From ParkingTicket ORDER BY TicketID DESC";
            String ThemVeXe = "INSERT INTO parkingticket (TicketID, LicenseNumber, VehicleType, TicketType, EntryTime, Cost) VALUES (?, ?, ?, ?, ?, ?)";
            String TimLoaiVe = "SELECT * From monthlyparking Where LicenseNumber = ?";
            state = tmp.prepareStatement(TimLoaiVe);
            state.setString(1, this.LicenseNumber);
            KetQuaTruyVan = state.executeQuery();
            if (KetQuaTruyVan.next()) {
                TicketType = "Vé Tháng";
            } else {
                TicketType = "Vé Thường";
            }
            this.setCost();
            
            state = tmp.prepareStatement(LayID);
            KetQuaTruyVan = state.executeQuery();
            if(KetQuaTruyVan.next())
                this.TicketID = KetQuaTruyVan.getInt("TicketID") + 1;
            else
                this.TicketID = 0;
            
            state = tmp.prepareStatement(ThemVeXe);
            state.setString(1, Integer.toString(this.TicketID));
            state.setString(2, LicenseNumber);
            state.setString(3, VehicleType);
            state.setString(4, TicketType);
            state.setString(5, EntryTime);
            state.setString(6, Integer.toString(Cost));
            int rowsAffected = state.executeUpdate();
            if (KetQuaTruyVan != null) {
                KetQuaTruyVan.close();
            }
            if (state != null) {
                state.close();
            }
            if (tmp != null) {
                tmp.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.TicketType = "error";
        }
    }
}
