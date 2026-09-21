package commands.builder;

import commands.implementations.EmailCommand;
import game.User;

public class EmailCommandBuilder {
    NotificationBuilder notificationBuilder;
    String link;

    public EmailCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }
    public EmailCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }
    public EmailCommandBuilder link(String link) {
        this.link = link;
        return this;
    }
    public EmailCommand build() {
        return new EmailCommand(notificationBuilder.build(), link);
    }
}
