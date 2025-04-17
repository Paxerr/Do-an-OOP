package com.mycompany.parkingmanagementsystem;

public class ParkingHistory {
    private String vehicleId;
    private String licensePlate;
    private String type;
    private String entryTime;
    private String exitTime;
    private String fee;
    private String ticketType;

    public ParkingHistory(String vehicleId, String licensePlate, String type, String entryTime, String exitTime, String fee, String ticketType) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate.trim();
        this.type = type;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.fee = fee;
        this.ticketType = ticketType;
    }

    // Getters
    public String getVehicleId() { return vehicleId; }
    public String getLicensePlate() { return licensePlate; }
    public String getType() { return type; }
    public String getEntryTime() { return entryTime; }
    public String getExitTime() { return exitTime; }
    public String getFee() { return fee; }
    public String getTicketType() { return ticketType; }
}