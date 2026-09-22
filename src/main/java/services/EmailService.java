package services;

import commands.implementations.EmailCommand;
import game.User;

public class EmailService {
    private void sendEmail(User user, String message) {
        // send email
    }

    public Void send(EmailCommand command) {
        sendEmail(command.getReceiver(), command.getMessage());
        return null;
    }
}
