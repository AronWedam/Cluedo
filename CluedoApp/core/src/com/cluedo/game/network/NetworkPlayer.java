package com.cluedo.game.network;

public class NetworkPlayer {
    private String Id;
    private String Username;
    private int x;
    private int y;

    public NetworkPlayer(String id, String username, int x, int y) {
        Id = id;
        Username = username;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
