/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingTicket extends Vehicle {

    private int TicketId;
    private String TicketType;
    private LocalDateTime EntryTime;
    private LocalDateTime TimeOut;

    public void setTicketType(String TicketType) {
        this.TicketType = TicketType;
    }

    public String getTicketType() {
        return TicketType;
    }

    public int getTicketId() {
        return TicketId;
    }

    public LocalDateTime getEntryTime() {
        return EntryTime;
    }

    public LocalDateTime getTimeOut() {
        return TimeOut;
    }


    public String getVehicleType() {
        return VehicleType;
    }

    public int getCost() {
        return Cost;
    }

    public void setTicketId(int ticketId) {
        this.TicketId = ticketId;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.EntryTime = entryTime;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.TimeOut = timeOut;
    }

    public void setVehicleType(String VehicleType) {
        this.VehicleType = VehicleType;
    }

    public void setCost(int Cost) {
        this.Cost = Cost;
    }

    public int Charge() {
        if (EntryTime == null || TimeOut == null) {
            System.out.println("Lỗi: Chưa có thời gian vào hoặc thời gian ra.");
            return 0;
        }

        long durationInMinutes = java.time.Duration.between(EntryTime, TimeOut).toMinutes();

        if (durationInMinutes <= 60) {
            return 10000;
        } else {
            return 10000 + (int) Math.ceil((durationInMinutes - 60) / 30.0) * 5000;
        }
    }

}
