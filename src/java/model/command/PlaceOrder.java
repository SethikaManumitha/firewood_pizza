/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.command;

import model.Builder.*;
/**
 *
 * @author MAS
 */
public class PlaceOrder implements Command {

    private final Order order;
    
    public PlaceOrder(Order order) {
        this.order = order;
    }
    
    @Override
    public void execute() {
        order.setStatus("Placed");
        System.out.println("Order placed: " + order);
    }

    @Override
    public void undo() {
        order.setStatus("Cancelled");
        System.out.println("Cancelling order");
    }
    
}
