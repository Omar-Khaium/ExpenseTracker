package org.emptybit.expensetracker.Model;

public class LoginModel {
    private int id;
    private UserModel User;
    private String username;
    private String password;

    public LoginModel() {
    }

    public LoginModel(int id, UserModel user, String username, String password) {
        this.id = id;
        User = user;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return User;
    }

    public void setUser(UserModel user) {
        User = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
