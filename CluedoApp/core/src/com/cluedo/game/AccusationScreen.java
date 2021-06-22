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

public class AccusationScreen implements Screen {
    private Cluedo cluedo;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas = new TextureAtlas("uiskin.atlas");;
    protected Skin skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;
    Table mainTable;

    //my accusation
    CheckBox accusedSuspect;
    CheckBox accusedWeapon;
    CheckBox accusedRoom;

    //to see if every category was checked
    boolean suspectChecked = false;
    boolean weaponChecked = false;
    boolean roomChecked = false;


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
    private final CheckBox cBRoomBedroom     =   new CheckBox("Bedroom", skin);
    private final CheckBox cBRoomDining     =   new CheckBox("Dining", skin);
    private final CheckBox cBRoomKitchen    =   new CheckBox("Kitchen", skin);
    private final CheckBox cBRoomGuest   =   new CheckBox("Guestroom", skin);
    private final CheckBox cBRoomMusicroom  =   new CheckBox("Musicroom", skin);
    private final CheckBox cBRoomBathroom   =   new CheckBox("Bathroom ", skin);
    private final CheckBox cBRoomStudy      =   new CheckBox("Study", skin);
    private final CheckBox cBRoomLibrary    =   new CheckBox("Library", skin);


    public AccusationScreen(GameClass game,MainScreen mainScreen, Cluedo cluedo){
        this.mainScreen = mainScreen;
        this.cluedo = cluedo;
        this.gameClass = game;
        connectionService = ConnectionService.GetInstance();


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
        mainTable = new Table(skin);
        mainTable.setFillParent(true);
        mainTable.align(Align.top);

        //Create Buttons
        final TextButton cluedoBtn = new TextButton("Back to Main", skin);
        final TextButton makeAccusationBtn = new TextButton("Express suspicion", skin);
        final TextButton makeFinalAccusationBtn = new TextButton("Accusation", skin);


        //Add Text and Buttons to the table
        mainTable.add("Who do you want to accuse").align(Align.left);
        mainTable.row().align(Align.left);
        mainTable.add("").align(Align.left);
        mainTable.row().align(Align.left);

        mainTable.add(cBMissScarlett).align(Align.left);
        cBMissScarlett.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerSuspect(cBMissScarlett);
            }
        });
        mainTable.row();

        mainTable.add(cBColonelMustard).align(Align.left);
        cBColonelMustard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerSuspect(cBColonelMustard);
            }
        });
        mainTable.row();


        mainTable.add(cBMrsWhite).align(Align.left);
        cBMrsWhite.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerSuspect(cBMrsWhite);
            }
        });
        mainTable.row();

        mainTable.add(cBReverend).align(Align.left);
        cBReverend.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerSuspect(cBReverend);
            }
        });
        mainTable.row();

        mainTable.add(cBMrsPeacock).align(Align.left);
        cBMrsPeacock.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerSuspect(cBMrsPeacock);
            }
        });
        mainTable.row();

        mainTable.add(cBProfessorPlum).align(Align.left);
        cBProfessorPlum.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerSuspect(cBProfessorPlum);
            }
        });
        mainTable.row();


        mainTable.add("").align(Align.left);
        mainTable.row().align(Align.left);
        mainTable.add("What weapon did they use").align(Align.left);
        mainTable.row().align(Align.left);
        mainTable.add("").align(Align.left);
        mainTable.row().align(Align.left);

        mainTable.add(cBWeaponKnife).align(Align.left);
        cBWeaponKnife.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerWeapon(cBWeaponKnife);
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponRope).align(Align.left);
        cBWeaponRope.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerWeapon(cBWeaponRope);
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponGun).align(Align.left);
        cBWeaponGun.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerWeapon(cBWeaponGun);
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponPoison).align(Align.left);
        cBWeaponPoison.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerWeapon(cBWeaponPoison);
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponPipe).align(Align.left);
        cBWeaponPipe.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerWeapon(cBWeaponPipe);
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponCandle).align(Align.left);
        cBWeaponCandle.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerWeapon(cBWeaponCandle);
            }
        });
        mainTable.row();


        mainTable.add("").align(Align.left);
        mainTable.row().align(Align.left);
        mainTable.add("What room was the person killed in").align(Align.left);
        mainTable.row().align(Align.left);
        mainTable.add("").align(Align.left);
        mainTable.row().align(Align.left);

        mainTable.add(cBRoomEntrance).align(Align.left);
        cBRoomEntrance.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomEntrance);
            }
        });
        mainTable.row();

        mainTable.add(cBRoomBedroom).align(Align.left);
        cBRoomBedroom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomBedroom);
            }
        });
        mainTable.row();

        mainTable.add(cBRoomDining).align(Align.left);
        cBRoomDining.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomDining);
            }
        });
        mainTable.row();

        mainTable.add(cBRoomKitchen).align(Align.left);
        cBRoomKitchen.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomKitchen);
            }
        });
        mainTable.row();

        mainTable.add(cBRoomGuest).align(Align.left);
        cBRoomGuest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomGuest);
            }
        });
        mainTable.row();

        mainTable.add(cBRoomMusicroom).align(Align.left);
        cBRoomMusicroom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomMusicroom);
            }
        });
        mainTable.row();

        mainTable.add(cBRoomBathroom).align(Align.left);
        cBRoomBathroom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomBathroom);
            }
        });
        mainTable.row();

        mainTable.add(cBRoomStudy).align(Align.left);
        cBRoomStudy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomStudy);
            }
        });
        mainTable.row();

        mainTable.add(cBRoomLibrary).align(Align.left);
        cBRoomLibrary.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkBoxListenerRoom(cBRoomLibrary);
            }
        });
        mainTable.row();


        mainTable.add("").align(Align.left);
        mainTable.row();
        mainTable.row();
        mainTable.add(makeAccusationBtn).size(130, 50).align(Align.left);
        mainTable.row();
        mainTable.add(makeFinalAccusationBtn).size(100, 50).align(Align.left);
        mainTable.add(cluedoBtn).size(100, 50).align(Align.center);

        //Add table to stage
        stage.addActor(mainTable);

        //If clicked go back to CluedoGame
        cluedoBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(cluedo);
            }
        });

        //If clicked exit the game
        makeAccusationBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                accusationButton();
            }
        });

        makeFinalAccusationBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                finalAccusationButton();
            }
        });
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

    private void accusationButton(){
        if(suspectChecked && weaponChecked && roomChecked){
            if(Murderer.isActuallyTheMurderer(accusedWeapon, accusedSuspect,
                    accusedRoom)){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connectionService.FinishGame();
                    }
                }).start();
                mainScreen.setScreen(new GameOverScreen());
            }else {
                mainScreen.setScreen(new WrongAccusationScreen(cluedo, mainScreen));
            }
        }
    }

    private void finalAccusationButton(){
        if(suspectChecked && weaponChecked && roomChecked){
            if(Murderer.isActuallyTheMurderer(accusedWeapon, accusedSuspect,
                    accusedRoom)){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connectionService.FinishGame();
                    }
                }).start();
                mainScreen.setScreen(new GameOverScreen());
            }else {
                Gdx.app.exit();
            }
        }
    }

    private void checkBoxListenerSuspect(CheckBox checkBox){
        if(!suspectChecked){
            suspectChecked = true;
            accusedSuspect = checkBox;
        }
    }

    private void checkBoxListenerWeapon(CheckBox checkBox){
        if(!weaponChecked){
            weaponChecked = true;
            accusedWeapon = checkBox;
        }
    }

    private void checkBoxListenerRoom(CheckBox checkBox){
        if(!roomChecked){
            roomChecked = true;
            accusedRoom = checkBox;
        }
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