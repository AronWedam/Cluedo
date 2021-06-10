package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cluedo.game.network.ConnectionService;

import javax.swing.text.TabExpander;


public class AccusationScreen implements Screen {
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas = new TextureAtlas("uiskin.atlas");;
    protected Skin skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
    private MainScreen mainScreen;
    private GameClass gameClass;
    private ConnectionService connectionService;
    Murderer murderer;
    Table mainTable;
    Cluedo cluedo;

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
        final TextButton makeAccusationBtn = new TextButton("Accusation", skin);


        //Add Text and Buttons to the table
        mainTable.add("Who do you want to accuse").align(Align.left);
        mainTable.row().align(Align.left); //+'\n'  colspan(2).
        mainTable.add("").align(Align.left);
        mainTable.row().align(Align.left);

        mainTable.add(cBMissScarlett).align(Align.left);
        cBMissScarlett.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!suspectChecked){
                    suspectChecked = true;
                    accusedSuspect = cBMissScarlett;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBColonelMustard).align(Align.left);
        cBColonelMustard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!suspectChecked){
                    suspectChecked = true;
                    accusedSuspect = cBColonelMustard;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBMrsWhite).align(Align.left);
        cBMrsWhite.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!suspectChecked){
                    suspectChecked = true;
                    accusedSuspect = cBMrsWhite;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBReverend).align(Align.left);
        cBReverend.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!suspectChecked){
                    suspectChecked = true;
                    accusedSuspect = cBReverend;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBMrsPeacock).align(Align.left);
        cBMrsPeacock.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!suspectChecked){
                    suspectChecked = true;
                    accusedSuspect = cBMrsPeacock;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBProfessorPlum).align(Align.left);
        cBProfessorPlum.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!suspectChecked){
                    suspectChecked = true;
                    accusedSuspect = cBProfessorPlum;
                }
            }
        });
        mainTable.row();


        mainTable.add("").align(Align.left);
        mainTable.add("What weapon did they use").align(Align.left);
        mainTable.row().align(Align.left);
        mainTable.add("").align(Align.left);
        mainTable.row().align(Align.left);

        mainTable.add(cBWeaponKnife).align(Align.left);
        cBWeaponKnife.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!weaponChecked){
                    weaponChecked = true;
                    accusedWeapon = cBWeaponKnife;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponRope).align(Align.left);
        cBWeaponRope.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!weaponChecked){
                    weaponChecked = true;
                    accusedWeapon = cBWeaponRope;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponGun).align(Align.left);
        cBWeaponGun.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!weaponChecked){
                    weaponChecked = true;
                    accusedWeapon = cBWeaponGun;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponPoison).align(Align.left);
        cBWeaponPoison.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!weaponChecked){
                    weaponChecked = true;
                    accusedWeapon = cBWeaponPoison;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponPipe).align(Align.left);
        cBWeaponPipe.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!weaponChecked){
                    weaponChecked = true;
                    accusedWeapon = cBWeaponPipe;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBWeaponCandle).align(Align.left);
        cBWeaponCandle.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!weaponChecked){
                    weaponChecked = true;
                    accusedWeapon = cBWeaponCandle;
                }
            }
        });
        mainTable.row();


        mainTable.add("").align(Align.left);
        mainTable.add("What room was the person killed in").align(Align.left);
        mainTable.row().align(Align.left);
        mainTable.add("").align(Align.left);
        mainTable.row().align(Align.left);

        mainTable.add(cBRoomEntrance).align(Align.left);
        cBRoomEntrance.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomEntrance;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBRoomBedroom).align(Align.left);
        cBRoomBedroom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomBedroom;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBRoomDining).align(Align.left);
        cBRoomDining.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomDining;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBRoomKitchen).align(Align.left);
        cBRoomKitchen.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomKitchen;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBRoomGuest).align(Align.left);
        cBRoomGuest.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomGuest;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBRoomMusicroom).align(Align.left);
        cBRoomMusicroom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomMusicroom;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBRoomBathroom).align(Align.left);
        cBRoomBathroom.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomBathroom;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBRoomStudy).align(Align.left);
        cBRoomStudy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomStudy;
                }
            }
        });
        mainTable.row();

        mainTable.add(cBRoomLibrary).align(Align.left);
        cBRoomLibrary.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!roomChecked){
                    roomChecked = true;
                    accusedRoom = cBRoomLibrary;
                }
            }
        });
        mainTable.row();


        mainTable.add("").align(Align.left);
        mainTable.add("").align(Align.left);
        mainTable.add(makeAccusationBtn).size(100, 50).align(Align.left);
        mainTable.row();
        mainTable.add(mainBtn).size(100, 50).align(Align.left);



        //Add table to stage
        stage.addActor(mainTable);

        //If clicked go back to CluedoGame
        mainBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //mainScreen.backpressed=true;
                //mainScreen.setScreen();
                //Gdx.input.setCatchBackKey(true);

                //NOT WORKING CORRECT BECAUSE YOU GET NEW GREEN INPUT
                try {
                    mainScreen.setScreen(new Cluedo(gameClass, mainScreen));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //mainScreen.setScreen(cluedo);
            }
        });

        //If clicked exit the game
        makeAccusationBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(suspectChecked && weaponChecked && roomChecked){
                    if(isActuallyTheMurderer(accusedWeapon, accusedSuspect,
                            accusedRoom)){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                connectionService.FinishGame();
                            }
                        }).start();
                        //TODO that it displays for everybody
                        mainScreen.setScreen(new GameOverScreen());
                        makeAccusationBtn.setDisabled(false);
                        Gdx.app.log("TRUE", "TRUE");
                    }else {
                        //TODO if somebody made a wrong accusation
                        Gdx.graphics.setTitle("Your accusation was not correct");
                        Gdx.app.log("WRONG", "WRONG");
                        //Gdx.app.exit(); //should the person be excluded or not?
                    }
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        /*
        double y = Gdx.input.getY(0);
        int row = mainTable.getRow((float) (Gdx.graphics.getHeight() - y));
        Gdx.app.log("Row", "Row: " + row);
        if (Gdx.input.justTouched()){       //if this is not here it goes into the second if, but then it is a loop
            if (row <= 30 && row >= 0) {
                try {
                    Actor actor = mainTable.getChild(row);
                    Gdx.app.log("Class", actor.getClass().getName());
                    if (actor instanceof Label) {
                        CheckBox myCheckBox = (CheckBox) actor;
                        String clickedCheckbox = myCheckBox.getText().toString();
                        if (row <= 13 && row >= 7) {
                            if(!suspectChecked)     checkedSuspect(clickedCheckbox);
                        } else if (row > 13 && row <= 19) {
                            if(!weaponChecked)      checkedWeapon(clickedCheckbox);
                        } else if (row > 19 && row <= 29) {
                            if(!roomChecked)        checkedRoom(clickedCheckbox);
                        }
                    }

                } catch (Exception ex) {
                    Gdx.app.log("Exception", ex.getMessage());
                }
            }


        }

         */

    }
    /*

    private void checkedSuspect(String checkBox){
        if(!suspectChecked) {
            switch (checkBox) {
                //PERSON
                case "MissScarlett":
                    suspectChecked = true;
                    checkCheckBox(cBMissScarlett);
                    accusedSuspect = cBMissScarlett;
                    break;
                case "ColonelMustard":
                    suspectChecked = true;
                    checkCheckBox(cBColonelMustard);
                    accusedSuspect = cBColonelMustard;
                    break;
                case "MrsWhite":
                    suspectChecked = true;
                    checkCheckBox(cBMrsWhite);
                    accusedSuspect = cBMrsWhite;
                    break;
                case "Reverend":
                    suspectChecked = true;
                    checkCheckBox(cBReverend);
                    accusedSuspect = cBReverend;
                    break;
                case "MrsPeacock":
                    suspectChecked = true;
                    checkCheckBox(cBMrsPeacock);
                    accusedSuspect = cBMrsPeacock;
                    break;
                case "ProfessorPlum":
                    suspectChecked = true;
                    checkCheckBox(cBProfessorPlum);
                    accusedSuspect = cBProfessorPlum;
                    break;
                default:
                    break;
            }
        }
    }

    //here
    private void checkedWeapon(String checkBox){
        if(!weaponChecked) {
            switch (checkBox) {
                case "Knife":
                    weaponChecked = true;
                    checkCheckBox(cBWeaponKnife);
                    accusedWeapon = cBWeaponKnife;
                    break;
                case "Rope":
                    weaponChecked = true;
                    checkCheckBox(cBWeaponRope);
                    accusedWeapon = cBWeaponRope;
                    break;
                case "Gun":
                    weaponChecked = true;
                    checkCheckBox(cBWeaponGun);
                    accusedWeapon = cBWeaponGun;
                    break;
                case "Poison":
                    weaponChecked = true;
                    checkCheckBox(cBWeaponPoison);
                    accusedWeapon = cBWeaponPoison;
                    break;
                case "Pipe":
                    weaponChecked = true;
                    checkCheckBox(cBWeaponPipe);
                    accusedWeapon = cBWeaponPipe;
                    break;
                case "Candle":
                    weaponChecked = true;
                    checkCheckBox(cBWeaponCandle);
                    accusedWeapon = cBWeaponCandle;
                    break;
                default:
                    break;
            }
        }
    }

    private void checkedRoom(String checkBox){
        if(!roomChecked) {
            switch (checkBox) {
                case "Entrance":
                    roomChecked = true;
                    checkCheckBox(cBRoomEntrance);
                    accusedRoom = cBRoomEntrance;
                    break;
                case "Bathroom":
                    roomChecked = true;
                    checkCheckBox(cBRoomBathroom);
                    accusedRoom = cBRoomBathroom;
                    break;
                case "Dining":
                    roomChecked = true;
                    checkCheckBox(cBRoomDining);
                    accusedRoom = cBRoomDining;
                    break;
                case "Kitchen":
                    roomChecked = true;
                    checkCheckBox(cBRoomKitchen);
                    accusedRoom = cBRoomKitchen;
                    break;
                case "Bedroom":
                    roomChecked = true;
                    checkCheckBox(cBRoomBedroom);
                    accusedRoom = cBRoomBedroom;
                    break;
                case "Musicroom":
                    roomChecked = true;
                    checkCheckBox(cBRoomMusicroom);
                    accusedRoom = cBRoomMusicroom;
                    break;
                case "Guestroom":
                    roomChecked = true;
                    checkCheckBox(cBRoomGuest);
                    accusedRoom = cBRoomGuest;
                    break;
                case "Study":
                    roomChecked = true;
                    checkCheckBox(cBRoomStudy);
                    accusedRoom = cBRoomStudy;
                    break;
                case "Library":
                    roomChecked = true;
                    checkCheckBox(cBRoomLibrary);
                    accusedRoom = cBRoomLibrary;
                    break;
                default:
                    break;
            }
        }
    }

     */


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

    /*
    private void checkCheckBox(CheckBox checkBox){
        checkBox.setDisabled(true);
        checkBox.setChecked(true);
    }

     */



    public boolean isActuallyTheMurderer(CheckBox cbAccusedWeapon, CheckBox cBAccusedPerson,
                                         CheckBox cBAccusedRoom){
        String weapon = cbAccusedWeapon.toString().split(" ")[1];
        String person = cBAccusedPerson.toString().split(" ")[1];
        String room = cBAccusedRoom.toString().split(" ")[1];
        if(weapon.equals(connectionService.getWeapon())){
            if(person.equals(connectionService.getSuspect())){
                return room.equals(connectionService.getRoom());
            }
        }

        return false;
    }
}