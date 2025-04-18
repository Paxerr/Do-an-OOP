package com.nhom2.quanlibaiguixe;
public class Staff extends Person{
    private String id;
    private String role;

    public Staff(String id, String FullName, String role, String BirthDate, String Gender, String Address, String PhoneNumber, String Identifier) {
        this.id = id;
        this.name = FullName.trim();
        this.role = role;
        this.birthDate = BirthDate;
        this.gender = Gender;
        this.Address = Address.trim();
        this.PhoneNumber = PhoneNumber.trim();
        this.Identifier = Identifier.trim();
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