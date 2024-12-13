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

public interface OrderState {
    void next(Order order);
    String getStatus();
}
