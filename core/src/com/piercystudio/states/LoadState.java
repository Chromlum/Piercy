/*
 * LoadState.java
 * 
 * @Author: G. Brolo
 * 
 * 19/09/15
 * 
 * Pantalla desplegada antes de cargar cualquier nivel.
 */
package com.piercystudio.states;

import org.python.util.PythonInterpreter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.piercystudio.PiercyGame;
import com.piercystudio.handlers.Animation;
import com.piercystudio.handlers.JSkin;

public class LoadState implements Screen{
	
	private PiercyGame game;
	private Stage myStage;
	private JSkin skin;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Animation animation;
	private Label loading;
	
	public LoadState(final PiercyGame game, final int lvl){
		
		this.game = game;
		camera = this.game.getCamera();
		batch = this.game.getBatch();
		myStage = this.game.getMyStage();
		Gdx.input.setInputProcessor(myStage);
		skin = new JSkin();
		
		/* Animacion barra */
		Texture tex = PiercyGame.res.getImage("loadingbar");
		TextureRegion[] sprites = new TextureRegion[4];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 32, 0, 128, 32);
		}
		animation = new Animation();
		animation.setFrames(sprites, 1 / 10f);
		
		/* Loading text */
		loading = new Label("Cargando", skin);
		loading.setVisible(true);
		
		new Thread(new Runnable(){
			
			public void run(){
				@SuppressWarnings("resource")
				final PythonInterpreter python = new PythonInterpreter();
				Gdx.app.postRunnable(new Runnable(){
					public void run(){
						PlayState gameScreen = new PlayState(game, lvl);
						gameScreen.python = python;
						game.setScreen(gameScreen);
						loading.setVisible(false);
					}
				});
			}
		}).start();
		
	}
	
	public void render(float delta){
		
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 0.15f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.myStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		this.myStage.draw();
		update();
		draw();
		
	}
	
	public void update() {
		batch.setProjectionMatrix(camera.combined);
		animation.update(Gdx.graphics.getDeltaTime());
	}
	
	public void draw() {
		batch.begin();
		batch.draw(animation.getFrames(), PiercyGame.WIDTH / 2
				- animation.getFrames().getRegionWidth() / 2, PiercyGame.HEIGHT
				/ 2 + animation.getFrames().getRegionHeight(), 128, 32);
		batch.end();
		this.loading.setX(PiercyGame.WIDTH / 2 + 75);
		this.loading.setY(PiercyGame.HEIGHT / 2  + 200);
		this.game.getMyStage().addActor(loading);
		
	}
	
	public void show(){}
	
	public void resize(int width, int height){}
	
	public void hide(){}
	
	public void pause(){}
	
	public void resume(){}
	
	public void dispose(){}

}
