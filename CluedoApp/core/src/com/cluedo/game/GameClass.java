package com.cluedo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClass extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	public GameClass () {

    }

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		try {
			this.setScreen(new Cluedo(this));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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