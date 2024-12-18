/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.state;

/**
 *
 * @author MAS
 */
public class PlacedState implements OrderState{
    
     @Override
     public void processOrder(OrderContext context) {
        context.setState(new InPreparationState());
        System.out.println("Order is now Processing.");
    }

    @Override
    public String getStatus() {
        return "Placed";
    }
}
