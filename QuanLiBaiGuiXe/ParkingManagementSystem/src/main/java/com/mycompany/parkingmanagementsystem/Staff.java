package com.mycompany.parkingmanagementsystem;

public class Staff {
    private String id;
    private String name;
    private String role;
    private String dob;
    private String gender;
    private String address;
    private String phone;
    private String password;

    public Staff(String id, String name, String role, String dob, String gender, String address, String phone, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}