package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class Notebook {

    public Table table;
    private ScrollPane pane;
    final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
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
        notebookText.setFontScale(5, 5);
        this.table.row();


        this.table.add(markText);
        markText.setFontScale(2, 2);
        this.table.row();


        this.table.add(new Label("", skin));
        this.table.row();


        this.table.add(suspectsText );
        suspectsText.setFontScale(4, 4);
        this.table.row();

        this.table.add(cBMissScarlett);
        cBMissScarlett.getLabel().setFontScale(getPane().getScaleX() / 2,
                getPane().getScaleY() / 2);
        this.table.row();
        this.table.add(cBColonelMustard);
        this.table.row();
        this.table.add(cBMrsWhite);
        this.table.row();
        this.table.add(cBReverend);
        this.table.row();
        this.table.add(cBMrsPeacock);
        this.table.row();
        this.table.add(cBProfessorPlum);



        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add(weaponsText);
        weaponsText.setFontScale(4, 4);
        this.table.row();

        this.table.add(cBWeaponCandle);
        this.table.row();
        this.table.add(cBWeaponGun);
        this.table.row();
        this.table.add(cBWeaponKnife);
        this.table.row();
        this.table.add(cBWeaponPipe);
        this.table.row();
        this.table.add(cBWeaponPoison);
        this.table.row();
        this.table.add(cBWeaponRope);

        this.table.add(new Label("", skin));
        this.table.row();

        this.table.add(roomsText);
        roomsText.setFontScale(4, 4);
        this.table.row();

        this.table.add(cBRoomBallroom);
        cBRoomBallroom.scaleBy(4, 4);
        this.table.row();
        this.table.add(cBRoomDining);
        this.table.row();
        this.table.add(cBRoomEntrance);
        this.table.row();
        this.table.add(cBRoomGameroom);
        this.table.row();
        this.table.add(cBRoomGarden);
        this.table.row();
        this.table.add(cBRoomKitchen);
        this.table.row();
        this.table.add(cBRoomLibrary);
        this.table.row();
        this.table.add(cBRoomMusicroom);
        this.table.row();
        this.table.add(cBRoomStudy);


        this.table.row();
        this.table.row();
        this.table.add(btnDice);
        btnDice.setSize(20, 20);
        this.table.row();

        this.table.row();
        this.table.add(btnAccusation);
        btnAccusation.setSize(20, 20);
        this.table.row();

        this.table.row();
        this.table.add(btnHelp);
        btnHelp.setSize(20, 20);
        this.table.row();


        pane.setActor(this.table);

    }
    public ScrollPane getPane(){
        return pane;
    }

}
