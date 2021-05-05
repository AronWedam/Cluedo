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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

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

        game.batch.setProjectionMatrix(camera.combined);

        player.render(camera, game.batch, player.getX(), player.getY(), piece.width, piece.height);


        //Single Touch enables player Movement for 1 Tile
        if(Gdx.input.justTouched()) {
            //TODO first if doesn't work properly
            //Check if Player figure is touched - doesn't work at all
            if(Gdx.input.getX(0) >= player.getX()-1 || Gdx.input.getX(0) <= player.getX()+1 && Gdx.input.getY(0) >= player.getY()-1 || Gdx.input.getY(0) <= player.getY()+1) {
                //Save Coordinates of Touched Area
                if(Gdx.input.justTouched()) {
                    Vector3 touchPos = new Vector3();
                    touchPos.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);
                    camera.unproject(touchPos);

                    //Make move in touched direction#
                    // TODO can't go left-down - don't know why
                    if(touchPos.x > player.getX() || touchPos.y > player.getY()) {
                        if(touchPos.x > player.getX()) {
                            player.setPos((int) player.getX() + 32, player.getY());
                            if(touchPos.y > player.getY()){
                                player.setPos((int) player.getX(), player.getY()+32);
                            }
                        } else
                            if(touchPos.y > player.getY()){
                                player.setPos((int) player.getX(), player.getY()+32);
                        }
                        if(touchPos.x < player.getX() || touchPos.y < player.getY()) {
                            if (touchPos.x < player.getX()) {
                                player.setPos((int) player.getX() - 32, player.getY());
                                if (touchPos.y < player.getY()) {
                                    player.setPos((int) player.getX(), player.getY() - 32);
                                }
                            } else if (touchPos.y < player.getY()) {
                                player.setPos((int) player.getX(), player.getY() - 32);
                            }
                        }
                    }
                }
            }
        }
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
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX/2, deltaY/2);
        camera.update();
        return false;
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
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
