package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cluedo.game.network.ConnectionService;

public class MenuScreen implements Screen{
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas = new TextureAtlas("uiskin.atlas");
    protected Skin skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;

    //Create Buttons
    final Label lblTitle = new Label("Please enter a username to start the game!", skin);
    final TextButton startBtn = new TextButton("Start Game", skin);
    final TextButton rulesBtn= new TextButton("Rules", skin);
    final TextButton exitBtn = new TextButton("Exit Game", skin);
    final TextField textFieldUsername = new TextField("", skin);
    final Label lblLoading = new Label("Waiting for other Players...", skin);
    private BitmapFont font;
    private Toast.ToastFactory toastFactory;
    private Toast toast;

    public MenuScreen(MainScreen mainScreen, GameClass game){
        gameClass = game;
        this.mainScreen = mainScreen;
        connectionService = ConnectionService.GetInstance();
        font = new BitmapFont();
        font.getData().setScale(2,2);

        toastFactory = new Toast.ToastFactory.Builder()
                .font(font)
                .build();

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
        final Table mainTable = new Table(skin);
        mainTable.setFillParent(true);
        mainTable.align(Align.center);
        textFieldUsername.setSize(250, 50);

        //Add listeners to buttons
        startBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (textFieldUsername.getText().length() > 0 ) {
                    swapView(false);
                    mainTable.row().colspan(2);
                    mainTable.add(lblLoading).size(300, 50);

                    try {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                connectionService.RegisterForGame(textFieldUsername.getText());

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            int responseCode;

                                            do {
                                                responseCode = connectionService.CheckRegistration();
                                                Thread.sleep(500);
                                            } while(responseCode != 200);

                                            Gdx.app.postRunnable(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mainScreen.setScreen(new Cluedo(gameClass, mainScreen));
                                                }
                                            });
                                        } catch (InterruptedException ex) {
                                            swapView(true);
                                            Gdx.app.log("Thread-Exception", ex.getMessage());
                                            Thread.currentThread().interrupt();
                                        }
                                    }
                                }).start();
                            }
                        }).start();
                    } catch (Exception ex) {
                        swapView(true);
                        Gdx.app.log("Thread-Exception", ex.getMessage());
                        Thread.currentThread().interrupt();
                    }
                } else {
                    toast = toastFactory.create("Please enter a username", Toast.Length.LONG);
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

        //If clicked go to Rules
        rulesBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(new RulesScreen(gameClass, mainScreen));
            }
        });

        //Add Buttons to the table
        mainTable.add(lblTitle).align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add(textFieldUsername).size(300, 50);
        mainTable.add(startBtn).size(100, 50);
        mainTable.row().colspan(2);
        mainTable.add(rulesBtn).size(100,50).align(Align.left);
        mainTable.add(exitBtn).size(100, 50).align(Align.left);

        //Add table to stage
        stage.addActor(mainTable);
    }

    public void swapView(boolean visibility) {
        lblTitle.setVisible(visibility);
        startBtn.setVisible(visibility);
        exitBtn.setVisible(visibility);
        rulesBtn.setVisible(visibility);
        textFieldUsername.setVisible(visibility);
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