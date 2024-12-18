/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.state;

/**
 *
 * @author MAS
 */
public class PlacedState implements OrderState {
    @Override
    public void next(OrderContext ctx) {
        ctx.setState(new InPreparationState());
        System.out.println("Order is now in preparation.");
    }

    @Override
    public void previous(OrderContext ctx) {
        System.out.println("Order is already in its initial state.");
    }

    @Override
    public String getState() {
        return "Placed";
    }
}

