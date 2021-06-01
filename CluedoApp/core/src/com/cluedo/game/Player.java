package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cluedo.game.network.ConnectionService;

public class Player {
    private static CardHandOut cardHandOut;
    SpriteBatch batch;
    Texture texture;
    private int x,y;
    private CluedoMap cluedoMap;
    private ConnectionService connectionService;

    Notebook notebook;
    private static int mySuspectCard;
    private static int myWeaponCard;
    private static int myRoomCard;

    public Player(Texture texture, CluedoMap cluedoMap, int x, int y){
        this.texture = texture;
        this.cluedoMap = cluedoMap;
        this.x = x;
        this.y = y;

        myCards();

        connectionService = ConnectionService.GetInstance();
    }

    // Display Player
    public void render(OrthographicCamera camera, SpriteBatch batch, float x, float y, float width, float height){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture,x,y,width,height);
        batch.end();
    }


    //Set Player position
    public void setPos(int x, int y){
        if(valid(x,y)){
            this.x = x;
            this.y = y;

            Thread postPosThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    connectionService.PostNewPosition(getX(), getY());
                }
            });
            postPosThread.start();
            try {
                postPosThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(int x) {
        this.x = x;
        if(this.x < 0) this.x = 0;
        if(this.x > 32*28) this.x = 32*28;
    }

    public void setY(int y) {
        this.y = y;
        if(this.y < 0) this.y = 0;
        if(this.y > 32*28) this.y = 32*28;

    }

    /**
     * checks if desired move is valid
     * @param x
     * @param y
     * @return true if move to desired tile is possile
     */

    public boolean valid(int x, int y){

        //Make Sure it keeps inside Gameboard
        if(x < 0) return false;
        if(y < 0) return false;

        //If someone is touching on the notebook it returns false
        if(y <= Gdx.graphics.getWidth() / 3) return false;

        if(x > 32*28) return false;
        if(y > 32*28) return false;

        //check if tile is blocked
        for(int i = 0; i < cluedoMap.invalidtiles.length-1; i++){
            if(cluedoMap.invalidtiles[i].x == x && cluedoMap.invalidtiles[i].y == y) return false;
        }
       return true;
    }

    public boolean checkIfPlayerIsInRoom(int x, int y){
        for(int i = 0; i < cluedoMap.roomTiles.length-1; i++){
            if(cluedoMap.roomTiles[i].x == x && cluedoMap.roomTiles[i].y == y) return true;
        }
        return false;
    }


    public static void myCards() {
        mySuspectCard = cardHandOut.handOutSuspect();
        myWeaponCard = cardHandOut.handOutWeapon();
        myRoomCard = cardHandOut.handOutRoom();
    }

    public int getMyWeaponCard(){
        return myWeaponCard;
    }

    public int getMyRoomCard(){
        return myRoomCard;
    }

    public int getMySuspectCard(){
        return mySuspectCard;
    }
}
