package com.cluedo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClass extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	public GameClass () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new Cluedo(this));
    }

	@Override
	public void create () {
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();

	}
}