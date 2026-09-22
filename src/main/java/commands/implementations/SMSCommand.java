package commands.implementations;

import events.Event;
import game.User;

public class SMSCommand {
    NotificationDetails details;

    public SMSCommand(Event event) {
        this.details = new NotificationDetails(event.getUser(), event.getMessage());
    }

    public NotificationDetails getDetails() {
        return details;
    }

    public User getReceiver() {
        return details.getReceiver();
    }

    public String getMessage() {
        return details.getMessage();
    }
}
