package com.piercystudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuScreen implements Screen{
	
	private PiercyGame game;
	private Stage myStage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureRegion bg;
	
	public MenuScreen(PiercyGame game){
		this.game = game;
		camera = this.game.getCamera();
		batch = this.game.getBatch();
		myStage = this.game.getMyStage();
		Gdx.input.setInputProcessor(myStage);
	}

	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		draw();
		
	}
	
	public void update(){
		batch.setProjectionMatrix(camera.combined);
	}
	
	public void draw(){
		batch.begin();
		batch.end();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void resume() {
		// TODO Auto-generated method stub
		
	}

	public void hide() {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	

}
