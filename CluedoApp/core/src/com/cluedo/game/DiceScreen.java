package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceScreen implements Screen {
    private SpriteBatch batch;
    protected Stage stage;

    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private MainScreen mainScreen;
    private Cluedo cludeo;
    private ConnectionService connectionService;
    private Texture dice1;
    private Texture dice2;
    private Texture dice3;
    private Texture dice4;
    private Texture dice5;
    private Texture dice6;
    private List<Texture> diceList;
    private int dice1Value;
    private int dice2Value;
    private Texture currentDice1;
    private Texture currentDice2;
    private boolean didAlreadyRoll;
    private Toast.ToastFactory toastFactory;
    private BitmapFont font;
    private Toast toast;
    private GameClass game;

    public DiceScreen(GameClass game, MainScreen mainScreen, Cluedo cluedo){
        this.mainScreen = mainScreen;
        this.cludeo = cluedo;
        this.game = game;
        connectionService = ConnectionService.GetInstance();
        atlas = new TextureAtlas("uiskin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
        font = new BitmapFont();
        toastFactory = new Toast.ToastFactory.Builder()
                .font(font)
                .build();
        diceList = new ArrayList<>();

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        viewport = new FitViewport(600,1000, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
        didAlreadyRoll = false;

        dice1 = new Texture(Gdx.files.internal("dice/dice1.png"));
        dice2 = new Texture(Gdx.files.internal("dice/dice2.png"));
        dice3 = new Texture(Gdx.files.internal("dice/dice3.png"));
        dice4 = new Texture(Gdx.files.internal("dice/dice4.png"));
        dice5 = new Texture(Gdx.files.internal("dice/dice5.png"));
        dice6 = new Texture(Gdx.files.internal("dice/dice6.png"));
        diceList.add(dice1);
        diceList.add(dice2);
        diceList.add(dice3);
        diceList.add(dice4);
        diceList.add(dice5);
        diceList.add(dice6);
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
        TextButton mainBtn = new TextButton("Back to Game", skin);

        //If clicked go back to MainMenu
        mainBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(cludeo);
            }
        });

        //Add Text and Buttons to the table
        mainTable.add("Tap the screen to roll the dice!").align(Align.center);
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

        if (toast != null) {
            toast.render(Gdx.graphics.getDeltaTime());
        }

        if (currentDice1 != null && currentDice2 != null) {
            game.batch.begin();
            game.batch.draw(currentDice1, 30, 400);
            game.batch.draw(currentDice2, 320, 400);
            game.batch.end();
        }

        if (Gdx.input.justTouched() && !didAlreadyRoll) {
            Random rand = new Random();
            dice1Value = rand.nextInt(6);
            dice2Value = rand.nextInt(6);
            dice1Value++;
            dice2Value++;
            int diceValue = dice1Value + dice2Value;
            currentDice1 = diceList.get(dice1Value-1);
            currentDice2 = diceList.get(dice2Value-1);
            toast = toastFactory.create("You rolled the value: " + diceValue, Toast.Length.LONG);
            cludeo.setMoves(diceValue);
            didAlreadyRoll = true;
        }
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
