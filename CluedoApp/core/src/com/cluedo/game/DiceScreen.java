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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cluedo.game.network.ConnectionService;

import java.util.HashMap;
import java.util.Random;

//made with help of tutorial https://www.codeandweb.com/texturepacker/tutorials/libgdx-physics

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

    //to store sprites
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();



    public DiceScreen(GameClass game){
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

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        drawSprite("dice1", 40, 450);
        drawSprite("dice6", 304, 450);

        batch.end();


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
