package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

public class Notebook {

    public Table table;
    private ScrollPane pane;
    final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

    public Notebook() {
        this.table = new Table(skin);
        this.table.defaults().padLeft(5).align(Align.left);
        this.pane = new ScrollPane(this.table, skin);

        this.table.add(new Label("Notebook: ", skin));
        this.table.row();
        this.table.add(new Label("(your cards in hand)", skin, "default"));
        this.table.row();
        this.table.add(new Label("when shown a card,", skin, "default"));
        this.table.row();
        this.table.add(new Label("you can mark it off below!", skin, "default"));

        this.table.row();
        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add(new Label("SUSPECTS", skin, "default"));


        this.table.row();
        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add(new Label("WEAPONS", skin, "default"));



        this.table.row();
        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add(new Label("ROOMS",skin, "default"));

        this.table.row();
        TextButton btnDice;
        this.table.add(btnDice = new TextButton("Dice", skin, "default"));
        btnDice.setSize(20, 20);
        this.table.row();

        this.table.row();
        TextButton btnAccusation;
        this.table.add(btnDice = new TextButton("Accusation", skin, "default"));
        btnDice.setSize(20, 20);
        this.table.row();

        this.table.row();
        TextButton btnHelp;
        this.table.add(btnDice = new TextButton("Help", skin, "default"));
        btnDice.setSize(20, 20);
        this.table.row();


        pane.setActor(this.table);

    }


    public ScrollPane getPane(){
        return pane;
    }

}
