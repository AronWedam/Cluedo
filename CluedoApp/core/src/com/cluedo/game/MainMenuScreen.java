package com.cluedo.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MainMenuScreen implements Screen {

    private SpriteBatch batch;
    protected Stage stage;
    private OrthographicCamera camera;
    private GameClass gameC;

    public MainMenuScreen(GameClass game) {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        gameC=game;
        stage = new Stage (new ScreenViewport());


    }


    @Override
    public void show() {

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

    }
}
