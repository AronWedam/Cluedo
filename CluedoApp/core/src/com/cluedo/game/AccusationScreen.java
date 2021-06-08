package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

import javax.swing.text.TabExpander;


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
    Murderer murderer;
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
    private final CheckBox cBRoomBedroom     =   new CheckBox("Bedroom", skin); //
    private final CheckBox cBRoomDining     =   new CheckBox("Dining", skin);
    private final CheckBox cBRoomKitchen    =   new CheckBox("Kitchen", skin);
    private final CheckBox cBRoomGuest   =   new CheckBox("Guestroom", skin); //
    private final CheckBox cBRoomMusicroom  =   new CheckBox("Musicroom", skin);
    private final CheckBox cBRoomBathroom   =   new CheckBox("Bathroom ", skin); //
    private final CheckBox cBRoomStudy      =   new CheckBox("Study", skin);
    private final CheckBox cBRoomLibrary    =   new CheckBox("Library", skin);


    public AccusationScreen(MainScreen mainScreen, GameClass game){
        this.mainScreen = mainScreen;

        this.gameClass = game;
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
        mainTable = new Table(skin);
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
        mainTable.add("Who do you want to accuse").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add(""+'\n').align(Align.left);
        mainTable.row().colspan(2);

        mainTable.add(cBMissScarlett);
        //cBMissScarlett.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
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

        double y = Gdx.input.getY(0);
        int row = mainTable.getRow((float) (Gdx.graphics.getHeight() - y));
        Gdx.app.log("Row", "Row: " + row);
        if (Gdx.input.justTouched()){
            if (row <= 30 && row >= 0) {
                try {
                    Actor actor = mainTable.getChild(row);
                    Gdx.app.log("Class", actor.getClass().getName());

                    if (actor instanceof CheckBox) {
                        CheckBox myCheckBox = (CheckBox) actor;
                        String clickedCheckbox = myCheckBox.getText().toString();
                        if (row <= 6 && row >= 0) {
                            checkedSuspect(clickedCheckbox);
                        } else if (row > 6 && row <= 12) {
                            checkedWeapon(clickedCheckbox);
                        } else if (row > 12 && row <= 21) {
                            checkedRoom(clickedCheckbox);
                        }
                    }

                } catch (Exception ex) {
                    Gdx.app.log("Exception", ex.getMessage());
                }
            }
        }

        if(suspectChecked && weaponChecked && roomChecked){
            isActuallyTheMurderer(accusedSuspect, accusedWeapon, accusedRoom);
        }
    }

    private void checkedSuspect(String checkBox){
        switch (checkBox) {
            //PERSON
            case "MissScarlett":
                checkCheckBox(cBMissScarlett);
                accusedSuspect = cBMissScarlett;
                suspectChecked = true;
                break;
            case "ColonelMustard":
                checkCheckBox(cBColonelMustard);
                accusedSuspect = cBColonelMustard;
                suspectChecked = true;
                break;
            case "MrsWhite":
                checkCheckBox(cBMrsWhite);
                accusedSuspect = cBMrsWhite;
                suspectChecked = true;
                break;
            case "Reverend":
                checkCheckBox(cBReverend);
                accusedSuspect = cBReverend;
                suspectChecked = true;
                break;
            case "MrsPeacock":
                checkCheckBox(cBMrsPeacock);
                accusedSuspect = cBMrsPeacock;
                suspectChecked = true;
                break;
            case "ProfessorPlum":
                checkCheckBox(cBProfessorPlum);
                accusedSuspect = cBProfessorPlum;
                suspectChecked = true;
                break;
            default:
                break;
        }
    }

    //here
    private void checkedWeapon(String checkBox){
        switch (checkBox) {
            case "Knife":
                checkCheckBox(cBWeaponKnife);
                accusedWeapon = cBWeaponKnife;
                weaponChecked = true;
                break;
            case "Rope":
                checkCheckBox(cBWeaponRope);
                accusedWeapon = cBWeaponRope;
                weaponChecked = true;
                break;
            case "Gun":
                checkCheckBox(cBWeaponGun);
                accusedWeapon = cBWeaponGun;
                weaponChecked = true;
                break;
            case "Poison":
                checkCheckBox(cBWeaponPoison);
                accusedWeapon = cBWeaponPoison;
                weaponChecked = true;
                break;
            case "Pipe":
                checkCheckBox(cBWeaponPipe);
                accusedWeapon = cBWeaponPipe;
                weaponChecked = true;
                break;
            case "Candle":
                checkCheckBox(cBWeaponCandle);
                accusedWeapon = cBWeaponCandle;
                weaponChecked = true;
                break;
            default:
                break;
        }
    }

    private void checkedRoom(String checkBox){
        switch (checkBox) {
            case "Entrance":
                checkCheckBox(cBRoomEntrance);
                accusedRoom = cBRoomEntrance;
                roomChecked = true;
                break;
            case "Bathroom":
                checkCheckBox(cBRoomBathroom);
                accusedRoom = cBRoomBathroom;
                roomChecked = true;
                break;
            case "Dining":
                checkCheckBox(cBRoomDining);
                accusedRoom = cBRoomDining;
                roomChecked = true;
                break;
            case "Kitchen":
                checkCheckBox(cBRoomKitchen);
                accusedRoom = cBRoomKitchen;
                roomChecked = true;
                break;
            case "Bedroom":
                checkCheckBox(cBRoomBedroom);
                accusedRoom = cBRoomBedroom;
                roomChecked = true;
                break;
            case "Musicroom":
                checkCheckBox(cBRoomMusicroom);
                accusedRoom = cBRoomMusicroom;
                roomChecked = true;
                break;
            case "Guestroom":
                checkCheckBox(cBRoomGuest);
                accusedRoom = cBRoomGuest;
                roomChecked = true;
                break;
            case "Study":
                checkCheckBox(cBRoomStudy);
                accusedRoom = cBRoomStudy;
                roomChecked = true;
                break;
            case "Library":
                checkCheckBox(cBRoomLibrary);
                accusedRoom = cBRoomLibrary;
                roomChecked = true;
                break;
        }
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

    private void checkCheckBox(CheckBox checkBox){
        checkBox.setDisabled(true);
        checkBox.setChecked(true);
    }


    public boolean isActuallyTheMurderer(CheckBox cbAccusedWeapon, CheckBox cBAccusedPerson,
                                         CheckBox cBAccusedRoom){
        if(cbAccusedWeapon.toString() == murderer.getMurdererWeaponString()){
            if(cBAccusedPerson.toString() == murderer.getMurdererSuspectString()){
                return cBAccusedRoom.toString() == murderer.getMurdererRoomString();
            }else{
                return false;
            }
        }else return false;
    }


}
