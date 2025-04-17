package com.nhom2.quanlibaiguixe;
public class Staff {
    private String id;
    private String name;
    private String role;
    private String birthDate;
    private String gender;
    private String address;
    private String phone;

    public Staff(String id, String name, String role, String birthDate, String gender, String address, String phone) {
        this.id = id;
        this.name = name.trim();
        this.role = role;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address.trim();
        this.phone = phone.trim();
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name.trim(); }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address.trim(); }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone.trim(); }
}