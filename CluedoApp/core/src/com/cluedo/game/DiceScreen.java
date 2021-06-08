package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cluedo.game.network.ConnectionService;

import java.util.HashMap;
import java.util.Random;

//made with help of tutorial https://www.codeandweb.com/texturepacker/tutorials/libgdx-physics
// and https://gamefromscratch.com/libgdx-tutorial-3b-simple-animation/

public class DiceScreen implements Screen, InputProcessor {

    private Sprite dice1;
    private Sprite dice2;
    private Viewport viewport;
    private Stage stage;
    private BitmapFont font;
    private Skin skin;
    int numberDice=2;
    int sideDice=6;
    Random random=new Random();
    int sum=0;
    int diceOneValue, diceTwoValue;
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private TextureAtlas atlas;
    private TextureAtlas textureAtlas;
    private ExtendViewport viewport1;
    private Animation animation;
    private float elapsedTime = 0;
    Dice dice;

    //to store sprites
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();



    public DiceScreen(GameClass game, MainScreen mainScreen){
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
        textureAtlas=new TextureAtlas("sprites.txt");

        addSprites();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();

        drawSprite("dice1", 40, 450);
        drawSprite("dice6", 304, 450);
        gameClass.font.draw(gameClass.batch, "Tap to roll", 230, 450);

        batch.end();

        int dice1Value;
        int dice2Value;

        if (Gdx.input.isTouched()) {
            diceAnimation();
            dice1Value = dice.randomDiceValue();
            dice2Value = dice.randomDiceValue();

            switch (dice1Value) {
                case 1:
                    drawSprite("dice1.png", 40, 450);
                case 2:
                    drawSprite("dice2.png", 40, 450);
                case 3:
                    drawSprite("dice3.png", 40, 450);
                case 4:
                    drawSprite("dice4.png", 40, 450);
                case 5:
                    drawSprite("dice5.png", 40, 450);
                case 6:
                    drawSprite("dice6.png", 40, 450);

            }
            switch (dice2Value) {
                case 1:
                    drawSprite("dice1.png", 304, 450);
                case 2:
                    drawSprite("dice2.png", 304, 450);
                case 3:
                    drawSprite("dice3.png", 304, 450);
                case 4:
                    drawSprite("dice4.png", 304, 450);
                case 5:
                    drawSprite("dice5.png", 304, 450);
                case 6:
                    drawSprite("dice6.png", 304, 450);
            }
        }

        stage.act();
        stage.draw();
    }
    //https://www.codeandweb.com/texturepacker/tutorials/libgdx-physics
    //method to make drawing spites easier
    private void drawSprite(String name, float x, float y){
        Sprite sprite=sprites.get(name);
        sprite.setPosition(x,y);
        sprite.draw(batch);
    }
    //method to add sprites to HashMap
    private void addSprites(){
        Array<TextureAtlas.AtlasRegion> regions= textureAtlas.getRegions();

        for (TextureAtlas.AtlasRegion region : regions){
            Sprite sprite= textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }
    public void diceAnimation(){
        textureAtlas=new TextureAtlas(Gdx.files.internal("dice/diceSprite.png"));

        //need to pass amount of time per frame, using all of the regions available (dice sides 1-6)
        animation=new Animation(1/15f, textureAtlas.getRegions());

        batch.begin();
        //drawing the current frame from the animation to the screen
        //true tells to loop the animation
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw((Texture) animation.getKeyFrame(elapsedTime, true),40,450);
        batch.draw((Texture) animation.getKeyFrame(elapsedTime, true),304,450);
        batch.end();
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
        textureAtlas.dispose();
        sprites.clear();
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
