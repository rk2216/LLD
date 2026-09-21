package commands.implementations;

import game.User;

public class SMSCommand {
    NotificationDetails details;

    public SMSCommand(NotificationDetails details) {
        this.details = details;
    }

    public NotificationDetails getDetails() {
        return details;
    }
}
