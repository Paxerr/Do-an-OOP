/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom2.quanlibaiguixe;

public class Person {
    protected String FullName, Address, PhoneNumber, Identifier;
    Person(){};
    public String getFullName() {
        return FullName;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public void setIdentifier(String Identifier) {
        this.Identifier = Identifier;
    } 
}
