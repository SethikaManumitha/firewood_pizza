package model.Builder;

import java.util.Date;
import java.util.Map;

public class Order {
    private int id;  
    private String name; 
    private String email;
    private Map<String, String> items; 
    private String address;
    private String deliveryOption;
    private String paymentType;
    private double total;
    private double discount;
    private String status;
    private Date date;

    // Constructor
    public Order(OrderBuilder builder) {
        this.id = builder.id; 
        this.name = builder.name; 
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

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public Map<String, String> getItems() {
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
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void placeOrder(Order order) {
        System.out.println("Order placed: " + order);
    }

    public void cancelOrder(Order order) {
        System.out.println("Order " + order + " has been canceled.");
    }

    // Methods to handle feedback
    public String addFeedback(String feedback) {
        System.out.println(feedback);
        return feedback;
    }
    
    public String removeFeedback() {
        return "";
    }

   
}
