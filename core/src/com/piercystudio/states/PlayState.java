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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.piercystudio.PiercyGame;
import com.piercystudio.handlers.GameInput;

public class PlayState implements Screen{
	/* Gdx */
	private PiercyGame game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private Skin skin;
	private Stage myStage;
	
	public PlayState(PiercyGame game){
		this.game = game;
		myStage = this.game.getMyStage();
		Gdx.input.setInputProcessor(myStage);
		camera = this.game.getCamera();
		batch = this.game.getBatch();
		Gdx.input.setInputProcessor(new GameInput());
		skin = new Skin();
		
		
	}

	public void show() { }

	public void render(float delta) { 
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.myStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		this.myStage.draw();
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

	public void dispose() { 
		myStage.dispose();
		skin.dispose();
	}

	
}
