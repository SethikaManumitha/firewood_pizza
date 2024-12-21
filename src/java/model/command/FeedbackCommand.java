/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.command;

import model.Feedback;
/**
 *
 * @author MAS
 */
public class FeedbackCommand implements Command{

    private final Feedback feedbackObj;
   
    public FeedbackCommand(Feedback feedbackObj) {
        this.feedbackObj = feedbackObj;
    }
    
    @Override
    public void execute() {
        System.out.println("Setting feedback for order");
        feedbackObj.setStatus("1");
    }

    @Override
    public void undo() {
        System.out.println("Undoing feedback for order");
        feedbackObj.setStatus("0");
    }
    

    
}

