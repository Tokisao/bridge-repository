# Assignment 3: Bridge Pattern Implementation

**Student Name:** Dayana Zharylgapova
**Group:** SE-2538
**Topic Option:** B (Notifications)  
**Repository URL:** https://github.com/your-username/assignment3-bridge  
**Base Commit Hash:** `29199d5`  

---

## Role Mapping Table

| Bridge Role | Class / Interface Name | Source File Path | Key Responsibilities & Methods |
| :--- | :--- | :--- | :--- |
| **Abstraction** | `Notification` | `src/Notification.java` | Base class; holds `Channel` reference, `id`, `title`, `message`. Defines `execute()` and `setImplementation(Channel)`. |
| **Refined Abstraction 1 (A1)** | `Reminder` | `src/Reminder.java` | Formats message title with `"Reminder: "` prefix. |
| **Refined Abstraction 2 (A2)** | `UrgentAlert` | `src/UrgentAlert.java` | Formats message title with `"[URGENT] "` prefix. |
| **Implementor** | `Channel` | `src/Channel.java` | Interface defining `send(String id, String title, String body)`. |
| **Concrete Implementor 1 (I1)**| `EmailChannel` | `src/EmailChannel.java` | Formats output as multi-line Email notification. |
| **Concrete Implementor 2 (I2)**| `SmsChannel` | `src/SmsChannel.java` | Formats output as single-line SMS notification. |
| **Concrete Implementor 3 (I3)**| `PushChannel` | `src/PushChannel.java` | Added extension; formats output as Push Notification. |
| **Client** | `Main` | `src/Main.java` | Runs demonstration checks `T1`–`T7` via `--demo` flag. |

---

## Key Method Reference Pointer

* **Bridge Field Reference:** `Notification.channel` (`protected Channel channel;`) in `src/Notification.java`
* **Execution Method:** `Notification.execute()` in `src/Notification.java` (delegates to `channel.send(...)`)
* **Runtime Switch Method:** `Notification.setImplementation(Channel channel)` in `src/Notification.java`
* **Runtime Object Identity Test (T5):** `Main.runSwitchCheck(...)` in `src/Main.java`

---

## Build and Run Instructions

From the root of the project directory, run the following standard commands:

```bash
# Compile all sources listed in sources.txt into the 'out' directory
javac --release 17 -encoding UTF-8 -d out "@sources.txt"

# Run the application demonstration
java -cp out Main.java --demo
```

---

## Expected Demonstration Outcomes (T1 – T7)

| Test ID | Setup / Action | Expected Result |
| :--- | :--- | :--- |
| **T1** | `Reminder` + `EmailChannel` | `Email id:N-101\n Subject: Reminder: Meeting\nTeam sync at 3 PM` |
| **T2** | `Reminder` + `SmsChannel` | `SMS N-101: Reminder: Meeting - Team sync at 3 PM` |
| **T3** | `UrgentAlert` + `EmailChannel` | `Email id:N-101\n Subject: [URGENT] Meeting\nTeam sync at 3 PM` |
| **T4** | `UrgentAlert` + `SmsChannel` | `SMS N-101: [URGENT] Meeting - Team sync at 3 PM` |
| **T5** | Runtime switch on same `Reminder` | `sameObject=true` (`==`), `stateUnchanged=true`, switches from `EmailChannel` output to `SmsChannel` output dynamically. |
| **T6** | `Reminder` + `PushChannel` | `PUSH NOTIFICATION App Alert N-101 ->\n Reminder: Meeting:Team sync at 3 PM` |
| **T7** | `UrgentAlert` + `PushChannel` | `PUSH NOTIFICATION App Alert N-101 ->\n [URGENT] Meeting:Team sync at 3 PM` |
