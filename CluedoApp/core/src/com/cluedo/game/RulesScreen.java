package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RulesScreen implements Screen {

    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    BitmapFont font = new BitmapFont();

    private TextureAtlas atlas;
    protected Skin skin;

    public RulesScreen(){

        atlas = new TextureAtlas("skin.atlas");
        skin = new Skin(Gdx.files.internal("skin.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        viewport = new FitViewport(400,800, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }

    @Override
    public void show() {
        Label title = new Label("Rules", skin, "big-black");
        title.setAlignment(Align.center);
        title.setY(780);
        title.setWidth(200);
        stage.addActor(title);
        String text= "Go from room to room with your detective colleagues and interpret the clues correctly " +
                "until one of you manages to solve the murder case. In each room, you suspect all players again, " +
                "until the circle of suspects shrink" +
                "You can use the dice to determine who is allowed to start. " +
                "Whoever rolls the highest value with both dice may start." +'\n'+
                "Whoever’s turn has to complete three phases one after the other:" +'\n' +
                "1. Roll the dice. Move and enter a Room when possible" +"\n" +
                "2. Question your teammates and collect clues"+"\n" +
                "3. Cross the clues you received off on the notepad and end your turn";

        batch.begin();
        font.draw(batch, text, 20,600);
        batch.end();

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
        font.dispose();
    }
}
