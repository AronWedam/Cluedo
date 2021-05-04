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

    public int x,y;
    public int dx, dy;
    public int width, height;
    public double area;

    public Player(Texture texture){
        this.texture = texture;
        width = 1150;
        height = 500;
        area = width*height;
        x = Gdx.graphics.getWidth()/2;
        y = Gdx.graphics.getHeight()/2;
        dx = 5;
        dy = 5;

    }

    public void render(OrthographicCamera camera, SpriteBatch batch, float x, float y, float width, float height){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture,x,y,width,height);
        batch.end();
    }


    public void setPos(int x, int y){
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
}
