package com.cluedo.game;

import com.badlogic.gdx.Game;

public class MainScreen extends Game {

    @Override
    public void create() {
        if (this.getScreen() == null) {
            StartScreen startScreen = new StartScreen(new GameClass(), this);
            this.setScreen(startScreen);
        }
    }
}
