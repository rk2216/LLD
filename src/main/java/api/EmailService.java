package api;

import commands.implementations.EmailCommand;
import game.User;

public class EmailService {
    private void sendEmail(User user, String message) {
        // send email
    }

    public void send(EmailCommand command) {
        sendEmail(command.getDetails().getReceiver(), command.getDetails().getMessage());
    }
}
