package events;

import game.User;

public class ActivityEvent extends Event {
    public ActivityEvent(User user, String message, String link, String type) {
        super(user, message, link, type);
    }
}
