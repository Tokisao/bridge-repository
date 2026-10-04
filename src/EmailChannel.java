public class EmailChannel implements Channel{
    @Override
    public String send(String id, String title, String body) {
        return "Email id:" + id + "\n Subject: " + title + "\n" + body;
    }
}