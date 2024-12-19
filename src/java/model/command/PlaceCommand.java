/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.command;
import model.Builder.Pizza;
import model.Customer;
/**
 *
 * @author MAS
 */
class PlaceOrderCommand {
    private Pizza pizza;
    private Customer customer;

    public PlaceOrderCommand(Pizza pizza, Customer customer) {
        this.pizza = pizza;
        this.customer = customer;
    }

    public void execute() {
        // Logic to place the order for this customer and pizza
    }
}

