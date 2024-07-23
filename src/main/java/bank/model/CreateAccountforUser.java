package bank.model;

public class CreateAccountforUser {
    private String name;
    private String address;
    private String mobile;
    private String email;
    private String dob;
    private double balance;
    private String password;
    private String accountNumber;
    private String accountType;
    private String accountStatus;
    private String createdDate; // Added field for created_date

    // Constructor without accountNumber, accountType, accountStatus, and createdDate parameters
    public CreateAccountforUser(String name, String address, String mobile, String email, String dob, double balance, String password) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.balance = balance;
        this.password = password;
    }

    // Constructor with all parameters except createdDate
    public CreateAccountforUser(String name, String address, String mobile, String email, String dob, double balance, String password, String accountNumber, String accountType, String accountStatus) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.balance = balance;
        this.password = password;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
    }

    // Constructor with all parameters including createdDate
    public CreateAccountforUser(String name, String address, String mobile, String email, String dob, double balance, String password, String accountNumber, String accountType, String accountStatus, String createdDate) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
        this.balance = balance;
        this.password = password;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.createdDate = createdDate;
    }

    // Getters and setters for all fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
