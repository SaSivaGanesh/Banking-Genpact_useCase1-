package bank.model;

import java.util.Date;

public class Customer {
    private String name;
    private String address;
    private String dob;
    private String mobileNumber;
    private String email;
    private long accountNumber;
    private String accountType;
    private double balance; // Added balance field
    private String accountStatus; // Added accountStatus field
    private Date createdDate; // Added createdDate field

    public Customer(String name, String address, String dob, String mobileNumber, String email, long accountNumber, String accountType) {
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    // Getters and setters for existing fields
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public long getAccountNumber() { return accountNumber; }
    public void setAccountNumber(long accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    // Getters and setters for new fields: balance, accountStatus, createdDate
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
