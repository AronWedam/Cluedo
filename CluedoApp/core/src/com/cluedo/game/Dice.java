package com.cluedo.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.net.Socket;
import java.util.Random;

import javax.xml.soap.Text;


public class Dice implements Screen {

    int sum=0;
    int numberDice=1;
    int sideDice=6;
    Random random=new Random();

    Texture img;
    TextureAtlas atlas;
    Skin skin;


    int diceOneValue, diceTwoValue;


    public static final String DICE_IMG_PATH="dice_1.png";
    SpriteBatch batch = new SpriteBatch();
    Texture texture;
    Sprite sprite;




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

    public int getDiceOneValue(){
        return diceOneValue;
    }
    public int getDiceTwoValue() {
        return diceOneValue;
    }
    public void setDiceOneValue(int diceOneValue) {
        this.diceOneValue=diceOneValue;
    }
    public void setDiceTwoValue(int diceTwoValue) {
        this.diceTwoValue=diceTwoValue;
    }



    @Override
    public void show() {
        batch=new SpriteBatch();
        texture = new Texture(Gdx.files.internal("dice_1.png"));
        sprite=new Sprite(texture);



        Button RollDice = new TextButton("Roll Dice", skin);

        RollDice.setSize(100, 50);
        RollDice.setPosition(20, 20);


        RollDice.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // call animation
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);

        batch.begin();
        sprite.draw(batch);
        batch.end();


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
        batch.dispose();
        img.dispose();
        texture.dispose();
    }
}
