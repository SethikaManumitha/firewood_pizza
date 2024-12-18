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
    public void next(OrderContext ctx) {
        System.out.println("Order has been delivered (final state).");
    }

    @Override
    public void previous(OrderContext ctx) {
        ctx.setState(new InPreparationState());
        System.out.println("Order has been returned to preparation state.");
    }

    @Override
    public String getState() {
        return "Out for Delivery";
    }
}

