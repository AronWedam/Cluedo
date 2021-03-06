package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.cluedo.game.network.ConnectionService;

public class Notebook {
    public Table table;
    private ScrollPane pane;
    final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private Player player;

    public int valueRoomCard;
    public int valueWeaponCard;
    public int valueSuspectCard;

    public boolean isChecked = false;

    private final Label notebookText        =   new Label("Notebook: ", skin);
    private final Label markText            =   new Label("(you can mark it off below!)", skin, "default");
    private final Label suspectsText        =   new Label("SUSPECTS", skin, "default");
    private final Label weaponsText         =   new Label("WEAPONS", skin, "default");
    private final Label roomsText           =   new Label("ROOMS",skin, "default");
    public final TextButton btnDice        =   new TextButton("Dice", skin, "default");
    public final TextButton btnAccusation  =   new TextButton("Accusation", skin, "default");
    public final TextButton btnFinishMove  =   new TextButton("Finish Move", skin);

    public final CheckBox cBMissScarlett   =   new CheckBox("MissScarlett", skin);
    public final CheckBox cBColonelMustard =   new CheckBox("ColonelMustard", skin);
    public final CheckBox cBMrsWhite       =   new CheckBox("MrsWhite", skin);
    public final CheckBox cBReverend       =   new CheckBox("Reverend", skin);
    public final CheckBox cBMrsPeacock     =   new CheckBox("MrsPeacock", skin);
    public final CheckBox cBProfessorPlum  =   new CheckBox("ProfessorPlum", skin);

    public final CheckBox cBRoomEntrance   =   new CheckBox("Entrance", skin);
    public final CheckBox cBRoomBedroom     =   new CheckBox("Bedroom", skin); //
    public final CheckBox cBRoomDining     =   new CheckBox("Dining", skin);
    public final CheckBox cBRoomKitchen    =   new CheckBox("Kitchen", skin);
    public final CheckBox cBRoomGuest   =   new CheckBox("Guestroom", skin); //
    public final CheckBox cBRoomMusicroom  =   new CheckBox("Musicroom", skin);
    public final CheckBox cBRoomBathroom   =   new CheckBox("Bathroom ", skin); //
    public final CheckBox cBRoomStudy      =   new CheckBox("Study", skin);
    public final CheckBox cBRoomLibrary    =   new CheckBox("Library", skin);

    public final CheckBox cBWeaponKnife    =   new CheckBox("Knife", skin);
    public final CheckBox cBWeaponRope     =   new CheckBox("Rope", skin);
    public final CheckBox cBWeaponGun      =   new CheckBox("Gun", skin);
    public final CheckBox cBWeaponPoison   =   new CheckBox("Poison", skin);
    public final CheckBox cBWeaponPipe     =   new CheckBox("Pipe", skin);
    public final CheckBox cBWeaponCandle   =   new CheckBox("Candle", skin);

    public final String remainingMovesText = "Remaining Moves: ";
    public final Label remainingMoves        =   new Label(remainingMovesText, skin);

    /**************************Non UI-Variables*************************************/
    private ConnectionService connectionService;

    private static Notebook notebook;

    public static Notebook getInstance(Player player){
        if(notebook == null){
            notebook = new Notebook(player);
        }
        return notebook;
    }

