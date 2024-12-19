/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.command;
import model.Builder.Order;
/**
 *
 * @author MAS
 */
class ProvideFeedbackCommand {
    private Order order;
    private String feedback;

    public ProvideFeedbackCommand(Order order, String feedback) {
        this.order = order;
        this.feedback = feedback;
    }

    
}

