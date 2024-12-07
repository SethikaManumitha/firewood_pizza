/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author MAS
 */
public class Customer {
     private int id;
     private String name;
     private String email;
     private String password;
     private String phone;
     private String address;
     private Date dob;
     private int points;
     
   
    //Constructor
    public Customer() {
        
    }
    
    public Customer(int id,String name, String email, String password,String phone,String address,Date dob,int points) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.points = points;
    }
    
     public Customer(String name, String email, String password,String phone,String address,Date dob,int points) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.points = points;
    }
    
   
    //Setters
    public void setId(int id) {
        this.id = id;
    }
     
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setPoints(int points) {
        this.points = points;
    }
     
    

    //Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getDob() {
        return dob;
    }

    public int getPoints() {
        return points;
    }
    
    
    
  
}
