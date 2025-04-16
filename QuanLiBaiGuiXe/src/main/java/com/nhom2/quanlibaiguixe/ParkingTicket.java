/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom2.quanlibaiguixe;

import java.time.LocalDateTime;

public class ParkingTicket {
    private int ticketId;
    public LocalDateTime entryTime;
    public LocalDateTime timeOut;
    public Vehicle vehicle;
    private String located;

    public ParkingTicket(int ticketId, LocalDateTime entryTime, Vehicle vehicle, String located) {
        this.ticketId = ticketId;
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.located = located;
        this.timeOut = null; // Ban đầu, thời gian ra chưa được xác định
    }

    public int getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getLocated() {
        return located;
    }

    public void setLocated(String located) {
        this.located = located;
    }

    public int charge() {
        if (entryTime == null || timeOut == null) {
            System.out.println("Lỗi: Chưa có thời gian vào hoặc thời gian ra.");
            return 0;
        }

        long durationInMinutes = java.time.Duration.between(entryTime, timeOut).toMinutes();
        // Đây là một ví dụ đơn giản về cách tính phí, bạn có thể điều chỉnh theo logic của mình
        if (durationInMinutes <= 60) {
            return 10000; // Ví dụ: 10,000 VNĐ cho giờ đầu tiên
        } else {
            return 10000 + (int) Math.ceil((durationInMinutes - 60) / 30.0) * 5000; // Ví dụ: thêm 5,000 VNĐ cho mỗi 30 phút tiếp theo
        }
    }

    public void display() {
        System.out.println("--- Vé giữ xe ---");
        System.out.println("Mã vé: " + ticketId);
        System.out.println("Thời gian vào: " + entryTime);
        if (timeOut != null) {
            System.out.println("Thời gian ra: " + timeOut);
            System.out.println("Tổng phí: " + charge() + " VNĐ");
        } else {
            System.out.println("Trạng thái: Xe đang trong bãi");
        }
        System.out.println("Vị trí đỗ: " + located);
        if (vehicle != null) {
            System.out.println("Thông tin xe:");
            vehicle.display(); // Giả sử class Vehicle có phương thức display()
        }
        System.out.println("------------------");
    }
}
