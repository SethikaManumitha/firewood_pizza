/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.command;

import model.Builder.Order;

/**
 *
 * @author MAS
 */
public class CancelOrder implements Command {

    private final Order order;
    
    public CancelOrder(Order order) {
        this.order = order;
    }
    
    @Override
    public void execute() {
        System.out.println("Cancelling order");
        order.setStatus("Cancelled");
    }

    @Override
    public void undo() {
        System.out.println("Order Placed " + order);
        order.setStatus("Placed");
    }
    
}
