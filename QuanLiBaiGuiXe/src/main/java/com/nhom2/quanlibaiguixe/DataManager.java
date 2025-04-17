package com.mycompany.parkingmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance;
    private List<Vehicle> vehicles;
    private List<Ticket> tickets;
    private List<MonthlyTicket> monthlyTickets;
    private List<Staff> staffList;
    private List<ParkingHistory> parkingHistory;

    private DataManager() {
        vehicles = new ArrayList<>();
        tickets = new ArrayList<>();
        monthlyTickets = new ArrayList<>();
        staffList = new ArrayList<>();
        parkingHistory = new ArrayList<>();
        // Thêm dữ liệu mẫu
        staffList.add(new Staff("S001", "Admin", "Quản lý", "1/1/1990", "Nam", "Hà Nội", "0123456789"));
        staffList.add(new Staff("S002", "Nhân viên 1", "Nhân viên", "1/1/1995", "Nữ", "Hà Nội", "0987654321"));
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // Quản lý xe
    public List<Vehicle> getVehicles() { return vehicles; }
    public void addVehicle(Vehicle vehicle) { vehicles.add(vehicle); }
    public void removeVehicle(String vehicleId) {
        vehicles.removeIf(v -> v.getId().equals(vehicleId));
    }

    // Quản lý vé thường
    public List<Ticket> getTickets() { return tickets; }
    public void addTicket(Ticket ticket) { tickets.add(ticket); }
    public void removeTicket(String ticketId) {
        tickets.removeIf(t -> t.getId().equals(ticketId));
    }

    // Quản lý vé tháng
    public List<MonthlyTicket> getMonthlyTickets() { return monthlyTickets; }
    public void addMonthlyTicket(MonthlyTicket monthlyTicket) { monthlyTickets.add(monthlyTicket); }
    public void removeMonthlyTicket(String monthlyTicketId) {
        monthlyTickets.removeIf(mt -> mt.getId().equals(monthlyTicketId));
    }

    // Quản lý nhân viên
    public List<Staff> getStaffList() { return staffList; }
    public void addStaff(Staff staff) { staffList.add(staff); }
    public void removeStaff(String staffId) {
        staffList.removeIf(s -> s.getId().equals(staffId));
    }

    // Quản lý lịch sử gửi xe
    public List<ParkingHistory> getParkingHistory() { return parkingHistory; }
    public void addParkingHistory(ParkingHistory history) { parkingHistory.add(history); }
}