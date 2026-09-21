package api;

import commands.implementations.SMSCommand;
import game.User;

public class SMSService {
    private void sendSMS(User user, String message) {
        // send SMS
    }

    public void send(SMSCommand command) {
        sendSMS(command.getDetails().getReceiver(), command.getDetails().getMessage());
    }
}
