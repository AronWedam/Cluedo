package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {

    SpriteBatch batch;

    Texture texture;

    private int x,y;

    private CluedoMap cluedoMap;

    public Player(Texture texture, CluedoMap cluedoMap){
        this.texture = texture; this. cluedoMap = cluedoMap;
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
        //Make Sure it keeps inside Gameboard
        if(x < 0) return;
        if(y < 0) return;

        if(x > 32*28) return;
        if(y > 32*28) return;

        int a = (int) cluedoMap.mapconstants[8][0].x;

        //first wall collision detection
        if(x == (int) cluedoMap.mapconstants[8][0].x && y == (int) cluedoMap.mapconstants[8][0].y) return;
        if(x == (int) cluedoMap.mapconstants[8][1].x && y == (int) cluedoMap.mapconstants[8][1].y) return;
        if(x == (int) cluedoMap.mapconstants[8][2].x && y == (int) cluedoMap.mapconstants[8][2].y) return;
        if(x == (int) cluedoMap.mapconstants[8][3].x && y == (int) cluedoMap.mapconstants[8][3].y) return;
        if(x == (int) cluedoMap.mapconstants[8][4].x && y == (int) cluedoMap.mapconstants[8][4].y) return;
        if(x == (int) cluedoMap.mapconstants[8][5].x && y == (int) cluedoMap.mapconstants[8][5].y) return;

        this.x = x;
        this.y = y;
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
}
