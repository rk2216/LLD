package api;

import commands.implementations.SendEmailCommand;
import game.User;

public class EmailService {
    private void sendEmail(User user, String message) {
        // send email
    }

    public void send(SendEmailCommand command) {
        sendEmail(command.getReceiver(), command.getMessage());
    }
}
