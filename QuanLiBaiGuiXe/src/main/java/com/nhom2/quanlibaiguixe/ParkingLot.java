/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom2.quanlibaiguixe;
import java.util.*;
/**
 *
 * @author Moderator
 */
public class ParkingLot {
    private String LotID;
    private int Capacity;
    private List<Vehicle> ParkedVehicle = new ArrayList<>();
    private int AvailableSpace;
    public void getAvailableSpace(){
        System.out.println(this.AvailableSpace);
    }
    public boolean CheckAvailable(){
        if(this.AvailableSpace == 0)
        return false;
        else return true;
    }
    public void FreeLocate(){
        
    }
}