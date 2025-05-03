/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingTicket extends Vehicle{
    private int ticketId;
    private LocalDateTime entryTime;
    private LocalDateTime timeOut;
    private String located;

    public int getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
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
}
