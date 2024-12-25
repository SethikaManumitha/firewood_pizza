/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.decorator;

import model.Builder.*;
/**
 *
 * @author MAS
 */
public abstract class OrderDecorator extends Order {
    protected Order order;

    public OrderDecorator(Order order) {
        super(new OrderBuilder(order.getId(), order.getName(), order.getEmail())
                .items(order.getItems())
                .address(order.getAddress())
                .deliveryOption(order.getDeliveryOption())
                .paymentType(order.getPaymentType())
                .total(order.getTotal())
                .discount(order.getDiscount())
                .status(order.getStatus())
                .date(order.getDate())     
        );
        this.order = order;
    }

    @Override
    public double getTotal() {
        return order.getTotal();
    }
}
