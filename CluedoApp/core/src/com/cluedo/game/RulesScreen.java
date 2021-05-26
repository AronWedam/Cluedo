package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cluedo.game.network.ConnectionService;

import java.awt.Menu;

public class RulesScreen implements Screen {
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;
    BitmapFont font = new BitmapFont();

    public RulesScreen(GameClass game){
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
    }

    @Override
    public void show() {
        //Stage controls the input
        Gdx.input.setInputProcessor(stage);

        //Create Table
        Table mainTable = new Table(skin);
        mainTable.setFillParent(true);
        mainTable.align(Align.top);

        //Create Buttons
        TextButton mainBtn = new TextButton("Back to Main", skin);
        TextButton exitBtn = new TextButton("Exit Game", skin);

        //If clicked go back to MainMenu
        mainBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(new MenuScreen(mainScreen, gameClass));
            }
        });
        //If clicked exit the game
        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });



        //Add Text and Buttons to the table
        mainTable.add("RULES").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add(""+'\n').align(Align.left);
        mainTable.row().colspan(2);
        mainTable.add("Go from room to room with your detective colleagues and"+'\n'+" interpret the clues correctly until one of you manages "
                +'\n'+" to solve the murder case.In each room, you suspect all"+'\n'+"players again, until the circle of suspects shrinks."+'\n').align(Align.left);
        mainTable.row().colspan(2);
        mainTable.add("When it is your turn you have to complete three phases"+'\n'+" one after the other:"+'\n'+
                "1. Roll the dice. Move and enter a Room when possible" +'\n'+"2. Question your teammates and collect clues"+'\n'+
                "3. Cross the clues you received off on the notepad and " +'\n'+ "     end your turn"+'\n').align(Align.left);
        mainTable.row().colspan(2);
        mainTable.add("You may enter rooms by the doors only, but cannot leave"+'\n'+"a room in the same move. This means that once you enter"+'\n'+
                " a room, the moves left on your dice are invalid."+'\n'+'\n'+"Secret passages - enable players to move from certain rooms"+'\n'+
                "to those indicated in one move. This can be done at a player's "+'\n'+"turn without throwing the dice but constitutes a move."+'\n').align(Align.left);
        mainTable.row().colspan(2);
        mainTable.add("When a player reaches a room, that player can immediately " +'\n'+"make a suggestion by calling into that particular "+'\n'+
                "room any other Person and any Weapon. The suggestion "+'\n'+"having been made another player must examine his cards and "+'\n'+
                "if that player is in possession of one or more of those "+'\n'+"mentioned in the suggestion, only one card has to be shown"+'\n'+
                "to the player making the suggestion."+'\n'+"If first player isn't able to show a card, the enquiry passes on to "+'\n'+
                "the next player and so on until one of the cards has been shown "+'\n'+"to the player who made the suggestion."+'\n').align(Align.left);
        mainTable.row().colspan(2);
        mainTable.add("Each player acts in a similar manner, and by a process of "+'\n'+"elimination will eventually discover the three murder cards."+'\n'+
                "When a player is satisfied that he knows the three Murder Cards"+'\n'+"the player can make an accusation. The player then gets to look"+'\n'+
                "at the Murder Cards in the envelope. If it matches the accusation,"+'\n'+" this player has won the game. If not the player lost and is out."+'\n'+
                "The other players are still in the game with a chance to win."+'\n'+'\n').align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add(mainBtn).size(100, 50).align(Align.left);
        mainTable.row().colspan(2);
        mainTable.add(exitBtn).size(100, 50).align(Align.left);

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
        batch.dispose();
    }
}
