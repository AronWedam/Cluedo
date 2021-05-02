package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import javax.swing.text.View;

public class Rules implements Screen {

    Stage stage;

    GameClass game;
    private OrthographicCamera camera;
    BitmapFont font=new BitmapFont();
    private SpriteBatch batch;


    public Rules(GameClass gameClass) {
        game=gameClass;
        stage=new Stage(new ScreenViewport());

        Label title = new Label("Rules", GameClass.gameSkin, "big-black");
        title.setAlignment(Align.center);
        //title.setY();
        //title.setWidth();
        stage.addActor(title);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1); //white
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Zieh gemeinsam mit deinen Detektivkollegen von Zimmer zu Zimmer und " +
                "interpretiere die Hinweise richtig, bis es einem von euch gelingt, " +
                "den Mordfall zu lösen. In jedem Raum verdächtigt ihr erneut alle Mitspieler, " +
                "bis sich der Kreis der Verdächtigen schließlich verkleinert.", 100, 700);

        font.draw(batch, "Wer anfangen darf, ermittelt ihr mithilfe der Würfel. Der, " +
                "der den höchsten Wert mit beiden Würfeln würfelt, darf beginnen."+
                "\n"+
                        "Wer an der Reihe ist, muss nacheinander drei Phasen beenden:" +
                        "\n"+
                        "1. Würfeln. ziehen und Raum betreten" +
                        "2. Befrage deine Mitspieler und hole Dir hinweise" +
                        "3. Kreuze die Hinweise auf dem Notizzettel an und beenden deinen Zug"+
                "\n", 100, 500);

        batch.end();


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
        batch.dispose();
        font.dispose();
        stage.dispose();
    }
}
