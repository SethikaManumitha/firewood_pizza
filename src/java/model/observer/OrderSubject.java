package model.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class that manages observers and notifies them of changes.
 */
public class OrderSubject {
    private int orderId;
    private String status;
    private List<UserObserver> observers = new ArrayList<>();

    public OrderSubject(int orderId) {
        this.orderId = orderId;
    }

    public void addObserver(UserObserver observer) { observers.add(observer); }

    public void removeObserver(UserObserver observer) { observers.remove(observer); }

    public String changeStatus(String status) {
        this.status = status;
        return notifyObservers();
    }

    private String notifyObservers() {
        StringBuilder notifications = new StringBuilder();
        for (UserObserver observer : observers) {
            notifications.append(observer.updateUser(status)).append("\n");
        }
        return notifications.toString();
    }
}
