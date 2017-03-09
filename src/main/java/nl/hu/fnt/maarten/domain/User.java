package nl.hu.fnt.maarten.domain;

public class User {
    private int userId;
    private String token;

    public User(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public User(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
