/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.handler;

import model.Builder.Pizza;

/**
 *
 * @author MAS
 */
public abstract class PizzaHandler {
    protected PizzaHandler nextHandler;

    public void setNextHandler(PizzaHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(Pizza pizza) throws Exception;
}
