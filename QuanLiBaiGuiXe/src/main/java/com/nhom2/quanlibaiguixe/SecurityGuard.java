/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom2.quanlibaiguixe;

/**
 *
 * @author Moderator
 */
public class SecurityGuard extends Person{
    private String ID, Password;
    public SecurityGuard(String FullName, String Address, String PhoneNumber,
                         String Identifier, String ID, String Password) {
        super(FullName, Address, PhoneNumber, Identifier);
        this.ID = ID;
        this.Password = Password;
    }
}