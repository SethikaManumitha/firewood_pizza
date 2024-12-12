/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.handler;

/**
 *
 * @author MAS
 */

import model.Builder.Pizza;

public class SizeHandler extends PizzaHandler {
     protected PizzaHandler nextHandler;
    
    @Override
    public void setNextHandler(PizzaHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    @Override
    public void handle(Pizza pizza) throws Exception {
        if (pizza.getSize()== null ||pizza.getSize().isEmpty()) {
            throw new Exception("Please Select Size");
        }
        if (nextHandler != null) {
            nextHandler.handle(pizza);
        }
    }
}

