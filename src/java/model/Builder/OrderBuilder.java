package model.Builder;

import java.util.Date;
import java.util.Map;

public class OrderBuilder {
    // Required fields
    int id;
    String name;
    String email;

    // Optional fields
    Map<String, String> items;
    String address;
    String deliveryOption;
    String paymentType;
    double total;
    double discount;
    String status;
    Date date;

    // Constructor for required fields
    public OrderBuilder(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Setter methods for optional fields
    public OrderBuilder items(Map<String, String> items) {
        this.items = items;
        return this;
    }

    public OrderBuilder address(String address) {
        this.address = address;
        return this;
    }

    public OrderBuilder deliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
        return this;
    }

    public OrderBuilder paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public OrderBuilder total(double total) {
        this.total = total;
        return this;
    }

    public OrderBuilder discount(double discount) {
        this.discount = discount;
        return this;
    }

    public OrderBuilder status(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder date(Date date) {
        this.date = date;
        return this;
    }

    public Order build() {
        return new Order(this);
    }
}
