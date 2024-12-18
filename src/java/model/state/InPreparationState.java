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
    public void next(OrderContext ctx) {
        ctx.setState(new OutForDeliveryState());
        System.out.println("Order is now out for delivery.");
    }

    @Override
    public void previous(OrderContext ctx) {
        ctx.setState(new PlacedState());
        System.out.println("Order has been returned to the placed state.");
    }

    @Override
    public String getState() {
        return "InPreparation";
    }
}

