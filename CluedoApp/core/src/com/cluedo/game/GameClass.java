package com.cluedo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

//Test Nejc Branch
public class GameClass extends Game {
	SpriteBatch batch;
	Texture img;

	//when we start the game, this is the first screen that shows
	//Source: https://gamedev.stackexchange.com/questions/75324/android-libgdx-game-choosing-starting-activity
	@Override
	public void create () {
		setScreen(new StartScreen());
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
