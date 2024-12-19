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

    public CustomPackage(Order baseOrder) {
        super(baseOrder);
    }

    @Override
    public double getTotal() {
        return baseOrder.getTotal() + wrappingCost;
    }

    @Override
    public String getDescription() {
        return baseOrder.getStatus() + " with gift wrapping";
    }
}
