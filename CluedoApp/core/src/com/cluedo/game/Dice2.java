package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Dice2 implements Screen {

    private SpriteBatch batchDice1;
    private SpriteBatch batchDice2;
    private SpriteBatch batchFont;
    private Texture textureDice1;
    private Sprite spriteDice1;
    private Sprite spriteDice2;
    private Texture textureDice2;
    private Viewport viewport;
    private Stage stage;
    private BitmapFont font;
    private Skin skin;

    @Override
    public void show() {

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batchDice1=new SpriteBatch();
        batchDice2=new SpriteBatch();

        textureDice1 = new Texture(Gdx.files.internal("dice_1.png"));
        spriteDice1 = new Sprite(textureDice1, 0,0,64,64);
        spriteDice1.setPosition(50,200);
        spriteDice1.setSize(128,128);

        textureDice2 = new Texture(Gdx.files.internal("dice_4.png"));
        spriteDice2 = new Sprite(textureDice2, 0,0,64,64);
        spriteDice2.setPosition(185,200);
        spriteDice2.setSize(128,128);

        viewport=new FitViewport(400, 800, camera);
        viewport.apply();

        stage=new Stage(viewport, batchFont);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batchDice1.begin();
        spriteDice1.draw(batchDice1);
        batchDice1.end();

        batchDice2.begin();
        spriteDice2.draw(batchDice2);
        batchDice2.end();

        /*Button RollDice = new TextButton("Roll Dice", skin);
        RollDice.setSize(100, 50);
        RollDice.setPosition(20, 20);
         */
        batchFont.begin();
        font.draw(batchFont, "Tap to Roll", 50, 100);
        batchFont.end();
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
        batchDice1.dispose();
        batchDice2.dispose();
        batchFont.dispose();
        textureDice1.dispose();
        textureDice2.dispose();
    }
}
