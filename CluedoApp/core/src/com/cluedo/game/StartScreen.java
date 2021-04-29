package com.cluedo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.xml.soap.Text;

import sun.security.pkcs11.wrapper.Constants;

public class StartScreen implements Screen{

    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

    private TextureAtlas atlas;
    protected Skin skin;

    public StartScreen(){

        atlas = new TextureAtlas("skin.atlas");
        skin = new Skin(Gdx.files.internal("skin.json"),atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(800,800,camera);

        stage = new Stage(viewport, batch);
    }



    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        //Create Buttons
        TextButton startBtn = new TextButton("Start Game",skin);
        TextButton optionsBtn = new TextButton("Options",skin);
        TextButton exitBtn = new TextButton("Exit Game",skin);

        //Add listeners to buttons
        startBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO add path to game
            }
        });
        //If clicked exit the game
        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        //If clicked go to Options menu
        optionsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());}
        });

        //Add Buttons to the table
        mainTable.add(startBtn);
        mainTable.add(optionsBtn);
        mainTable.add(exitBtn);

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
