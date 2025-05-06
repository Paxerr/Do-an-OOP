/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import DataBase.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
public class MonthlyParking extends Vehicle{
    private int CardID;
    private String ExpireDate;
    public int getCardID() {
        return CardID;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public String getLicenseNumber() {
        return LicenseNumber;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public int getCost() {
        return Cost;
    }

    public void setCardID(int CardID) {
        this.CardID = CardID;
    }

    public void setExpireDate(String ExpireDate) {
        this.ExpireDate = ExpireDate;
    }

    public void setLicenseNumber(String LicenseNumber) {
        this.LicenseNumber = LicenseNumber;
    }

    public void setVehicleType(String VehicleType) {
        this.VehicleType = VehicleType;
    }

    public void setCost() {
        if("Xe máy".equals(this.VehicleType)) this.Cost = 120000;
        if("Ô tô".equals(this.VehicleType)) this.Cost = 240000;
        if("Xe đạp".equals(this.VehicleType)) this.Cost = 48000;
    }
    public void Register(){
        ResultSet KetQuaTruyVan = null;
        Connection tmp = null;
        PreparedStatement state = null;
        try {
            tmp = JDBCUtil.getConnection();
            String LayID = "SELECT * From monthlyparking ORDER BY CardID DESC";
            String ThemXeThang = "INSERT INTO monthlyparking (LicenseNumber, Cost, VehicleType, ExpireDate, CardID) VALUES (?, ?, ?, ?, ?)";
            
            if(this.CardID == -1){
                state = tmp.prepareStatement(LayID);
                KetQuaTruyVan = state.executeQuery();
                if (KetQuaTruyVan.next()) {
                    this.CardID = KetQuaTruyVan.getInt("CardID") + 1;
                } else {
                    this.CardID = 0;
                }
            }
            state = tmp.prepareStatement(ThemXeThang);
            state.setString(1, this.LicenseNumber);
            state.setString(2, Integer.toString(this.Cost));
            state.setString(3, this.VehicleType);
            state.setString(4, this.ExpireDate);
            state.setString(5, Integer.toString(this.CardID));
            state.executeUpdate();
            
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
        }
        
    }
}
