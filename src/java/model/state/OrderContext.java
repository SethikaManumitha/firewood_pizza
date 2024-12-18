/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.state;

/**
 *
 * @author MAS
 */
public class OrderContext {
    
   private OrderState state;
   
   public OrderContext() {
       
        this.state = new PlacedState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }

    public void processOrder() {
        state.processOrder(this);
    }

    public String getStatus() {
        return state.getStatus();
    }
    
}

