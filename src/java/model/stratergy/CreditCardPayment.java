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
    private String cardName;
    public CreditCardPayment(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("LKR." + amount + " has been charged from your "+ cardName +" Credit Card");
    }
    
    @Override
    public double getDiscount() {
        return 0.0; 
    }
}

