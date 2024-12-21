/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MAS
 */
public class Feedback {
    private String id;
    private String feedback;
    private String rating;
    private String user;
    private String status;
    
    public Feedback(String id,String feedback,String rating,String user){
        this.id = id;
        this.feedback = feedback;
        this.rating = rating;
        this.user = user;
        
    }

    public String getId() {
        return id;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getRating() {
        return rating;
    }

    public String getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
