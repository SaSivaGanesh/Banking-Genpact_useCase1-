package bank.model;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SessionManager {
    private static final ConcurrentMap<Long, HttpSession> loggedInUsers = new ConcurrentHashMap<>();

    public static void addSession(long accountNumber, HttpSession session) {
        loggedInUsers.put(accountNumber, session);
    }

    public static void removeSession(long accountNumber) {
        loggedInUsers.remove(accountNumber);
    }

    public static HttpSession getMostRecentSession() {
        return loggedInUsers.values().stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }
}