    private Notebook(Player player) {
        connectionService = ConnectionService.GetInstance();
        this.player = player;
        this.table = new Table(skin);
        this.table.defaults().padLeft(5).align(Align.left);
        this.pane = new ScrollPane(this.table, skin);

        //for the font size of the checkBoxes
        float CB_SCALING_X = (float) (getPane().getScaleX() / 0.5);
        float CB_SCALING_Y = (float) (getPane().getScaleY() / 0.5);

        this.table.add(notebookText);
        notebookText.setFontScale((float) (getPane().getScaleX() / 0.2),
                (float) (getPane().getScaleY() / 0.2));;
        this.table.row();


        this.table.add(markText);
        markText.setFontScale((float) (getPane().getScaleX() / 0.5),
                (float) (getPane().getScaleY() / 0.5));
        this.table.row();


        //SUSPECTS Category in Notebook
        this.table.add(suspectsText );
        suspectsText.setFontScale((float) (getPane().getScaleX() / 0.25),
                (float) (getPane().getScaleY() / 0.25));
        this.table.row();

        this.table.add(cBMissScarlett);
        cBMissScarlett.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBColonelMustard);
        cBColonelMustard.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBMrsWhite);
        cBMrsWhite.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBReverend);
        cBReverend.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBMrsPeacock);
        cBMrsPeacock.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBProfessorPlum);
        cBProfessorPlum.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();


        //WEAPONS Category in Notebook
        this.table.add(weaponsText);
        weaponsText.setFontScale((float) (getPane().getScaleX() / 0.25),
                (float) (getPane().getScaleY() / 0.25));
        this.table.row();

        this.table.add(cBWeaponCandle);
        cBWeaponCandle.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBWeaponGun);
        cBWeaponGun.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBWeaponKnife);
        cBWeaponKnife.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBWeaponPipe);
        cBWeaponPipe.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBWeaponPoison);
        cBWeaponPoison.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBWeaponRope);
        cBWeaponRope.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();


        //ROOMS Category in Notebook
        this.table.add(roomsText);
        roomsText.setFontScale((float) (getPane().getScaleX() / 0.25),
                (float) (getPane().getScaleY() / 0.25));
        this.table.row();

        this.table.add(cBRoomBedroom);
        cBRoomBedroom.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBRoomDining);
        cBRoomDining.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBRoomEntrance);
        cBRoomEntrance.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBRoomGuest);
        cBRoomGuest.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBRoomBathroom);
        cBRoomBathroom.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBRoomKitchen);
        cBRoomKitchen.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBRoomLibrary);
        cBRoomLibrary.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBRoomMusicroom);
        cBRoomMusicroom.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(cBRoomStudy);
        cBRoomStudy.getLabel().setFontScale(CB_SCALING_X, CB_SCALING_Y);
        this.table.row();

        this.table.add(btnFinishMove);
        btnFinishMove.getLabel().setFontScale((float) (getPane().getScaleX() / 0.45),
                (float) (getPane().getScaleY() / 0.45));
        btnFinishMove.center();
        this.table.row();

        this.table.add(btnDice);
        btnDice.getLabel().setFontScale((float) (getPane().getScaleX() / 0.45),
                (float) (getPane().getScaleY() / 0.45));
        btnAccusation.center();
        this.table.row();

        this.table.add(btnAccusation);
        btnAccusation.getLabel().setFontScale((float) (getPane().getScaleX() / 0.6),
                (float) (getPane().getScaleY() / 0.45));
        btnAccusation.center();
        this.table.row();

        this.table.add(remainingMoves);
        remainingMoves.setFontScale((float) (getPane().getScaleX() / 0.5),
                (float) (getPane().getScaleY() / 0.5));
        this.table.row();

        pane.setActor(this.table);
    }


    public void yourRoomCards(){
        valueRoomCard = player.getMyRoomCard().getValue();

        if(valueRoomCard == 1){
            cardInHand(cBRoomEntrance);
        }else if(valueRoomCard == 2){
            cardInHand(cBRoomBedroom);
        }else if(valueRoomCard == 3){
            cardInHand(cBRoomDining);
        }else if(valueRoomCard == 4){
            cardInHand(cBRoomKitchen);
        }else if(valueRoomCard == 5){
            cardInHand(cBRoomGuest);
        }else if(valueRoomCard == 6){
            cardInHand(cBRoomMusicroom);
        }else if(valueRoomCard == 7){
            cardInHand(cBRoomBathroom);
        }else if(valueRoomCard == 8){
            cardInHand(cBRoomStudy);
        }else if(valueRoomCard == 9){
            cardInHand(cBRoomLibrary);
        }
    }

    public void yourSuspectCard(){
        valueSuspectCard = player.getMySuspectCard().getValue();

        if(valueSuspectCard == 1){
            cardInHand(cBMissScarlett);
        }else if(valueSuspectCard == 2){
            cardInHand(cBColonelMustard);
        }else if(valueSuspectCard == 3){ ;
            cardInHand(cBMrsWhite);
        }else if(valueSuspectCard == 4){
            cardInHand(cBReverend);
        }else if(valueSuspectCard == 5){
            cardInHand(cBMrsPeacock);
        }else if(valueSuspectCard == 6){
            cardInHand(cBProfessorPlum);
        }
    }


    public void yourWeaponCard(){
        valueWeaponCard = player.getMyWeaponCard().getValue();

        if(valueWeaponCard == 1){
            cardInHand(cBWeaponKnife);
        }else if(valueWeaponCard == 2){
            cardInHand(cBWeaponRope);
        }else if(valueWeaponCard == 3){
            cardInHand(cBWeaponGun);
        }else if(valueWeaponCard == 4){
            cardInHand(cBWeaponPoison);
        }else if(valueWeaponCard == 5){
            cardInHand(cBWeaponPipe);
        }else if(valueWeaponCard == 6){
            cardInHand(cBWeaponCandle);
        }
    }

    //Mark your cards Green and checks it, so you know what Card is yours
    private void cardInHand(CheckBox checkBox){
        checkBox.getLabel().setColor(Color.GREEN);
        checkBox.setDisabled(true);
        checkBox.setChecked(true);
    }

    public void checkCheckBox(CheckBox checkBox){
        checkBox.setDisabled(true);
        checkBox.setChecked(true);
        isChecked = true;
    }

    public void uncheckCheckBox(CheckBox checkBox){
        checkBox.setChecked(false);
        isChecked = false;
    }

    public ScrollPane getPane(){
        return pane;
    }
}
