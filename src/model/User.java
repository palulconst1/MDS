package model;

public class User {
    private static int numberUsers = 0;
    private int userID;
    private String email;
    private String password;
    private int currency = 10000;

    public User() {
        numberUsers++;
        userID = numberUsers;
    }

    public User(String email, String password) {
        numberUsers++;
        userID = numberUsers;
        this.email = email;
        this.password = password;
    }

    public void setUserID(int x) {
        this.userID = x;
    }

    public int getUserID() {
        return userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return this.userID + " " + this.email + " " + this.password;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}