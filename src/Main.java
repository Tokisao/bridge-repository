public class Main {
    private static int passCount = 0;
    private static int totalCount = 0;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Use --demo flag to run the demonstration checks.");
        }
    }

    // Compares actual result with expected one and prints PASS/FAIL.
    private static void check(String testId, String classes, String actual, String expected) {
        totalCount++;
        boolean ok = expected.equals(actual);
        if (ok) {
            passCount++;
        }
        System.out.println(testId + " " + (ok ? "PASS" : "FAIL")
                + " | " + classes + " | result=" + actual);
        if (!ok) {
            System.out.println("   expected=" + expected);
        }
    }

    private static void runDemo() {
        final String id = "N-101";
        final String title = "Meeting";
        final String msg = "Team sync at 3 PM";

        Channel email = new EmailChannel();
        Channel sms = new SmsChannel();
        Channel push = new PushChannel();
        

        // T1: Reminder + Email
        Notification t1 = new Reminder(id, title, msg, email);
        check("T1", "Reminder + EmailChannel", t1.execute(),
                "Email id:N-101\n Subject: Reminder: Meeting\nTeam sync at 3 PM");

        // T2: Reminder + SMS
        Notification t2 = new Reminder(id, title, msg, sms);
        check("T2", "Reminder + SmsChannel", t2.execute(),
                "SMS N-101: Reminder: Meeting - Team sync at 3 PM");

        // T3: UrgentAlert + Email
        Notification t3 = new UrgentAlert(id, title, msg, email);
        check("T3", "UrgentAlert + EmailChannel", t3.execute(),
                "Email id:N-101\n Subject: [URGENT] Meeting\nTeam sync at 3 PM");

        // T4: UrgentAlert + SMS
        Notification t4 = new UrgentAlert(id, title, msg, sms);
        check("T4", "UrgentAlert + SmsChannel", t4.execute(),
                "SMS N-101: [URGENT] Meeting - Team sync at 3 PM");

        // T5: runtime switch on the same object
        runSwitchCheck(id, title, msg, email, sms);
        
         // T6: Reminder + Push (extension I3)
        Notification t6 = new Reminder(id, title, msg, push);
        check("T6", "Reminder + PushChannel", t6.execute(),
                "PUSH NOTIFICATION App Alert N-101 ->\n Reminder: Meeting:Team sync at 3 PM");

        // T7: UrgentAlert + Push (extension I3)
        Notification t7 = new UrgentAlert(id, title, msg, push);
        check("T7", "UrgentAlert + PushChannel", t7.execute(),
                "PUSH NOTIFICATION App Alert N-101 ->\n [URGENT] Meeting:Team sync at 3 PM");
       

        System.out.println("SUMMARY: " + passCount + "/" + totalCount + " PASS");
    }
    
    private static void runSwitchCheck(String id, String title, String msg,
                                       Channel email, Channel sms) {
        totalCount++;

        Notification obj = new Reminder(id, title, msg, email);
        Notification refBefore = obj;
        String before = obj.execute();

        obj.setImplementation(sms);
        Notification refAfter = obj;
        String after = obj.execute();

        String expectedBefore = "Email id:N-101\n Subject: Reminder: Meeting\nTeam sync at 3 PM";
        String expectedAfter = "SMS N-101: Reminder: Meeting - Team sync at 3 PM";

        boolean sameObject = (refBefore == refAfter);
        boolean stateUnchanged = obj.getId().equals(id)
                && obj.getTitle().equals(title)
                && obj.getMessage().equals(msg);
        boolean resultsOk = expectedBefore.equals(before) && expectedAfter.equals(after);

        boolean ok = sameObject && stateUnchanged && resultsOk;
        if (ok) {
            passCount++;
        }

        System.out.println("T5 " + (ok ? "PASS" : "FAIL")
                + " | sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged);
        System.out.println("   before=" + before + " | after=" + after);
        if (!ok) {
            System.out.println("   expectedBefore=" + expectedBefore
                    + " | expectedAfter=" + expectedAfter);
        }
    }
}