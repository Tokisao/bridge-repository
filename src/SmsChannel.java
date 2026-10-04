public class SmsChannel implements Channel{
    @Override
    public String send(String id, String title, String body) {
        return "SMS " + id + ": " + title + " - " + body;
    }
}