public class UrgentAlert extends Notification {
    public UrgentAlert(String id, String title, String message, Channel channel) {
        super(id, title, message, channel);
    }
    @Override
    public String execute() {
        return channel.send(id, "[URGENT] " + title, message);
    }
}