package commands.builder;

import commands.implementations.SMSCommand;
import game.User;

public class SMSCommandBuilder {
    NotificationBuilder notificationBuilder;
    String link;
    String templateId;
    String templateString;

    public SMSCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }
    public SMSCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }
    public SMSCommand build() {
        return new SMSCommand(notificationBuilder.build());
    }
}
