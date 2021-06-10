package com.cluedo.game.network;

import com.badlogic.gdx.Gdx;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectionService {
    private OkHttpClient client;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static final String Url = "https://se2ss21cluedo.herokuapp.com/";
    //"Free" Error Code. Signals that something in the Method for calling the server failed
    private final int ServerErrorCode = 512;
    private String GameId;
    private String PlayerId;
    private List<NetworkPlayer> players;
    private static ConnectionService instance;
    private NetworkPlayer currentPlayer;
    //The finishing combination
    private String Weapon;
    private String Room;
    private String Suspect;
    private boolean isGameOver;

    private ConnectionService() {
        client = new OkHttpClient();
        players = new ArrayList<>();
    }

    public static ConnectionService GetInstance() {
        if (instance == null)
            instance = new ConnectionService();

        return instance;
    }

    public String GetGameId() {return GameId;}

    public String GetPlayerId() {return PlayerId;}

    public List<NetworkPlayer> getPlayers() {
        return players;
    }

    public NetworkPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(NetworkPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getWeapon() {
        return Weapon;
    }

    public String getRoom() {
        return Room;
    }

    public String getSuspect() {
        return Suspect;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    /*
                    Method to register for a game with a username.
                    Takes in the username.
                    Returns the HTTP-Code. If the code 512 is returned then there was an error when calling the server.
                */
    public int RegisterForGame(String username)
    {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Username", username);
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(Url + "games/register")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject jsonRes = new JSONObject(response.body().string());
            PlayerId = jsonRes.getString("playerId");
            return response.code();
        } catch (Exception ex) {
            Gdx.app.log("Register Error", ex.getMessage());
        }

        return ServerErrorCode;
    }

    /*
        Method to check the Registration.
        Gets called periodically when registering for game.
     */
    public int CheckRegistration() {
        try {
            Request request = new Request.Builder()
                    .url(Url + "games/checkGameState")
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);
                GameId = jsonObject.getString("gameId");
                isGameOver = jsonObject.getBoolean("isGameOver");
                GetPlayersOfJsonObject(responseBody);
                GetFinishCombinationOfJsonObject(responseBody);
            }

            return response.code();
        } catch (Exception ex) {
            Gdx.app.log("Check Registration Error", ex.getMessage());
        }

        return ServerErrorCode;
    }

    /*
        Method to check the Registration.
        Gets called periodically when registering for game.
    */
    public int PostNewPosition(int x, int y) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("playerId", PlayerId);
            jsonObject.put("x", x);
            jsonObject.put("y", y);

            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(Url + "games/playerMoved")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            return response.code();
        } catch (Exception ex) {
            Gdx.app.log("Posting New Position Error", ex.getMessage());
        }

        return ServerErrorCode;
    }

    public int GetGame() {
        try {
            Request request = new Request.Builder()
                    .url(Url + "games")
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                GetPlayersOfJsonObject(response.body().string());
            }

            return response.code();
        } catch (Exception ex) {
            Gdx.app.log("Get Game Error", ex.getMessage());
        }

        return ServerErrorCode;
    }

    public int FinishMove() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("playerId", PlayerId);

            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(Url + "games/finishMove")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            return response.code();
        } catch (Exception ex) {
            Gdx.app.log("Finish Move Error", ex.getMessage());
        }

        return ServerErrorCode;
    }

    private void GetPlayersOfJsonObject(String responseBody) throws IOException {
        List<NetworkPlayer> tempPlayers = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray playerArray = jsonObject.getJSONArray("players");

        for (int i=0; i<playerArray.length(); i++) {
            JSONObject playerObject = playerArray.getJSONObject(i);
            tempPlayers.add(new NetworkPlayer(playerObject.getString("id"), playerObject.getString("username"), playerObject.getInt("x"), playerObject.getInt("y"), playerObject.getString("playerImage"), playerObject.getBoolean("maywalk")));
        }

        players = tempPlayers;
    }

    private void GetFinishCombinationOfJsonObject(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject finishCombination = jsonObject.getJSONObject("finishCombination");
        Weapon = finishCombination.getString("weapon");
        Room = finishCombination.getString("room");
        Suspect = finishCombination.getString("suspect");
    }
}