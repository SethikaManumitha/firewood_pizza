/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Builder;

/**
 *
 * @author MAS
 */
import java.util.Date;
import model.state.*;

public class Order {
    private final String pizza;
    private final String customer;
    private final Date date;
    private final double totalPrice;
    private final double discount;
    private final String deliveryType;
    private OrderState orderState; 

    private Order(OrderBuilder builder) {
        this.pizza = builder.pizza;
        this.customer = builder.customer;
        this.date = builder.date;
        this.totalPrice = builder.totalPrice;
        this.discount = builder.discount;
        this.deliveryType = builder.deliveryType;
        this.orderState = builder.orderState; // Initial state
    }

    public void setOrderState(OrderState newState) {
        this.orderState = newState;
    }

    public void progressState() {
        orderState.next(this);
    }

    public String getOrderStatus() {
        return orderState.getStatus();
    }

    // Getters for other fields
    public String getPizza() { return pizza; }
    public String getCustomer() { return customer; }
    public Date getDate() { return date; }
    public double getTotalPrice() { return totalPrice; }
    public double getDiscount() { return discount; }
    public String getDeliveryType() { return deliveryType; }

    // Builder class
    public static class OrderBuilder {
        private String pizza;
        private String customer;
        private Date date;
        private double totalPrice;
        private double discount;
        private String deliveryType;
        private OrderState orderState; // State Pattern integration

        public OrderBuilder(String pizza, String customer) {
            this.pizza = pizza;
            this.customer = customer;
        }

        public OrderBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public OrderBuilder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public OrderBuilder setDiscount(double discount) {
            this.discount = discount;
            return this;
        }

        public OrderBuilder setDeliveryType(String deliveryType) {
            this.deliveryType = deliveryType;
            return this;
        }

        public OrderBuilder setOrderState(OrderState state) {
            this.orderState = state;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}

