package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cluedo.game.network.ConnectionService;


//TODO ACCUSATION AND HOW TO DO THAT

public class AccusationScreen implements Screen {
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;
    private final Murderer murderer;


    private final CheckBox cBMissScarlett   =   new CheckBox("MissScarlett", skin);
    private final CheckBox cBColonelMustard =   new CheckBox("ColonelMustard", skin);
    private final CheckBox cBMrsWhite       =   new CheckBox("MrsWhite", skin);
    private final CheckBox cBReverend       =   new CheckBox("Reverend", skin);
    private final CheckBox cBMrsPeacock     =   new CheckBox("MrsPeacock", skin);
    private final CheckBox cBProfessorPlum  =   new CheckBox("ProfessorPlum", skin);

    private final CheckBox cBWeaponKnife    =   new CheckBox("Knife", skin);
    private final CheckBox cBWeaponRope     =   new CheckBox("Rope", skin);
    private final CheckBox cBWeaponGun      =   new CheckBox("Gun", skin);
    private final CheckBox cBWeaponPoison   =   new CheckBox("Poison", skin);
    private final CheckBox cBWeaponPipe     =   new CheckBox("Pipe", skin);
    private final CheckBox cBWeaponCandle   =   new CheckBox("Candle", skin);

    private final CheckBox cBRoomEntrance   =   new CheckBox("Entrance", skin);
    private final CheckBox cBRoomBedroom     =   new CheckBox("Bedroom", skin); //
    private final CheckBox cBRoomDining     =   new CheckBox("Dining", skin);
    private final CheckBox cBRoomKitchen    =   new CheckBox("Kitchen", skin);
    private final CheckBox cBRoomGuest   =   new CheckBox("Guestroom", skin); //
    private final CheckBox cBRoomMusicroom  =   new CheckBox("Musicroom", skin);
    private final CheckBox cBRoomBathroom   =   new CheckBox("Bathroom ", skin); //
    private final CheckBox cBRoomStudy      =   new CheckBox("Study", skin);
    private final CheckBox cBRoomLibrary    =   new CheckBox("Library", skin);

//BEDRROOM
    //GUESTROOM
    //BATHROOM

    public AccusationScreen(MainScreen mainScreen, GameClass game, Murderer murderer){
        this.mainScreen = mainScreen;
        this.gameClass = game;
        this.murderer = murderer;
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


        //If clicked go back to MainMenu
        mainBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(new MenuScreen(mainScreen, gameClass));
            }
        });


        //Add Text and Buttons to the table
        mainTable.add("Who do you want to accuse").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add(""+'\n').align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add(cBMissScarlett);
        mainTable.row();

        mainTable.add(cBColonelMustard);
        mainTable.row();

        mainTable.add(cBMrsWhite);
        mainTable.row();

        mainTable.add(cBReverend);
        mainTable.row();

        mainTable.add(cBMrsPeacock);
        mainTable.row();

        mainTable.add(cBProfessorPlum);
        mainTable.row();


        mainTable.add("What weapon did they use").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add(""+'\n').align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add(cBWeaponKnife);
        mainTable.row();

        mainTable.add(cBWeaponRope);
        mainTable.row();

        mainTable.add(cBWeaponGun);
        mainTable.row();

        mainTable.add(cBWeaponPoison);
        mainTable.row();

        mainTable.add(cBWeaponPipe);
        mainTable.row();

        mainTable.add(cBWeaponCandle);
        mainTable.row();


        /*
        public final CheckBox cBRoomEntrance   =   new CheckBox("Entrance", skin);
    public final CheckBox cBRoomGarden     =   new CheckBox("Garden", skin);
    public final CheckBox cBRoomDining     =   new CheckBox("Dining", skin);
    public final CheckBox cBRoomKitchen    =   new CheckBox("Kitchen", skin);
    public final CheckBox cBRoomBallroom   =   new CheckBox("Ballroom", skin);
    public final CheckBox cBRoomMusicroom  =   new CheckBox("Musicroom", skin);
    public final CheckBox cBRoomGameroom   =   new CheckBox("Gameroom ", skin);
    public final CheckBox cBRoomStudy      =   new CheckBox("Study", skin);
    public final CheckBox cBRoomLibrary    =   new CheckBox("Library", skin);
    public final CheckBox cBRoomNEEDSName  =   new CheckBox("NEEDS NAME", skin);
         */

        mainTable.add("What room was the person killed in").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add(""+'\n').align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add(cBRoomEntrance);
        mainTable.row();

        mainTable.add(cBRoomBedroom);
        mainTable.row();

        mainTable.add(cBRoomDining);
        mainTable.row();

        mainTable.add(cBRoomKitchen);
        mainTable.row();

        mainTable.add(cBRoomGuest);
        mainTable.row();

        mainTable.add(cBRoomMusicroom);
        mainTable.row();

        mainTable.add(cBRoomBathroom);
        mainTable.row();

        mainTable.add(cBRoomStudy);
        mainTable.row();

        mainTable.add(cBRoomLibrary);
        mainTable.row();

        mainTable.add(cBRoomLibrary);
        mainTable.row();


        mainTable.add(mainBtn).size(100, 50).align(Align.left);
        mainTable.row().colspan(2);

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

    }

    public boolean isActuallyTheMurderer(CheckBox cbAccusedWeapon, CheckBox cBAccusedPerson){
        if(cbAccusedWeapon.toString() == murderer.getMurdererWeaponString()){
            if(cBAccusedPerson.toString() == murderer.getMurdererSuspectString()){
                //if()
            }
        }
        return false;
    }


}
