package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cluedo.game.network.ConnectionService;

import java.util.HashMap;

public class DiceScreenNew implements Screen {
    private SpriteBatch batch;
    protected Stage stage;

    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;
    BitmapFont font = new BitmapFont();
    private Texture dice1;
    private Texture dice2;
    private Texture dice3;
    private Texture dice4;
    private Texture dice5;
    private Texture dice6;
    private int dice1Value;
    private int dice2Value;
    Dice dice;
    private Sprite sprite;


    public DiceScreenNew(GameClass game, MainScreen mainScreen){
        this.mainScreen = mainScreen;
        gameClass = game;
        connectionService = ConnectionService.GetInstance();
        atlas = new TextureAtlas("uiskin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        viewport = new FitViewport(600,1000, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);

        dice1 = new Texture(Gdx.files.internal("dice/dice1.png"));
        dice2 = new Texture(Gdx.files.internal("dice/dice2.png"));



    }

    @Override
    public void show() {
        //Stage controls the input
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table(skin);
        mainTable.setFillParent(true);
        mainTable.align(Align.top);

        //Create Buttons
        TextButton mainBtn = new TextButton("Back to Main", skin);

        //If clicked go back to MainMenu
        mainBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(new MenuScreen(mainScreen, gameClass));
            }
        });

        //Add Text and Buttons to the table
        mainTable.add("DICE TEST").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add(mainBtn).size(100, 50).align(Align.left);

        //Add table to stage
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        gameClass.batch.begin();
        gameClass.batch.draw(dice1, 30, 400);
        gameClass.batch.draw(dice2, 320, 400);


        gameClass.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
        skin.dispose();
        atlas.dispose();
        batch.dispose();
    }
}
