package edu.upc.dsa.models;

public class UserPoint {
    private String userId;
    private int x;
    private int y;

    public UserPoint() {}

    public UserPoint(String userId, int x, int y) {
        this.userId = userId;
        this.x = x;
        this.y = y;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}
