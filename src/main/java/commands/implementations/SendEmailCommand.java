package commands.implementations;

import game.User;

public class SendEmailCommand {
    User receiver;
    String message;
    String link;
    String templateId;
    String templateString;

    public SendEmailCommand(User user, String message) {
        receiver = user;
        this.message = message;
    }

    public User getReceiver() {
        return receiver;
    }
    public String getMessage() {
        return message;
    }
}
