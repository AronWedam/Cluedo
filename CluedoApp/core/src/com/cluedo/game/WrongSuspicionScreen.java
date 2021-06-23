package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

public class WrongSuspicionScreen implements Screen {
    private Cluedo cluedo;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private MainScreen mainScreen;
    private ConnectionService connectionService;
    private String weapon;
    private String suspect;
    private String room;

    public WrongSuspicionScreen(Cluedo cluedo, MainScreen mainScreen, String weapon, String suspect, String room){
        this.cluedo = cluedo;
        this.mainScreen = mainScreen;
        connectionService = ConnectionService.GetInstance();
        this.weapon = weapon;
        this.suspect = suspect;
        this.room = room;

        atlas = new TextureAtlas("uiskin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        viewport = new FitViewport(600,1000, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }

    @Override
    public void show() {
        //Stage controls the input
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table(skin);
        mainTable.setFillParent(true);
        mainTable.align(Align.top);
        TextButton cluedoBtn = new TextButton("Back to the Game", skin);

        //Add Text and Buttons to the table
        mainTable.add("WRONG SUSPICION").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add("You guessed the following things correctly:").align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add("Weapon: " + (weapon.equals(connectionService.getWeapon()))).align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add("Suspect: " + (suspect.equals(connectionService.getSuspect()))).align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add("Room: " + (room.equals(connectionService.getRoom()))).align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add(" ");
        mainTable.row().colspan(2);
        mainTable.add(cluedoBtn).size(200, 50).align(Align.center);

        //If clicked go back to CluedoGame
        cluedoBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(cluedo);
            }
        });

        //Add table to stage
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
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

    }
}
