/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.state;

/**
 *
 * @author MAS
 */
public class InPreparationState implements OrderState {

    
     @Override
     public void processOrder(OrderContext context) {
        context.setState(new OutForDeliveryState());
        System.out.println("Order has been Delivered.");
    }

    @Override
    public String getStatus() {
        return "InPreparation";
    }
    
   
   
}

