package com.cluedo.game;
import com.badlogic.gdx.Gdx;
import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


class ConnectionService {

    private OkHttpClient client;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static final String Url = "https://se2ss21cluedo.herokuapp.com/";

    public ConnectionService() {
        client = new OkHttpClient();
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
            jsonObject.put("username", username);
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            Request request = new Request.Builder()
                    .url(Url + "games/register")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            return response.code();
        } catch (Exception ex) {
            Gdx.app.log("Register Error", ex.getMessage());
        }

        //"Free" Error Code. Signals that something in the Method for calling the server failed
        return 512;
    }
}