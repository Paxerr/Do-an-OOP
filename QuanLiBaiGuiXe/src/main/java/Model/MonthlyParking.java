/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.*;
public class MonthlyParking extends Vehicle{
    private String CardID;
    private Date ExpireDate;
 
    public void ExtendTime(int months) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(ExpireDate);
        calendar.add(java.util.Calendar.MONTH, months); // thêm số tháng
        ExpireDate = calendar.getTime();
    }
}
