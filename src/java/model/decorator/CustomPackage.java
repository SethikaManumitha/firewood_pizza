/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.decorator;
import model.Builder.Order;
/**
 *
 * @author MAS
 */
public class CustomPackage extends OrderDecorator {
    private final double wrappingCost = 50.0;

    public CustomPackage(Order order) {
        super(order);
    }

    @Override
    public double getTotal() {
        return order.getTotal() + wrappingCost;
    }

    @Override   
    public String toString() {
        return order.toString() + " with gift wrapping";
    }
}

