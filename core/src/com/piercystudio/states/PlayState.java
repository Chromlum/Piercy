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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.piercystudio.PiercyGame;
import com.piercystudio.entities.Jugador;
import com.piercystudio.entities.Moneda;
import com.piercystudio.handlers.GameInput;
import com.piercystudio.handlers.GameKey;
import com.piercystudio.handlers.JSkin;
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
	private Moneda[] monedasPrimerNivel;
	private TextureRegion bg;
	
	/* Current Level */
	private int currentLevel;
	private int currentCoins;
	private Label lblCurrentCoins, lblCantidadCoins, lblNivelText, lblNivel;
	private JSkin labelSkin;
	private int asignarMonedas;
	
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
		labelSkin = new JSkin();
		
		/* Mapa */
		map = new TmxMapLoader().load(PiercyGame.res.getLevel(currentLevel));
		renderer = new OrthogonalTiledMapRenderer(map);
		jugador = new Jugador(map);
		jugador.setPosition(70, 130);
		currentCoins = 0;
		lblCurrentCoins = new Label("Monedas", labelSkin);
		lblCantidadCoins = new Label("0", labelSkin);
		lblCurrentCoins.setVisible(true);
		lblCantidadCoins.setVisible(true);
		
		lblNivelText = new Label("Nivel: ", labelSkin);
		lblNivel = new Label(String.valueOf(currentLevel), labelSkin);
		lblNivelText.setVisible(true);
		lblNivel.setVisible(true);
		
		/* Monedas: Primer Nivel */
		if(currentLevel == 1)
			asignarMonedas = 5;
		if(currentLevel == 2)
			asignarMonedas = 4;
		monedasPrimerNivel = new Moneda[asignarMonedas];
		for(int i = 0; i < monedasPrimerNivel.length; i++){
			monedasPrimerNivel[i] = new Moneda(map);
			monedasPrimerNivel[i].setPosition(100+ (i*90), 130);
		}
		
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
		for(int i = 0; i < monedasPrimerNivel.length; i++){
			monedasPrimerNivel[i].update(Gdx.graphics.getDeltaTime());
		}
		handleInput();
		colisionJugadorMoneda(monedasPrimerNivel);
		sigNivel();
		Save.gd.setCurrentLevel(currentLevel);
		Save.save();
	}
	
	public void colisionJugadorMoneda(Moneda[] monedas){
		for(int i = 0; i < monedas.length; i ++){
			if(((int)jugador.getX() == monedas[i].getX()) || (jugador.getY() == monedas[i].getX())){
				monedas[i].setPosition(500, 1500);
				currentCoins += 1;
				lblCantidadCoins.setText(String.valueOf(currentCoins));
			}
		}
	}
	
	public void sigNivel(){
		if(currentLevel == 1){
			if(currentCoins == monedasPrimerNivel.length){
				jugador.setRight(false);
				currentLevel += 1;
				this.lblCantidadCoins.setVisible(false);
				this.lblCurrentCoins.setVisible(false);
				this.lblNivelText.setVisible(false);
				this.lblNivel.setVisible(false);
				Save.gd.setCurrentLevel(currentLevel);
				Save.save();
				game.setScreen(new PlayState(game, currentLevel));
			}
		}
	}
	
	public void handleInput(){
		if(GameKey.isPressed(GameKey.ESC)){
			game.setScreen(new MenuScreen(game));
		}
		
		jugador.setRight(true);
		
	}
	
	public void draw(){
		fondo.begin();
		fondo.draw(bg, 0, 0, 600, 480);
		fondo.end();
		renderer.setView(camera);
		renderer.render();
		
		for(int i = 0; i < monedasPrimerNivel.length; i++){
			monedasPrimerNivel[i].draw(batch);
		}
		jugador.draw(batch);
		
		/* Texto */
		this.lblCurrentCoins.setX(PiercyGame.WIDTH / 2  + 300);
		this.lblCurrentCoins.setY(PiercyGame.HEIGHT / 2  + 270);
		this.game.getMyStage().addActor(lblCurrentCoins);
		
		this.lblCantidadCoins.setX(PiercyGame.WIDTH / 2  + 500);
		this.lblCantidadCoins.setY(PiercyGame.HEIGHT / 2  + 270);
		this.game.getMyStage().addActor(lblCantidadCoins);
		
		this.lblNivelText.setX(PiercyGame.WIDTH / 2 - 150);
		this.lblNivelText.setY(PiercyGame.HEIGHT / 2 + 270);
		this.game.getMyStage().addActor(lblNivelText);
		
		this.lblNivel.setX(PiercyGame.WIDTH / 2 );
		this.lblNivel.setY(PiercyGame.HEIGHT / 2 + 270);
		this.game.getMyStage().addActor(lblNivel);
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
