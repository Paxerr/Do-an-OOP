/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.*;
public class MonthlyCard extends Person{
    private String CardID;
    private List<Vehicle> Vehicles = new ArrayList<>();
    private Date ExpireDate;
 
    public void renewCard(int months) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(ExpireDate);
        calendar.add(java.util.Calendar.MONTH, months); // thêm số tháng
        ExpireDate = calendar.getTime();
    }
    public void setCardID(String CardID) {
        this.CardID = this.PhoneNumber;
    }

    public void setVehicles(List<Vehicle> Vehicles) {
        this.Vehicles = Vehicles;
    }

    public void setExpireDate(Date ExpireDate) {
        this.ExpireDate = ExpireDate;
    }

    public String getCardID() {
        return CardID;
    }

    public List<Vehicle> getVehicles() {
        return Vehicles;
    }

    public Date getExpireDate() {
        return ExpireDate;
    }
}
