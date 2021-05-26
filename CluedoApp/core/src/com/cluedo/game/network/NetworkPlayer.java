package com.cluedo.game.network;

public class NetworkPlayer {
    private String Id;
    private String Username;
    private int x;
    private int y;
    private String playerImage;

    public NetworkPlayer(String id, String username, int x, int y, String playerImage) {
        Id = id;
        Username = username;
        this.x = x;
        this.y = y;
        this.playerImage = playerImage;
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

    public String getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }
}
