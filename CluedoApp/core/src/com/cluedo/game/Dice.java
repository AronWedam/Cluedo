
        package com.cluedo.game;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.InputProcessor;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.Sprite;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.Skin;
        import com.badlogic.gdx.utils.viewport.FitViewport;
        import com.badlogic.gdx.utils.viewport.Viewport;
        import com.cluedo.game.network.ConnectionService;

        import java.util.Random;

public class Dice implements Screen, InputProcessor {
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
    int numberDice = 2;
    int sideDice = 6;
    Random random = new Random();
    int sum = 0;
    int diceOneValue, diceTwoValue;
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private TextureAtlas atlas;


    public Dice(GameClass game) {
        gameClass = game;
        //this.mainScreen = mainScreen;
        connectionService = ConnectionService.GetInstance();
        atlas = new TextureAtlas("uiskin.atlas");
        skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        viewport = new FitViewport(600, 1000, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }

    @Override
    public void show() {

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batchDice1 = new SpriteBatch();
        batchDice2 = new SpriteBatch();

        textureDice1 = new Texture(Gdx.files.internal("dice_1.png"));
        spriteDice1 = new Sprite(textureDice1, 0, 0, 64, 64);
        spriteDice1.setPosition(50, 200);
        spriteDice1.setSize(128, 128);

        textureDice2 = new Texture(Gdx.files.internal("dice_4.png"));
        spriteDice2 = new Sprite(textureDice2, 0, 0, 64, 64);
        spriteDice2.setPosition(185, 200);
        spriteDice2.setSize(128, 128);

        viewport = new FitViewport(400, 800, camera);
        viewport.apply();

        stage = new Stage(viewport, batchFont);
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
        batchDice1.dispose();
        batchDice2.dispose();
        batchFont.dispose();
        textureDice1.dispose();
        textureDice2.dispose();
    }

    public Dice(int num, int sideNum) {
        this.numberDice = num;
        this.sideDice = sideNum;
    }

    public int randomDiceValue() {
        /*
         nextInt method to get a random int value between 0 and specified value
         drawn from random number generator
        */
        return random.nextInt(sideDice) + 1;
    }

    public int roll() {

        for (int i = 0; i < numberDice; i++) {

            sum += randomDiceValue();
        }

        return sum;
    }


    public int getDiceOneValue() {
        return diceOneValue;
    }

    public int getDiceTwoValue() {
        return diceOneValue;
    }

    public void setDiceOneValue(int diceOneValue) {
        this.diceOneValue = diceOneValue;
    }

    public void setDiceTwoValue(int diceTwoValue) {
        this.diceTwoValue = diceTwoValue;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;

    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
        }
    }
