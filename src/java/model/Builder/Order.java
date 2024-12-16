package model.Builder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String email;
    private Map<String, String> items;  // Directly accepting the HashMap for items
    private String address;
    private String deliveryOption;
    private String paymentType;
    private double total;
    private double discount;
    private String status;
    private Date date;

    // Private constructor to enforce using Builder
    private Order(OrderBuilder builder) {
        this.email = builder.email;
        this.items = builder.items;
        this.address = builder.address;
        this.deliveryOption = builder.deliveryOption;
        this.paymentType = builder.paymentType;
        this.total = builder.total;
        this.discount = builder.discount;
        this.status = builder.status;
        this.date = builder.date;
    }

    // Getters (optional but useful for later retrieval)
    public String getEmail() {
        return email;
    }

    public Map<String, String> getItems() {  // Returns the HashMap of items
        return items;
    }

    public String getAddress() {
        return address;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public double getTotal() {
        return total;
    }

    public double getDiscount() {
        return discount;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

  
    public static class OrderBuilder {
        private String email;
        private Map<String, String> items;  // Now accepts the HashMap directly
        private String address;
        private String deliveryOption;
        private String paymentType;
        private double total;
        private double discount = 0; 
        private String status;
        private Date date = new Date();

        // Constructor that accepts HashMap directly for items
        public OrderBuilder(String email, String address, String status, Map<String, String> items) {
            this.email = email;
            this.address = address;
            this.status = status;
            this.items = items;  // Assign the passed-in HashMap directly
        }

        public OrderBuilder setDeliveryOption(String deliveryOption) {
            this.deliveryOption = deliveryOption;
            return this;
        }

        public OrderBuilder setPaymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public OrderBuilder setTotal(double total) {
            this.total = total;
            return this;
        }

        public OrderBuilder setDiscount(double discount) {
            this.discount = discount;
            return this;
        }

        public OrderBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
