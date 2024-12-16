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
    private double discount;   

    public LoyaltyProgramPayment(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public void pay(double amount) {
       
        discount = calculateDiscount(amount); 
        double finalAmount = amount - discount;

        System.out.println("Paid " + finalAmount + " using Loyalty Program. Discount applied: " + discount);
        System.out.println("Points Earned: " + accumulatePoints(amount));
    }

    @Override
    public double getDiscount() {
        return discount; 
    }

    private double calculateDiscount(double amount) {
       
        double discountRate;
        if (loyaltyPoints >= 500) {
            discountRate = 0.10; 
        } else if (loyaltyPoints >= 100) {
            discountRate = 0.05;
        } else {
            discountRate = 0.01; 
        }

        discount = amount * discountRate;
        double maxDiscount = amount * 0.5; 
        if (discount > maxDiscount) {
            discount = maxDiscount;
        }

        return discount; 
    }

    private int accumulatePoints(double amount) {
       
        loyaltyPoints += (int) (amount / 100);  
        return loyaltyPoints;  
    }
}



