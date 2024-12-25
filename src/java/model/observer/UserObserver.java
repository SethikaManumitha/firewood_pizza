package model.observer;

/**
 * Observer class that represents users who receive notifications.
 */
public class UserObserver {
    private String name;
    private String email;

    public UserObserver(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String updateUser(String status) {
        return "Notification to " + name + 
                ": Your order status is now: " + status;
    }
}
