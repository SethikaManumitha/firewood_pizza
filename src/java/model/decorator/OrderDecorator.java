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
abstract class OrderDecorator extends Order {
    protected Order baseOrder;

    protected OrderDecorator(Order baseOrder) {
        super(new Order.OrderBuilder(
                baseOrder.getId(),
                baseOrder.getName(),
                baseOrder.getEmail(), 
                baseOrder.getAddress(), 
                baseOrder.getStatus(),
                baseOrder.getItems())
               .setTotal(baseOrder.getTotal())
               .setDiscount(baseOrder.getDiscount())
               .setPaymentType(baseOrder.getPaymentType())
               .setDeliveryOption(baseOrder.getDeliveryOption())
               .setDate(baseOrder.getDate()));
        
        this.baseOrder = baseOrder;
    }

    @Override
    public abstract double getTotal();
    
    public abstract String getDescription();
}
