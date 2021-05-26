package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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



    public Notebook() {
        this.table = new Table(skin);
        this.table.defaults().padLeft(5).align(Align.left);
        this.pane = new ScrollPane(this.table, skin);


        this.table.add(notebookText = new Label("Notebook: ", skin));
        notebookText.setFontScale(5
                , 5);
        this.table.row();

        this.table.add(handCardText = new Label("(your cards in hand)", skin, "default"));
        handCardText.setFontScale(3, 3);
        this.table.row();
        //this.table.row();
       // this.table.row();

        this.table.add(shownCardText = new Label("when shown a card,", skin, "default"));
        shownCardText.setFontScale(3, 3);
        this.table.row();


        this.table.add(markText = new Label("you can mark it off below!", skin, "default"));
        markText.setFontScale(3, 3);
        this.table.row();


        this.table.add(new Label("", skin));
        this.table.row();


        this.table.add(suspectsText = new Label("SUSPECTS", skin, "default"));
        suspectsText.setFontScale(4, 4);
        this.table.row();


        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add(weaponsText = new Label("WEAPONS", skin, "default"));
        weaponsText.setFontScale(4, 4);
        this.table.row();


        this.table.add(new Label("", skin));
        this.table.row();

        this.table.add(roomsText = new Label("ROOMS",skin, "default"));
        roomsText.setFontScale(4, 4);
        this.table.row();


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

}
