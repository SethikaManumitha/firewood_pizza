/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.stratergy;

/**
 *
 * @author MAS
 */
public class LoyaltyProgramPayment implements PaymentStrategy {
    private int loyaltyPoints;

    public LoyaltyProgramPayment(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public void pay(double amount) {
        double discount = calculateDiscount(amount);
        double finalAmount = amount - discount;
        System.out.println("Paid " + finalAmount + " using Loyalty Program. Discount applied: " + discount);
        accumulatePoints(finalAmount);
    }

    private double calculateDiscount(double amount) {
        return amount * 0.05;
    }

    private void accumulatePoints(double amount) {
        loyaltyPoints += (int) (amount / 10);
        System.out.println("Loyalty Points accumulated: " + loyaltyPoints);
    }
}

