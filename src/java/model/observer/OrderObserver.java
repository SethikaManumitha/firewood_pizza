/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.observer;


/**
 *
 * @author MAS
 */
public class OrderObserver {
    private int orderId;
    private String status; 
    private User user; 

    public OrderObserver(int orderId) {
        this.orderId = orderId;
    }

  
    public void setUser(User user) { 
        this.user = user;
    }

   
    public String changeStatus(String status) { 
        this.status = status;
        notifyUser();
        System.out.println("Order " + orderId + " status updated to: " + status);
        return "Order " + orderId + " status updated to: " + status;
    }

    
    private void notifyUser() {
        if (user != null) {
            user.update(status);
        } else {
            System.out.println("No user is associated with order " + orderId);
        }
    }

    
    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }
}
