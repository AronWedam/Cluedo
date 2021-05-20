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

import com.badlogic.gdx.scenes.scene2d.InputListener;
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

    Texture img1;
    Texture img2;
    Texture img3;
    Texture img4;
    Texture img5;
    Texture img6;

    //TextureAtlas atlas;
    Skin skin;

    int diceOneValue, diceTwoValue;

    //public static final String DICE_IMG_PATH="dice_1.png";
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
    public void changeDiceValue(){
        if(sum==1){
            img1 = new Texture(Gdx.files.internal("dice_1.png"));
        } else if(sum==2) {
            img1 = new Texture(Gdx.files.internal("dice_2.png"));
        } else if(sum==3){
            img1 = new Texture(Gdx.files.internal("dice_3.png"));
        }else if(sum==4){
            img1 = new Texture(Gdx.files.internal("dice_4.png"));
        }else if(sum==5){
            img1 = new Texture(Gdx.files.internal("dice_5.png"));
        }else if(sum==6){
            img1 = new Texture(Gdx.files.internal("dice_6.png"));
        }
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
        img1 = new Texture(Gdx.files.internal("dice_1.png"));
        img2 = new Texture(Gdx.files.internal("dice_2.png"));
        img3 = new Texture(Gdx.files.internal("dice_3.png"));
        img4 = new Texture(Gdx.files.internal("dice_4.png"));
        img5 = new Texture(Gdx.files.internal("dice_5.png"));
        img6 = new Texture(Gdx.files.internal("dice_6.png"));
        sprite=new Sprite(texture);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);

        float xDice1 = 150;
        float yDice1 = 20;
        float xDice2 = 150;
        float yDice2 = 180;
        int srcX = 100;
        int srcY = 30;
        int srcWidth = 140;
        int srcHeight = 140;

        batch.begin();
        batch.draw(img1, xDice1, yDice1, srcX, srcY, srcWidth, srcHeight);
        batch.draw(img2, xDice2, yDice2, srcX, srcY, srcWidth, srcHeight);
        batch.end();

        Button RollDice = new TextButton("Roll Dice", skin);
        RollDice.setSize(100, 50);
        RollDice.setPosition(20, 20);


        RollDice.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Example", "touch started at (" + x + ", " + y + ")");
                return false;
            }
        });

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
        img1.dispose();
        img2.dispose();
        texture.dispose();
    }
}
