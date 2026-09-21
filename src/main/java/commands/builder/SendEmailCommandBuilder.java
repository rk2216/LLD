package commands.builder;

import commands.implementations.SendEmailCommand;
import game.User;

public class SendEmailCommandBuilder {
    User user;
    String message;
    String link;
    String templateId;
    String templateString;

    public SendEmailCommandBuilder user(User user) {
        this.user = user;
        return this;
    }
    public SendEmailCommandBuilder message(String message) {
        this.message = message;
        return this;
    }
    public SendEmailCommandBuilder link(String link) {
        this.link = link;
        return this;
    }
    public SendEmailCommandBuilder templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }
    public SendEmailCommandBuilder templateString(String templateString) {
        this.templateString = templateString;
        return this;
    }
    public SendEmailCommand build() {
        return new SendEmailCommand(user, message);
    }
}
