/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.command;
import model.Builder.Order;
import dao.OrderDao;
/**
 *
 * @author MAS
 */
public class PlaceOrderCommand implements Command {

    private Order order;
   
    
    public PlaceOrderCommand(Order order) {
        this.order = order;

    }
     
    @Override
    public void execute() {
        order.placeOrder(order);
    }

    @Override
    public void undo() {
        System.out.println("Undoing Order " + order);
        order.cancelOrder(order);
    }
    
}

