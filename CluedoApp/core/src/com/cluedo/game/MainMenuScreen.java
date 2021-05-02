package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
        Gdx.input.setInputProcessor(stage);


    }


    @Override
    public void show() {
        Table table = new Table();

        // temporary
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton rules= new TextButton("Game Rules", skin);

        table.add(rules).fillX().uniformX();

        rules.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //gameC.changeScreen(GameClass.Rules);
            }
        });
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
        stage.dispose();
    }
}
