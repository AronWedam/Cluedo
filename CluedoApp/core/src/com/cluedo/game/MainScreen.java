package com.cluedo.game;

import com.badlogic.gdx.Game;

class MainScreen extends Game {

    @Override
    public void create() {
        this.setScreen(new StartScreen(new GameClass()));
    }
}
