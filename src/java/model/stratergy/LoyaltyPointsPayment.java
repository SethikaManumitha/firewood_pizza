/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.stratergy;

/**
 *
 * @author MAS
 */
public class LoyaltyPointsPayment implements PaymentStrategy {
    private int points;

    public LoyaltyPointsPayment(int points) {
        this.points = points;
    }

    @Override
    public void pay(double amount) {
        if (points >= amount) {
            System.out.println("Paid " + amount + " using Loyalty Points.");
        } else {
            System.out.println("Insufficient points. Payment failed.");
        }
    }
}

