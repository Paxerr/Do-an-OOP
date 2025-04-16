package com.mycompany.parkingmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance;
    private List<Vehicle> vehicles;
    private List<Ticket> tickets;
    private List<MonthlyTicket> monthlyTickets;
    private List<Staff> staffList;

    private DataManager() {
        vehicles = new ArrayList<>();
        tickets = new ArrayList<>();
        monthlyTickets = new ArrayList<>();
        staffList = new ArrayList<>();

        // Dữ liệu mẫu
        vehicles.add(new Vehicle("VE0002", "19-H1 0000", "Ô tô", "", "", "Vé lượt", "9/16/2017 5:00 PM"));
        vehicles.add(new Vehicle("VE0001", "19-H1 0001", "Xe máy", "", "", "Vé lượt", "9/22/2017 5:00 PM"));

        tickets.add(new Ticket("T0001", "19-H1 0000", "Ô tô", "5000", "9/16/2017 5:00 PM"));
        tickets.add(new Ticket("T0002", "19-H1 0001", "Xe máy", "5000", "9/22/2017 5:00 PM"));

        monthlyTickets.add(new MonthlyTicket("MT0001", "19-H1 0000", "Ô tô", "9/1/2017", "9/30/2017", "100000"));
        monthlyTickets.add(new MonthlyTicket("MT0002", "19-H1 0001", "Xe máy", "9/1/2017", "9/30/2017", "100000"));

        staffList.add(new Staff("admin", "Nguyên N.", "Quản lý", "5/20/1986", "Nam", "Phú Thọ", "0971555654", "123456"));
        staffList.add(new Staff("admin2", "Bùi Thị XYZ", "Nhân viên thường", "5/7/1997", "Nữ", "Phú Thọ", "971555654", "123456"));
        staffList.add(new Staff("staff", "Nhân viên A", "Nhân viên thường", "1/1/1990", "Nam", "Hà Nội", "0987654321", "123456"));
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // Methods for vehicles
    public List<Vehicle> getVehicles() { return vehicles; }
    public void addVehicle(Vehicle vehicle) { vehicles.add(vehicle); }
    public void removeVehicle(String id) {
        vehicles.removeIf(v -> v.getId().equals(id));
    }

    // Methods for tickets
    public List<Ticket> getTickets() { return tickets; }
    public void addTicket(Ticket ticket) { tickets.add(ticket); }
    public void removeTicket(String id) {
        tickets.removeIf(t -> t.getId().equals(id));
    }

    // Methods for monthly tickets
    public List<MonthlyTicket> getMonthlyTickets() { return monthlyTickets; }
    public void addMonthlyTicket(MonthlyTicket monthlyTicket) { monthlyTickets.add(monthlyTicket); }
    public void removeMonthlyTicket(String id) {
        monthlyTickets.removeIf(mt -> mt.getId().equals(id));
    }

    // Methods for staff
    public List<Staff> getStaffList() { return staffList; }
    public void addStaff(Staff staff) { staffList.add(staff); }
    public void removeStaff(String id) {
        staffList.removeIf(s -> s.getId().equals(id));
    }
    public void updateStaff(Staff updatedStaff) {
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getId().equals(updatedStaff.getId())) {
                staffList.set(i, updatedStaff);
                break;
            }
        }
    }
    public Staff getStaffById(String id) {
        return staffList.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }
}