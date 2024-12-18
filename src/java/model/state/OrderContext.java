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
    private OrderState currentState;

    public OrderContext() {
        currentState = new PlacedState(); // Default state
    }

    public void setState(OrderState state) {
        this.currentState = state;
    }

    public void nextState() {
        currentState.next(this);
    }

    public void previousState() {
        currentState.previous(this);
    }

    public String getCurrentState() {
        return currentState.getState();
    }
    
    public void resetState() {
        this.currentState = new PlacedState(); 
    }
}

