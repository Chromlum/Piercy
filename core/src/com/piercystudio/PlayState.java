package com.piercystudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class PlayState implements Screen{
	
	private PiercyGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	public PlayState(PiercyGame game){
		this.game = game;
		camera = this.game.getCamera();
		batch = this.game.getBatch();
		Gdx.input.setInputProcessor(new GameInput());
	}

	public void show() { }

	public void render(float delta) { 
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		draw();
	}
	
	public void update(){
		batch.setProjectionMatrix(camera.combined);
	}
	
	public void draw(){
		
	}

	public void resize(int width, int height) { }

	public void pause() { }

	public void resume() { }

	public void hide() { }

	public void dispose() { }

	
}
