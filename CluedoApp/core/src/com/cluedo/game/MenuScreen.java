package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cluedo.game.network.ConnectionService;

//Source: https://stackoverflow.com/questions/32451921/how-to-create-libgdx-main-menu-screen
// How to set up the classes and screens: https://mfg.fhstp.ac.at/development/erste-schritte-mit-libgdx-android/
public class MenuScreen implements Screen{
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;

    public MenuScreen(MainScreen mainScreen, GameClass game){
        gameClass = game;
        this.mainScreen = mainScreen;
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
    }

    @Override
    public void show() {
        //Stage controls the input
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table(skin);
        mainTable.setFillParent(true);
        mainTable.align(Align.center);

        //Create Buttons
        final TextButton startBtn = new TextButton("Start Game", skin);
        TextButton optionsBtn = new TextButton("Options", skin);
        TextButton rulesBtn= new TextButton("Rules", skin);
        TextButton diceBtn= new TextButton("Dice", skin);
        TextButton exitBtn = new TextButton("Exit Game", skin);
        final TextField textFieldUsername = new TextField("", skin);
        textFieldUsername.setSize(250, 50);

        //Add listeners to buttons
        startBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    startBtn.setDisabled(true);
                    Thread RegisterThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            connectionService.RegisterForGame(textFieldUsername.getText());
                        }
                    });
                    RegisterThread.start();
                    RegisterThread.join();

                    Thread CheckRegisterThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                int responseCode;

                                do {
                                    responseCode = connectionService.CheckRegistration();
                                    Thread.sleep(500);
                                } while(responseCode != 200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    CheckRegisterThread.start();
                    CheckRegisterThread.join();
                    mainScreen.setScreen(new Cluedo(gameClass));
                    startBtn.setDisabled(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                //((Game)Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());
            }
        });
        //If clicked go to Rules
        rulesBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(new RulesScreen(gameClass));
            }
        });




        //Add Buttons to the table
        mainTable.add("Please enter a username to start the game!").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add(textFieldUsername).size(300, 50);
        mainTable.add(startBtn).size(100, 50);
        mainTable.row().colspan(2);
        mainTable.add(rulesBtn).size(100,50).align(Align.left);
        mainTable.add(diceBtn).size(100,50).align(Align.left);
        mainTable.add(optionsBtn).size(100, 50).align(Align.left);
        mainTable.add(exitBtn).size(100, 50).align(Align.left);

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
        skin.dispose();
        atlas.dispose();
    }
}