package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class StartScreen implements Screen {

    final GameClass game;
    OrthographicCamera camera;
    private Texture Logo1;
    private MainScreen mainScreen;

    public StartScreen(final GameClass game, MainScreen mainScreen){
        this.game = game;
        this.mainScreen = mainScreen;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 1000);
        camera.rotate(0);

        Logo1 = new Texture(Gdx.files.internal("CLUEDO.png"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(Logo1,50,450);
        game.font.draw(game.batch, "Welcome to CLUEDO!!! ", 230, 500);
        game.font.draw(game.batch, "Tap anywhere to begin!", 230, 450);
        Gdx.gl.glClearColor(53/255f, 28/255f, 67/255f, 1);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            mainScreen.setScreen(new MenuScreen(mainScreen, game));
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
    public void dispose() {
    }
}

