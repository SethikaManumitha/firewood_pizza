/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.state;

/**
 *
 * @author MAS
 */
import model.Builder.*;
public class DeliveryState implements OrderState {
    @Override
    public void next(Order order) {
        order.setOrderState(new DeliveredState());
    }

    @Override
    public String getStatus() {
        return "Out for Delivery";
    }
}
