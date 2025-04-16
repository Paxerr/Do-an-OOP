package com.mycompany.parkingmanagementsystem;

public class Vehicle {
    private String id;
    private String licensePlate;
    private String type;
    private String name;
    private String color;
    private String ticketType;
    private String entryTime;

    public Vehicle(String id, String licensePlate, String type, String name, String color, String ticketType, String entryTime) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.type = type;
        this.name = name;
        this.color = color;
        this.ticketType = ticketType;
        this.entryTime = entryTime;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getTicketType() { return ticketType; }
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }
    public String getEntryTime() { return entryTime; }
    public void setEntryTime(String entryTime) { this.entryTime = entryTime; }
}