/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom2.quanlibaiguixe;

/**
 *
 * @author Moderator
 */
public class Vehicle{
    Vehicle(){};
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
}
