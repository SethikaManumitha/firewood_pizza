/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.command;
import model.Builder.Pizza;
import model.Builder.Topping;
/**
 *
 * @author MAS
 */
class AddToppingCommand {
    private Pizza pizza;
    private Topping topping;

    public AddToppingCommand(Pizza pizza, Topping topping) {
        this.pizza = pizza;
        this.topping = topping;
    }

}

