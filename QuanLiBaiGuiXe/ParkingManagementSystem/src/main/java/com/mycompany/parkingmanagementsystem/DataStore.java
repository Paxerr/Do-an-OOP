/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static class User {
        String username;
        String password;
        String role;
        String fullName;
        String dob;
        String gender;
        String address;
        String phone;

        public User(String username, String password, String role, String fullName, String dob, String gender, String address, String phone) {
            this.username = username;
            this.password = password;
            this.role = role;
            this.fullName = fullName;
            this.dob = dob;
            this.gender = gender;
            this.address = address;
            this.phone = phone;
        }
    }

    public static class Vehicle {
        String id;
        String licensePlate;
        String type;
        String name;
        String color;
        String ticketType;
        String entryTime;

        public Vehicle(String id, String licensePlate, String type, String name, String color, String ticketType, String entryTime) {
            this.id = id;
            this.licensePlate = licensePlate;
            this.type = type;
            this.name = name;
            this.color = color;
            this.ticketType = ticketType;
            this.entryTime = entryTime;
        }
    }

    public static class RegularTicket {
        String ticketId;
        String licensePlate;
        String vehicleType;
        String fee;
        String entryTime;

        public RegularTicket(String ticketId, String licensePlate, String vehicleType, String fee, String entryTime) {
            this.ticketId = ticketId;
            this.licensePlate = licensePlate;
            this.vehicleType = vehicleType;
            this.fee = fee;
            this.entryTime = entryTime;
        }
    }

    public static class MonthlyTicket {
        String ticketId;
        String licensePlate;
        String vehicleType;
        String startDate;
        String endDate;
        String fee;

        public MonthlyTicket(String ticketId, String licensePlate, String vehicleType, String startDate, String endDate, String fee) {
            this.ticketId = ticketId;
            this.licensePlate = licensePlate;
            this.vehicleType = vehicleType;
            this.startDate = startDate;
            this.endDate = endDate;
            this.fee = fee;
        }
    }

    private static List<User> users = new ArrayList<>();
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<RegularTicket> regularTickets = new ArrayList<>();
    private static List<MonthlyTicket> monthlyTickets = new ArrayList<>();

    static {
        // Khởi tạo dữ liệu mẫu
        users.add(new User("admin", "123456", "Quản lý", "Nguyên N.", "5/20/1986", "Nam", "Phú Thọ", "0971555654"));
        users.add(new User("admin2", "123456", "Nhân viên thường", "Bùi Thị XYZ", "5/7/1997", "Nữ", "Phú Thọ", "971555654"));
        users.add(new User("staff", "123456", "Nhân viên thường", "Nhân Viên A", "1/1/1990", "Nam", "Hà Nội", "0987654321"));

        vehicles.add(new Vehicle("VE0002", "19-H1 0000", "Ô tô", "", "", "Vé lượt", "9/16/2017 5:00:00"));
        vehicles.add(new Vehicle("VE0001", "19-H1 0001", "Xe máy", "", "", "Vé lượt", "9/22/2017 5:00:00"));

        regularTickets.add(new RegularTicket("T0001", "19-H1 0000", "Ô tô", "5000", "9/16/2017 5:00:00"));
        regularTickets.add(new RegularTicket("T0002", "19-H1 0001", "Xe máy", "5000", "9/22/2017 5:00:00"));

        monthlyTickets.add(new MonthlyTicket("MT0001", "19-H1 0000", "Ô tô", "9/1/2017", "9/30/2017", "100000"));
        monthlyTickets.add(new MonthlyTicket("MT0002", "19-H1 0001", "Xe máy", "9/1/2017", "9/30/2017", "100000"));
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Vehicle> getVehicles() {
        return vehicles;
    }

    public static List<RegularTicket> getRegularTickets() {
        return regularTickets;
    }

    public static List<MonthlyTicket> getMonthlyTickets() {
        return monthlyTickets;
    }
}