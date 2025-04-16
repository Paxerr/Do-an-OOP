package com.mycompany.parkingmanagementsystem;

public class MonthlyTicket {
    private String id;
    private String vehiclePlate;
    private String vehicleType;
    private String startDate;
    private String endDate;
    private String fee;

    public MonthlyTicket(String id, String vehiclePlate, String vehicleType, String startDate, String endDate, String fee) {
        this.id = id;
        this.vehiclePlate = vehiclePlate;
        this.vehicleType = vehicleType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fee = fee;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public String getFee() { return fee; }
    public void setFee(String fee) { this.fee = fee; }
}