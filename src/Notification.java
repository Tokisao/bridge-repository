public abstract class Notification {
    protected String id;
    protected String title;
    protected String message;
    protected Channel channel;

    public Notification(String id, String title, String message, Channel channel) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.channel = channel;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }

    public void setImplementation(Channel channel) { this.channel = channel; }

    public String execute() { return channel.send(id, title, message); }
}