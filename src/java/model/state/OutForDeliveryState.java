/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.state;

/**
 *
 * @author MAS
 */
public class OutForDeliveryState implements OrderState {
   @Override
    public void processOrder(OrderContext context) {
        System.out.println("Order is now Delivered.");
    }

    @Override
    public String getStatus() {
        return "OutForDelivery";
    }
 
}

