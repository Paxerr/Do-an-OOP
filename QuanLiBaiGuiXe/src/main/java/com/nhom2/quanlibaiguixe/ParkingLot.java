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
    public void FreeLocate(String LicenseNumber){
        for(Vehicle tmp : this.ParkedVehicle){
            if(tmp.getLicenseNumber() == LicenseNumber) {
                this.ParkedVehicle.remove(tmp);
                this.AvailableSpace++;
            }
        }
    }
    public void TakeLocate(Vehicle vehicle){
        this.ParkedVehicle.add(vehicle);
        this.AvailableSpace--;
    }

    public void setLotID(String LotID) {
        this.LotID = LotID;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public void setAvailableSpace(int AvailableSpace) {
        this.AvailableSpace = AvailableSpace;
    }

    public String getLotID() {
        return LotID;
    }

    public int getCapacity() {
        return Capacity;
    }

    public List<Vehicle> getParkedVehicle() {
        return ParkedVehicle;
    }

    public int getAvailableSpace() {
        return AvailableSpace;
    }
    
}