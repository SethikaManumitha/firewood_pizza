/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.stratergy;

/**
 *
 * @author MAS
 */
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private double discount;
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("LKR." + amount + " has been charged from your Credit Card");
    }
    
    @Override
    public double getDiscount() {
        return discount; 
    }
}

