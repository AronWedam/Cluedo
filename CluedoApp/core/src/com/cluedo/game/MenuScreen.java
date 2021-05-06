package com.cluedo.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.xml.soap.Text;

import sun.security.pkcs11.wrapper.Constants;

//Source: https://stackoverflow.com/questions/32451921/how-to-create-libgdx-main-menu-screen
// How to set up the classes and screens: https://mfg.fhstp.ac.at/development/erste-schritte-mit-libgdx-android/
    public class MenuScreen implements Screen{


        private SpriteBatch batch;
        protected Stage stage;
        private Viewport viewport;
        private OrthographicCamera camera;

        private TextureAtlas atlas;
        protected Skin skin;

        public MenuScreen(){


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

            //Stage controls the input
            Gdx.input.setInputProcessor(stage);

            //Create Table
            Table mainTable = new Table();
            mainTable.setFillParent(true);
            mainTable.top();

            //Create Buttons
            TextButton startBtn = new TextButton("Start Game",skin);
            TextButton rulesBtn= new TextButton("Rules", skin);
            TextButton optionsBtn = new TextButton("Options",skin);
            TextButton exitBtn = new TextButton("Exit Game",skin);

            //Add listeners to buttons
            startBtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //TODO add path to game
                }
            });

            rulesBtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new RulesScreen());
                }
            });

            //If clicked exit the game
            exitBtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });

            //If clicked go to Options menu
            optionsBtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());}
            });

            //Add Buttons to the table
            mainTable.add(startBtn);
            mainTable.add(rulesBtn);
            mainTable.add(optionsBtn);
            mainTable.add(exitBtn);

            //Add table to stage
            stage.addActor(mainTable);

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
        }
    }

