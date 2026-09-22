package commands.implementations;

import events.Event;
import game.User;

public class EmailCommand {
    NotificationDetails details;
    String link;

    public EmailCommand(Event event) {
        this.details = new NotificationDetails(event.getUser(), event.getMessage());
        this.link = event.getLink();
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
