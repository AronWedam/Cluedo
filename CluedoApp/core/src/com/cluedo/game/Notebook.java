package com.cluedo.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class Notebook {

    private Table table;
    private ScrollPane pane;
    private Object GameClass;
    private CardHandOut cardHandOut;

    public Notebook setNotebook() {

        this.table = new Table((Skin) GameClass);
        this.table.defaults().padLeft(5).align(Align.left);

        if (this.pane != null) {
            this.pane.remove();
        }

        this.pane = new ScrollPane(this.table, (Skin) GameClass);

        this.table.add(new Label("Notebook: ", (Skin) GameClass));
        this.table.row();
        this.table.add(new Label("(your cards in hand)", (Skin) GameClass, "default-green"));
        this.table.row();
        this.table.add(new Label("when shown a card,", (Skin) GameClass, "default-red"));
        this.table.row();
        this.table.add(new Label("you can mark it off below!", (Skin) GameClass, "default-red"));

        this.table.row();
        this.table.add(new Label("", (Skin) GameClass));
        this.table.row();
        this.table.add(new Label("SUSPECTS", (Skin) GameClass, "default-yellow"));


        this.table.row();
        this.table.add(new Label("", (Skin) GameClass));
        this.table.row();
        this.table.add((CharSequence) new Label("WEAPONS", (Skin) GameClass), "default-yellow");


        this.table.row();
        this.table.add(new Label("", (Skin) GameClass));
        this.table.row();
        this.table.add((CharSequence) new Label("ROOMS", (Skin) GameClass), "default-yellow");

        return this;

    }

}
