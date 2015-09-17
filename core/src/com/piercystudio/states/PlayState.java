/*
 * PlayState.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Estado destinado a poder jugar. Es en este estado en donde se juega.
 * 
 */
package com.piercystudio.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.piercystudio.PiercyGame;
import com.piercystudio.entities.Jugador;
import com.piercystudio.handlers.GameInput;
import com.piercystudio.handlers.GameKey;
import com.piercystudio.handlers.Save;

public class PlayState implements Screen{
	/* Gdx */
	private PiercyGame game;
	private OrthographicCamera camera, fondoCam;
	private SpriteBatch batch, fondo;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private Skin skin;
	private Stage myStage;
	private Jugador jugador;
	private TextureRegion bg;
	
	/* Current Level */
	private int currentLevel;
	
	public PlayState(PiercyGame game, int level){
		this.game = game;
		currentLevel = level;
		myStage = this.game.getMyStage();
		Gdx.input.setInputProcessor(myStage);
		camera = this.game.getCamera();
		fondoCam = new OrthographicCamera();
		fondoCam.setToOrtho(false, PiercyGame.WIDTH, PiercyGame.HEIGHT);
		Texture tex = PiercyGame.res.getImage("bg");
		bg = new TextureRegion(tex, 400, 240);
		batch = this.game.getBatch();
		fondo = new SpriteBatch();
		Gdx.input.setInputProcessor(new GameInput());
		skin = new Skin();
		
		/* Mapa */
		map = new TmxMapLoader().load(PiercyGame.res.getLevel(currentLevel));
		renderer = new OrthogonalTiledMapRenderer(map);
		jugador = new Jugador(map);
		jugador.setPosition(70, 130);
		
		
	}

	public void show() { }

	public void render(float delta) { 
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.myStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		this.myStage.draw();
		seguirJugador();
		update();
		draw();
	}
	
	/* Camara sigue al jugador */
	public void seguirJugador(){
		camera.position.x = (int) jugador.getX() + PiercyGame.WIDTH / 20;
		if (camera.position.x < camera.viewportWidth / 2) {
			camera.position.x = camera.viewportWidth / 2;
		}
		if (camera.position.x > 1600 - camera.viewportWidth / 2) {
			camera.position.x = 1600 - camera.viewportWidth / 2;
		}
		camera.position.y = (int) jugador.getY() + PiercyGame.HEIGHT / 20;
		if (camera.position.y < camera.viewportHeight / 2) {
			camera.position.y = camera.viewportHeight / 2;
		}
		if (camera.position.y > 1600 - camera.viewportHeight / 2) {
			camera.position.y = camera.viewportHeight / 2;
		}
		camera.update();
	}
	
	public void update(){
		batch.setProjectionMatrix(camera.combined);
		fondo.setProjectionMatrix(fondoCam.combined);
		jugador.update(Gdx.graphics.getDeltaTime());
		handleInput();
		Save.gd.setCurrentLevel(currentLevel);
		Save.save();
	}
	
	public void handleInput(){
		if(GameKey.isPressed(GameKey.ESC)){
			game.setScreen(new MenuScreen(game));
		}
	}
	
	public void draw(){
		fondo.begin();
		fondo.draw(bg, 0, 0, 600, 480);
		fondo.end();
		renderer.setView(camera);
		renderer.render();
		jugador.draw(batch);
	}

	public void resize(int width, int height) { }

	public void pause() { }

	public void resume() { }

	public void hide() { }

	public void dispose() { 
		myStage.dispose();
		skin.dispose();
	}

	
}
