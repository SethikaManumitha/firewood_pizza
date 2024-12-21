/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.command;

import java.util.Stack;

/**
 *
 * @author MAS
 */
public class CommandInvoker {
    Command command;
    Stack<Command> commandHistory = new Stack<>();
    
    public void setCommand(Command command) {this.command = command;}
    
    public void executeCommand() {
        command.execute();
        commandHistory.push(command);
    }
    
    public void undoCommand() {
        Command lastCommand = commandHistory.pop();
        lastCommand.undo();
    }
    
}
