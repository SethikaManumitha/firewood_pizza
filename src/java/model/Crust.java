/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MAS
 */
public class Crust {
    private int crustId;
    private String name;
    private double price;

    // Constructor
    public Crust(int crustId, String name, double price) {
        this.crustId = crustId;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public int getCrustId() {
        return crustId;
    }

    public void setCrustId(int crustId) {
        this.crustId = crustId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Crust [crustId=" + crustId + ", name=" + name + ", price=" + price + "]";
    }
}

