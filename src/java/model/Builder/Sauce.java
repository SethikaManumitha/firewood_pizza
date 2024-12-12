/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Builder;

/**
 *
 * @author MAS
 */
public class Sauce {
    private int sauceId;
    private String name;
    private double price;

    // Constructor
    public Sauce(int sauceId, String name, double price) {
        this.sauceId = sauceId;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public int getSauceId() {
        return sauceId;
    }

    public void setSauceId(int sauceId) {
        this.sauceId = sauceId;
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
        return "Sauce [sauceId=" + sauceId + ", name=" + name + ", price=" + price + "]";
    }
}

