/*
 * PiercyGame.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Crea el juego.
 * 
 */
package com.piercystudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PiercyGame extends Game {
	private Stage myStage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	public static int WIDTH = 480;
	public static int HEIGHT = 320;
	public static Content res;
	
	
	public void create () {
		myStage = new Stage();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		batch = new SpriteBatch();
		res = new Content();
		
		/* Graficos */
		res.loadImage("logoMenu", "logoMenu.png");
		
		setScreen(new MenuScreen(this));
		
	}


	public void render () {
		super.render();
		myStage.act();
		myStage.draw();
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Stage getMyStage() {
		return myStage;
	}
}
