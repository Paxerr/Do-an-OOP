package com.mycompany.parkingmanagementsystem;

public class Ticket {
    private String id;
    private String vehiclePlate;
    private String vehicleType;
    private String fee;
    private String entryTime;

    public Ticket(String id, String vehiclePlate, String vehicleType, String fee, String entryTime) {
        this.id = id;
        this.vehiclePlate = vehiclePlate.trim();
        this.vehicleType = vehicleType;
        this.fee = fee;
        this.entryTime = entryTime;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getVehiclePlate() { return vehiclePlate; }
    public void setVehiclePlate(String vehiclePlate) { this.vehiclePlate = vehiclePlate.trim(); }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getFee() { return fee; }
    public void setFee(String fee) { this.fee = fee; }

    public String getEntryTime() { return entryTime; }
    public void setEntryTime(String entryTime) { this.entryTime = entryTime; }
}