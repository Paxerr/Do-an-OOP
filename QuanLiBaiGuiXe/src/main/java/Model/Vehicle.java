package Model;

import DataBase.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Vehicle {

    Vehicle() {
    }
    ;
     private String LicenseNumber;
    protected String VehicleType;
    protected int Cost;

    public int GetIn() {
        return 1;
    }

    public int GetOut() {
        return 1;
    }

    public void setCost(int Cost) {
        this.Cost = Cost;
    }

    public int getCost() {
        return Cost;
    }

    public String getLicenseNumber() {
        return LicenseNumber;
    }

    public void setLicenseNumber(String LicenseNumber) {
        this.LicenseNumber = LicenseNumber;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void ParkTheVehicle() {
        ResultSet KetQuaTruyVan = null;
        Connection tmp = null;
        PreparedStatement state = null;
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
            if (!(rowsAffected > 0)) {
                return "error";
            }
            if (KetQuaTruyVan != null) {
                KetQuaTruyVan.close();
            }
            if (state != null) {
                state.close();
            }
            if (tmp != null) {
                tmp.close();
            }
            return TicketType;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
}
