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
    private Label notebookText;
    private Label handCardText;
    private Label shownCardText;
    private Label markText;
    private Label suspectsText;
    private Label weaponsText;
    private Label roomsText;
    private TextButton btnDice;
    private TextButton btnAccusation;
    private TextButton btnHelp;

    private CheckBox cBMissScarlett = new CheckBox("MissScarlett", skin);
    private CheckBox cBColonelMustard = new CheckBox("ColonelMustard", skin);
    private CheckBox cBMrsWhite = new CheckBox("MrsWhite", skin);
    private CheckBox cBReverend = new CheckBox("Reverend", skin);
    private CheckBox cBMrsPeacock = new CheckBox("MrsPeacock", skin);
    private CheckBox cBProfessorPlum = new CheckBox("ProfessorPlum", skin);

    private CheckBox cBRoomEntrance = new CheckBox("Entrance", skin);
    private CheckBox cBRoomGarden = new CheckBox("Garden", skin);
    private CheckBox cBRoomDining = new CheckBox("Dining", skin);
    private CheckBox cBRoomKitchen = new CheckBox("Kitchen", skin);
    private CheckBox cBRoomBallroom = new CheckBox("Ballroom", skin);
    private CheckBox cBRoomMusicroom = new CheckBox("Musicroom", skin);
    private CheckBox cBRoomGameroom  = new CheckBox("Gameroom ", skin);
    private CheckBox cBRoomStudy = new CheckBox("Study", skin);
    private CheckBox cBRoomLibrary = new CheckBox("Library", skin);

    private CheckBox cBWeaponKnife = new CheckBox("Knife", skin);
    private CheckBox cBWeaponRope = new CheckBox("Rope", skin);
    private CheckBox cBWeaponGun = new CheckBox("Gun", skin);
    private CheckBox cBWeaponPoison = new CheckBox("Poison", skin);
    private CheckBox cBWeaponPipe = new CheckBox("Pipe", skin);
    private CheckBox cBWeaponCandle = new CheckBox("Candle", skin);




    public Notebook() {
        this.table = new Table(skin);
        this.table.defaults().padLeft(5).align(Align.left);
        this.pane = new ScrollPane(this.table, skin);



        this.table.add(notebookText = new Label("Notebook: ", skin));
        notebookText.setFontScale(5
                , 5);
        this.table.row();


        this.table.add(markText = new Label("(you can mark it off below!)", skin, "default"));
        markText.setFontScale(2, 2);
        this.table.row();


        this.table.add(new Label("", skin));
        this.table.row();


        this.table.add(suspectsText = new Label("SUSPECTS", skin, "default"));
        suspectsText.setFontScale(4, 4);
        this.table.row();
        /*
        for (int i = 0; i < Cards.numSuspects; i++) {
            this.table.row();
            Cards card = new Cards(Cards.TYPE_SUSPECT, i);
            this.table.add(new Item(card));
        }

         */
        this.table.add(cBMissScarlett);
        this.table.add(cBColonelMustard);
        this.table.add(cBMrsWhite);
        this.table.add(cBReverend);
        this.table.add(cBMrsPeacock);
        this.table.add(cBProfessorPlum);



        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add(weaponsText = new Label("WEAPONS", skin, "default"));
        weaponsText.setFontScale(4, 4);
        this.table.row();

        this.table.add(cBWeaponCandle);
        this.table.add(cBWeaponGun);
        this.table.add(cBWeaponKnife);
        this.table.add(cBWeaponPipe);
        this.table.add(cBWeaponPoison);
        this.table.add(cBWeaponRope);

        this.table.add(new Label("", skin));
        this.table.row();

        this.table.add(roomsText = new Label("ROOMS",skin, "default"));
        roomsText.setFontScale(4, 4);
        this.table.row();

        this.table.add(cBRoomBallroom);
        this.table.add(cBRoomDining);
        this.table.add(cBRoomEntrance);
        this.table.add(cBRoomGameroom);
        this.table.add(cBRoomGarden);
        this.table.add(cBRoomKitchen);
        this.table.add(cBRoomLibrary);
        this.table.add(cBRoomMusicroom);
        this.table.add(cBRoomStudy);


        this.table.add(btnDice = new TextButton("Dice", skin, "default"));
        btnDice.setSize(20, 20);
        this.table.row();

        this.table.row();
        this.table.add(btnAccusation = new TextButton("Accusation", skin, "default"));
        btnAccusation.setSize(20, 20);
        this.table.row();

        this.table.row();
        this.table.add(btnHelp = new TextButton("Help", skin, "default"));
        btnHelp.setSize(20, 20);
        this.table.row();


        pane.setActor(this.table);

    }
    public ScrollPane getPane(){
        return pane;
    }

    private class Item {
        Cards card;
        CheckBox checkBox;
        Boolean isCardInHand;

        Item(Cards card) {
            this.card = card;
            if(isCardInHand){
                this.checkBox = new CheckBox(card.toString(), skin);
                this.checkBox.setDisabled(true);
                this.checkBox.setChecked(true);
            } else {
                this.checkBox = new CheckBox(card.toString(), skin);
            }

        }
    }

}
