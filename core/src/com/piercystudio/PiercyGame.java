/*
 * PiercyGame.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo
 * Fecha: 16/09/15
 * 
 * Descripccion: Clase principal del juego es llamada del clase main
 * 
 */
package com.piercystudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.piercystudio.handlers.Content;
import com.piercystudio.states.MenuScreen;

public class PiercyGame extends Game {
	private Stage myStage;
	private OrthographicCamera camera;
	private SpriteBatch batch; // batch para dibuajr
	public static int WIDTH = 480;
	public static int HEIGHT = 320;
	public static Content res; // Recursos
	public static final String TITULO = "Piercy";
	public static final String VERSION = "0.9";

	/**
	 * Metodo que se ejecuta al crear el juego
	 */
	public void create () {
		myStage = new Stage();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		batch = new SpriteBatch();
		res = new Content();

		// Carga de recursos

		/* Graficos */
		res.loadImage("logoMenu", "logoMenu.png");
		res.loadImage("player", "Sprites/sprites.png");
		res.loadImage("coin", "Sprites/coinsprite.png");
		res.loadImage("box","Sprites/box.png");
		res.loadImage("loadingbar", "Sprites/loadingbar.png");
		res.loadImage("bg", "Maps/JnRLayer01.png");
		
		/* Mapas */
		res.loadLevel(1, "Maps/map1.tmx");
		res.loadLevel(2, "Maps/map2.tmx");
		res.loadLevel(3, "Maps/map3.tmx");
		res.loadLevel(4, "Maps/map4.tmx");
		res.loadLevel(5, "Maps/map5.tmx");
		res.loadLevel(6, "Maps/map6.tmx");
		res.loadLevel(7, "Maps/map7.tmx");
		res.loadLevel(8, "Maps/map8.tmx");
		res.loadLevel(9, "Maps/map9.tmx");
		res.loadLevel(10, "Maps/map10.tmx");
		res.loadLevel(11, "Maps/map11.tmx");
		res.loadLevel(12, "Maps/map12.tmx");
		res.loadLevel(13, "Maps/map13.tmx");
		res.loadLevel(14, "Maps/map14.tmx");
		res.loadLevel(15, "Maps/map15.tmx");
		res.loadLevel(16, "Maps/map16.tmx");
		res.loadLevel(17, "Maps/map17.tmx");
		res.loadLevel(18, "Maps/map18.tmx");
		res.loadLevel(19, "Maps/map19.tmx");
		res.loadLevel(20, "Maps/map20.tmx");
		res.loadLevel(21, "Maps/map21.tmx");
		res.loadLevel(22, "Maps/map22.tmx");
		res.loadLevel(23, "Maps/map23.tmx");
		res.loadLevel(24, "Maps/map24.tmx");
		
		/* Musica */
		res.loadMusic("bgmusic", "Music/ostGame1.mp3");
		res.loadMusic("menuMusic", "Music/ostMenu.mp3");
		res.loadSound("coin", "Music/coin.mp3");
		res.loadSound("jump", "Music/sfxJump.wav");
		res.loadSound("nextLevel", "Music/sfxNext.wav");
		res.loadSound("select", "Music/sfxSelect.wav");
		
		setScreen(new MenuScreen(this));
		
	}


	/**
	 * Metodo para renderizar
	 */
	public void render () {
		super.render();
		myStage.act();
		myStage.draw();
	}

	// Getter de la camara
	public OrthographicCamera getCamera() {
		return camera;
	}

	// Getter del batch
	public SpriteBatch getBatch() {
		return batch;
	}


	// Getter del stage
	public Stage getMyStage() {
		return myStage;
	}

	// Obtener version del juego
	public String getVersion(){
		return VERSION;
	}

	/**
	 * Metodo para liberar memoria
	 */
	@Override
	public void dispose(){
		super.dispose();
		this.batch.dispose();
		this.myStage.dispose();
	}

	/**
	 * Metodo para cambiar pantalla
	 * @param screen
     */
    @Override
    public void setScreen(Screen screen){
        if (this.screen != null) this.screen.dispose();
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

    }


}
