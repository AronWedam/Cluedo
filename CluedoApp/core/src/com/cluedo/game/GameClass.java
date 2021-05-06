package com.cluedo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


=======
public class GameClass extends ApplicationAdapter implements GestureDetector.GestureListener{

	GestureDetector gestureDetector;

	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	private float unitScale = 1/32f;

	private TiledMap map;

	float currentZoom;
	
	@Override
	public void create () {

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		map = new TmxMapLoader().load("maps/map1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);

		gestureDetector = new GestureDetector(this);

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 400, 800);
		camera.rotate(180);

		Gdx.input.setInputProcessor(gestureDetector);

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.setView(camera);
		renderer.render();
		camera.update();
	}

	@Override
	public void dispose () {
		map.dispose();

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		currentZoom = camera.zoom;
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		camera.translate(deltaX/2, deltaY/2);
		camera.update();
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		Gdx.app.log("INFO", "panStop");
		currentZoom = camera.zoom;
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		camera.zoom = (initialDistance / distance) * currentZoom;
		camera.update();
		return true;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

	}
}
