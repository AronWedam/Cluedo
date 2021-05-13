package com.cluedo.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;


public class Dice implements Screen {

    int sum=0;
    int numberDice=1;
    int sideDice=6;
    Random random=new Random();
    SpriteBatch batch;
    Texture img;


    public Dice(int num, int sideNum) {
        this.numberDice = num;
        this.sideDice = sideNum;
    }

    public int randomDiceValue(){
        /*
         nextInt method to get a random int value between 0 and specified value
         drawn from random number generator
        */

        return random.nextInt(sideDice)+1;
    }

    public int roll(){

        for (int i = 0; i < numberDice; i++) {

            sum += randomDiceValue();
        }
        return sum;
    }


    @Override
    public void show() {
        batch=new SpriteBatch();
        img=new Texture("dice_transparent.png");


        Button RollDice = new TextButton("Roll Dice", skin);


        RollDice.setSize(100, 50);
        RollDice.setPosition(20, 20);


        RollDice.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO add path to game
            }
        });
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
