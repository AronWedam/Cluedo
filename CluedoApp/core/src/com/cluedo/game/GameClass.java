package com.cluedo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

//Test Nejc Branch
public class GameClass extends Game {
	SpriteBatch batch;
	public BitmapFont font;
	Texture img;

	private StartScreen startScreen;


	//when we start the game, this is the first screen that shows
	//Source: https://gamedev.stackexchange.com/questions/75324/android-libgdx-game-choosing-starting-activity
	@Override
	public void create () {
		this.setStartScreen();
	}

	//Method for setting the Startscreen
	public void setStartScreen(){
		startScreen = new StartScreen();
		setScreen(startScreen);
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
