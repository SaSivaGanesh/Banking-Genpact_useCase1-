package bank.model;

import java.util.HashSet;
import java.util.Set;

public class LoggedInUsers {
    private static Set<Long> loggedInAccounts = new HashSet<>();
    private static long lastAddedAccountNumber; // Track last added account number

    // Method to add an account number to the list
    public static synchronized void addAccount(long accountNumber) {
        loggedInAccounts.add(accountNumber);
        lastAddedAccountNumber = accountNumber; // Update last added account number
    }

    // Method to remove an account number from the list
    public static synchronized void removeAccount(long accountNumber) {
        loggedInAccounts.remove(accountNumber);
    }

    // Method to check if an account number is in the list
    public static synchronized boolean isAccountLoggedIn(long accountNumber) {
        return loggedInAccounts.contains(accountNumber);
    }

    // Method to get the list of all logged-in account numbers
    public static synchronized Set<Long> getLoggedInAccounts() {
        return new HashSet<>(loggedInAccounts);
    }

    // Method to retrieve the last added account number
    public static synchronized long getLastAddedAccountNumber() {
        return lastAddedAccountNumber;
    }
}
