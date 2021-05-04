package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cluedo implements Screen, GestureDetector.GestureListener{

    private GameClass game;

    Vector2 firstStartPos = new Vector2(320,1);

    private Player player;
    Rectangle piece;


    GestureDetector gestureDetector;

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private TiledMap map;

    float currentZoom;

    Texture gamepieceBlue;


    public Cluedo(final GameClass game){
        this.game = game;

        //create map
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        map = new TmxMapLoader().load("maps/map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        //create gestureDetector
        gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);

        //create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);

        //load images
        gamepieceBlue = new Texture("Gamepiece_blue.png");

        //create a Rectangle to logically represent one player
        piece = new Rectangle();
        piece.x = 321;
        piece.y = 1;
        piece.width = 30;
        piece.height = 30;

        //create the player
        player = new Player(gamepieceBlue);
        player.setPos((int)firstStartPos.x, (int)firstStartPos.y);
    }


    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {

        //clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //set up map and camera
        renderer.setView(camera);
        renderer.render();
        camera.update();

        //
        player.render(camera, game.batch,piece.x, piece.y, piece.width, piece.height);

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
    public void dispose () {
        map.dispose();

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        currentZoom = camera.zoom;
        Gdx.app.log("INFO", "x:" + x + "y:" + y);
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX/2, deltaY/2);
        camera.update();
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log("INFO", "panStop");
        currentZoom = camera.zoom;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        camera.zoom = (initialDistance / distance) * currentZoom;
        camera.update();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
