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
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.piercystudio.handlers.Content;
import com.piercystudio.states.MenuScreen;

public class PiercyGame extends Game {
	private Stage myStage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	public static int WIDTH = 480;
	public static int HEIGHT = 320;
	public static Content res;
	public static final String TITULO = "Piercy";
	public static final String VERSION = "0.0.1";
	
	
	public void create () {
		myStage = new Stage();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		batch = new SpriteBatch();
		res = new Content();
		
		/* Graficos */
		res.loadImage("logoMenu", "logoMenu.png");
		res.loadImage("player", "Sprites/sprites.png");
		res.loadImage("coin", "Sprites/coinsprite.png");
		res.loadImage("box","Sprites/box.png");
		res.loadImage("bg", "Maps/JnRLayer01.png");
		
		/* Mapas */
		res.loadLevel(1, "Maps/map1.tmx");
		res.loadLevel(2, "Maps/map2.tmx");
		
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

	public String getVersion(){
		return VERSION;
	}
	@Override
	public void dispose(){
		super.dispose();
		this.batch.dispose();
		this.myStage.dispose();
	}

}
