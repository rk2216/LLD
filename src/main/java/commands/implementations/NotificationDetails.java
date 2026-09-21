package commands.implementations;

import game.User;

public class NotificationDetails {
    User receiver;
    String message;

    public NotificationDetails(User user, String message) {
        this.receiver = user;
        this.message = message;
    }

    public User getReceiver() {
        return receiver;
    }
    public String getMessage() {
        return message;
    }
}
