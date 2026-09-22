package events;

import game.User;

public class WinEvent extends Event {
    public WinEvent(User user, String message, String link, String type) {
        super(user, message, link, type);
    }
}
