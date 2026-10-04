public class Reminder extends Notification {
    public Reminder(String id, String title, String message, Channel channel) {
        super(id, title, message, channel);
    }

    @Override
    public String execute() {
        return channel.send(id, "Reminder: " + title, message);
    }
}