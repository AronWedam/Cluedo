package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class Notebook {

    private Table table;
    private ScrollPane pane;
    private Object Cluedo;
    private CardHandOut cardHandOut;
    final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    public void setNotebook(Notebook notebook, Stage stage) {

        this.table = new Table((Skin) Cluedo);
        this.table.defaults().padLeft(5).align(Align.left);

        if (this.pane != null) {
            this.pane.remove();
        }

        this.pane = new ScrollPane(this.table, skin);

        this.table.add(new Label("Notebook: ", skin));
        this.table.row();
        this.table.add(new Label("(your cards in hand)", skin, "default-green"));
        this.table.row();
        this.table.add(new Label("when shown a card,", skin, "default-red"));
        this.table.row();
        this.table.add(new Label("you can mark it off below!", skin, "default-red"));

        this.table.row();
        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add(new Label("SUSPECTS", skin, "default-yellow"));


        this.table.row();
        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add((CharSequence) new Label("WEAPONS", skin, "default-yellow"));


        this.table.row();
        this.table.add(new Label("", skin));
        this.table.row();
        this.table.add((CharSequence) new Label("ROOMS",skin, "default-yellow"));

        pane.setBounds(32 * 8 + 32 * 24 + 2, 0, 32 * 8, 32 * 25);
        //stage.addActor(pane);

    }

}
