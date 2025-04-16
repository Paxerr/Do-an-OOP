/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom2.quanlibaiguixe;
import java.util.*;
public class MonthlyCard{
    private int CardID;
    private List<Vehicle> Vehicles = new ArrayList<>();
    private Date ExpireDate;

    public MonthlyCard(Date ExpireDate) {
        this.CardID = CardID;
        this.ExpireDate = ExpireDate;
    }

    public void renewCard(int months) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(ExpireDate);
        calendar.add(java.util.Calendar.MONTH, months); // thêm số tháng
        ExpireDate = calendar.getTime();
    }
}
