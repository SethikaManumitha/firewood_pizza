/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.observer;

/**
 *
 * @author MAS
 */
public class User {
     private String name;
     private String email;

    public User(String name,String email) {
        this.name = name;
        this.email = email;
        
    }

    // Update method to handle order status notifications
    public void update(String status) {
        System.out.println("Notification to " + name + "( " + email + " )" +
                " Your order status is now: " + status);
    }
}
