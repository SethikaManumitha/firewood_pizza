/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MAS
 */
public class Topping {
    private int toppingId;
    private String name;
    private double price;
    private double discount;

    // Constructor
    public Topping(int toppingId, String name, double price,double discount) {
        this.toppingId = toppingId;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    // Getters and Setters
    public int getToppingId() {
        return toppingId;
    }

    public String getName() {
        return name;
    }
    
     public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    
    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    

    public void setPrice(double price) {
        this.price = price;
    }

    
}

