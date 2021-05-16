package com.cluedo.game.network;

public class NetworkPlayer {
    private String Id;
    private String Username;

    public NetworkPlayer(String id, String username) {
        Id = id;
        Username = username;
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
}
