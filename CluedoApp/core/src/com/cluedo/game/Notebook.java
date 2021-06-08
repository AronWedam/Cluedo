package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class Notebook {

    public Table table;
    private ScrollPane pane;
    final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private GameClass gameClass;
    private MainScreen mainScreen;
    private final Label notebookText        =   new Label("Notebook: ", skin);
    private final Label markText            =   new Label("(you can mark it off below!)", skin, "default");
    private final Label suspectsText        =   new Label("SUSPECTS", skin, "default");
    private final Label weaponsText         =   new Label("WEAPONS", skin, "default");
    private final Label roomsText           =   new Label("ROOMS",skin, "default");
    private final TextButton btnDice        =   new TextButton("Dice", skin, "default");
    private final TextButton btnAccusation  =   new TextButton("Accusation", skin, "default");
    private final TextButton btnHelp        =   new TextButton("Help", skin, "default");

    private final CheckBox cBMissScarlett   =   new CheckBox("MissScarlett", skin);
    private final CheckBox cBColonelMustard =   new CheckBox("ColonelMustard", skin);
    private final CheckBox cBMrsWhite       =   new CheckBox("MrsWhite", skin);
    private final CheckBox cBReverend       =   new CheckBox("Reverend", skin);
    private final CheckBox cBMrsPeacock     =   new CheckBox("MrsPeacock", skin);
    private final CheckBox cBProfessorPlum  =   new CheckBox("ProfessorPlum", skin);

    private final CheckBox cBRoomEntrance   =   new CheckBox("Entrance", skin);
    private final CheckBox cBRoomGarden     =   new CheckBox("Garden", skin);
    private final CheckBox cBRoomDining     =   new CheckBox("Dining", skin);
    private final CheckBox cBRoomKitchen    =   new CheckBox("Kitchen", skin);
    private final CheckBox cBRoomBallroom   =   new CheckBox("Ballroom", skin);
    private final CheckBox cBRoomMusicroom  =   new CheckBox("Musicroom", skin);
    private final CheckBox cBRoomGameroom   =   new CheckBox("Gameroom ", skin);
    private final CheckBox cBRoomStudy      =   new CheckBox("Study", skin);
    private final CheckBox cBRoomLibrary    =   new CheckBox("Library", skin);

    private final CheckBox cBWeaponKnife    =   new CheckBox("Knife", skin);
    private final CheckBox cBWeaponRope     =   new CheckBox("Rope", skin);
    private final CheckBox cBWeaponGun      =   new CheckBox("Gun", skin);
    private final CheckBox cBWeaponPoison   =   new CheckBox("Poison", skin);
    private final CheckBox cBWeaponPipe     =   new CheckBox("Pipe", skin);
    private final CheckBox cBWeaponCandle   =   new CheckBox("Candle", skin);


    public Notebook() {
        this.table = new Table(skin);
        this.table.defaults().padLeft(5).align(Align.left);
        this.pane = new ScrollPane(this.table, skin);

        this.table.add(notebookText);
        notebookText.setFontScale((float) (getPane().getScaleX() / 0.2),
                (float) (getPane().getScaleY() / 0.2));
        ;
        this.table.row();


        this.table.add(markText);
        markText.setFontScale((float) (getPane().getScaleX() / 0.5),
                (float) (getPane().getScaleY() / 0.5));
        this.table.row();


        this.table.add(new Label("", skin));
        this.table.row();


        //SUSPECTS Category in Notebook
        this.table.add(suspectsText);
        suspectsText.setFontScale((float) (getPane().getScaleX() / 0.25),
                (float) (getPane().getScaleY() / 0.25));
        this.table.row();

        this.table.add(cBMissScarlett);
        float CB_SCALING_X = (float) (getPane().getScaleX() / 0.5);
        float CB_SCALING_Y = (float) (getPane().getScaleY() / 0.5);
        cBMissScarlett.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBMissScarlett);
        this.table.row();

        this.table.add(cBColonelMustard);
        cBColonelMustard.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBColonelMustard);
        this.table.row();

        this.table.add(cBMrsWhite);
        cBMrsWhite.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBMrsWhite);
        this.table.row();

        this.table.add(cBReverend);
        cBReverend.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBReverend);
        this.table.row();

        this.table.add(cBMrsPeacock);
        cBMrsPeacock.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBMrsPeacock);
        this.table.row();

        this.table.add(cBProfessorPlum);
        cBProfessorPlum.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBProfessorPlum);
        this.table.add(new Label("", skin));
        this.table.row();


        //WEAPONS Category in Notebook
        this.table.add(weaponsText);
        weaponsText.setFontScale((float) (getPane().getScaleX() / 0.25),
                (float) (getPane().getScaleY() / 0.25));
        this.table.row();

        this.table.add(cBWeaponCandle);
        cBWeaponCandle.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBWeaponCandle);
        this.table.row();

        this.table.add(cBWeaponGun);
        cBWeaponGun.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBWeaponGun);
        this.table.row();

        this.table.add(cBWeaponKnife);
        cBWeaponKnife.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBWeaponKnife);
        this.table.row();

        this.table.add(cBWeaponPipe);
        cBWeaponPipe.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBWeaponPipe);
        this.table.row();

        this.table.add(cBWeaponPoison);
        cBWeaponPoison.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBWeaponPoison);
        this.table.row();

        this.table.add(cBWeaponRope);
        cBWeaponRope.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBWeaponPoison);

        this.table.add(new Label("", skin));
        this.table.row();


        //ROOMS Category in Notebook
        this.table.add(roomsText);
        roomsText.setFontScale((float) (getPane().getScaleX() / 0.25),
                (float) (getPane().getScaleY() / 0.25));
        this.table.row();

        this.table.add(cBRoomBallroom);
        cBRoomBallroom.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomBallroom);
        this.table.row();

        this.table.add(cBRoomDining);
        cBRoomDining.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomDining);
        this.table.row();

        this.table.add(cBRoomEntrance);
        cBRoomEntrance.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomEntrance);
        this.table.row();

        this.table.add(cBRoomGameroom);
        cBRoomGameroom.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomGameroom);
        this.table.row();

        this.table.add(cBRoomGarden);
        cBRoomGarden.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomGarden);
        this.table.row();

        this.table.add(cBRoomKitchen);
        cBRoomKitchen.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomKitchen);
        this.table.row();

        this.table.add(cBRoomLibrary);
        cBRoomLibrary.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomLibrary);
        this.table.row();

        this.table.add(cBRoomMusicroom);
        cBRoomMusicroom.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomMusicroom);
        this.table.row();

        this.table.add(cBRoomStudy);
        cBRoomStudy.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        addListenerToCheckBox(cBRoomStudy);

        this.table.add(new Label("", skin));
        this.table.row();
        this.table.row();


        //BUTTONS
        this.table.add(btnDice);
        btnDice.getLabel().setFontScale((float) (getPane().getScaleX() / 0.45),
                (float) (getPane().getScaleY() / 0.45));
        btnAccusation.center();
        this.table.row();

        this.table.row();
        this.table.add(btnAccusation);
        btnAccusation.getLabel().setFontScale((float) (getPane().getScaleX() / 0.45),
                (float) (getPane().getScaleY() / 0.45));
        btnAccusation.center();
        this.table.row();

        this.table.row();
        this.table.add(btnHelp);
        btnHelp.getLabel().setFontScale((float) (getPane().getScaleX() / 0.45),
                (float) (getPane().getScaleY() / 0.45));
        btnAccusation.center();
        this.table.row();

        pane.setActor(this.table);

        btnDice.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainScreen.setScreen(new DiceScreen(gameClass, mainScreen));
            }
        });
    }


    public ScrollPane getPane(){
        return pane;
    }

    private static void addListenerToCheckBox(final CheckBox cB){
        cB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(cB.isChecked());
            }
        });
    }

}
