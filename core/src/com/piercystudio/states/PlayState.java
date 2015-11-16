
/*
 * PlayState.java
 * 
 * @author: J. Custodio, G. Brolo
 * 16/09/15
 * 
 * Estado destinado a poder jugar. Es en este estado en donde se juega.
 * 
 */
package com.piercystudio.states;

import java.io.InputStream;
import java.io.StringWriter;

import com.badlogic.gdx.graphics.g2d.Sprite;
import org.python.util.PythonInterpreter;

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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.piercystudio.PiercyGame;
import com.piercystudio.entities.Jugador;
import com.piercystudio.entities.Jugador.Movimientos;
import com.piercystudio.handlers.GameKey;
import com.piercystudio.handlers.JSkin;
import com.piercystudio.handlers.Save;


public class PlayState implements Screen{
	
	/* Gdx */
	private PiercyGame game;
	private OrthographicCamera camera, fondoCamera;
	private SpriteBatch batch;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private Skin skin;
	private Stage myStage;
	private Jugador jugador;
	private TextureRegion bg;
	
	/* Current level */
	private int currentLevel;
	private int nextLevel;
	private int currentCoins;
    private int totalMonedas;
	private Label lblCurrentCoins, lblCantidadCoins, lblNivelText, lblNivel;
	private JSkin labelSkin;
	
	/* Atributos de consola */
	private TextButton botonConsola;
	private TextArea consola;
	public PythonInterpreter python;
	
	public PlayState(PiercyGame game, int level){
		this.game = game;
		currentLevel = level;
		nextLevel = currentLevel + 1;
		myStage = this.game.getMyStage();
		Gdx.input.setInputProcessor(myStage);
		camera = this.game.getCamera();
		fondoCamera = new OrthographicCamera();
		fondoCamera.setToOrtho(false, PiercyGame.WIDTH, PiercyGame.HEIGHT);
		Texture tex = PiercyGame.res.getImage("bg");
		bg = new TextureRegion(tex, 400, 240);
		batch = this.game.getBatch();
		Gdx.input.setInputProcessor(myStage);
		skin = new Skin();
		labelSkin = new JSkin();
		
		PiercyGame.res.getMusic("menuMusic").stop();
		PiercyGame.res.getMusic("bgmusic").setLooping(true);
		PiercyGame.res.getMusic("bgmusic").setVolume(0.5f);
		PiercyGame.res.getMusic("bgmusic").play();
		
		/* Mapa */
		map = new TmxMapLoader().load(PiercyGame.res.getLevel(currentLevel));
        totalMonedas = Integer.parseInt((String)map.getLayers().get(0).getProperties().get("coinNumber"));
		renderer = new OrthogonalTiledMapRenderer(map);
		jugador = new Jugador(new Sprite(PiercyGame.res.getImage("player")), map);
		currentCoins = 0;
		lblCurrentCoins = new Label("Monedas", labelSkin);
		lblCantidadCoins = new Label("0", labelSkin);
		lblCurrentCoins.setVisible(true);
		lblCantidadCoins.setVisible(true);
		
		lblNivelText = new Label("Nivel: ", labelSkin);
		lblNivel = new Label(String.valueOf(currentLevel), labelSkin);
		lblNivelText.setVisible(true);
		lblNivel.setVisible(true);
		

		
	}

	public void show() {
		Skin skin = new Skin(Gdx.files.internal("ConsoleSkin/uiskin.json"));
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		/* Consola */
		botonConsola = new TextButton("RUN", skin);
		consola = new TextArea("", skin);
		consola.setVisible(true);
		consola.setWidth((int)(width * 0.5));
		consola.setHeight((int)(height * 0.2));
		consola.setY(height - (int)(height * 0.35));
		botonConsola.setX(
				consola.getX() + (consola.getWidth() - botonConsola.getWidth()));
		botonConsola.setY(consola.getY() - botonConsola.getHeight());
		/*         */ 
		
		// Se aniaden al estadio
		myStage.addActor(botonConsola);
		myStage.addActor(consola);
		
		// Animaciones de entrada
		botonConsola.addAction(Actions.fadeOut(0));
		botonConsola.addAction(Actions.fadeIn(3));
		consola.addAction(Actions.fadeOut(0));
		consola.addAction(Actions.fadeIn(3));
		myStage.setKeyboardFocus(consola);
		
		consola.addListener(new InputListener(){
			
			public boolean keyTyped(InputEvent event, char character){
				syntaxHighlight(event, character);
				return true;
			}
		});
		
		botonConsola.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				interpretarCodigo(event, x, y);
			}
		});
		
	}
	
	public void syntaxHighlight(InputEvent e, char c){
		//TODO
	}
	
	public void interpretarCodigo(InputEvent event, float x, float y){
		new Thread(new Runnable() {
			   public void run() {
				   String[] comandos = null;
				   try{
						StringWriter sw = new StringWriter();
						InputStream is = Gdx.files.internal("Interpreter/JavaInterpreter.py").read();
						String codigo = consola.getText();
						python.exec("import sys");
						python.exec("sys.argv = []");
						python.exec("sys.argv.append('0')");
						python.exec("sys.argv.append(\"\"\"" + codigo + "\"\"\")");
						python.setOut(sw);
						python.execfile(is);
						python.close();
						comandos = sw.toString().split("\n");						
					}catch(Exception e){
						Gdx.app.log(PiercyGame.TITULO, "Script Python Error");
					}
				   final String[] resultados = comandos;
			      Gdx.app.postRunnable(new Runnable() {
			         public void run() {			        	 
			            for (int i = 0; i < resultados.length; i++){
							if (resultados[i].equals("fd")){
								jugador.addAction(Movimientos.DERECHA);
								Save.gd.setExp(1);
								Save.save();
							}else
							if(resultados[i].equals("bc")){
								jugador.addAction(Movimientos.IZQUIERDA);
								Save.gd.setExp(1);
								Save.save();
							}else
							if(resultados[i].equals("ju")){
								jugador.addAction(Movimientos.BRINCAR);
								Save.gd.setExp(1);
								Save.save();
							}else
							if(resultados[i].equals("jfd")){
								jugador.addAction(Movimientos.BRINCARD);
								Save.gd.setExp(1);
								Save.save();
							}else
							if(resultados[i].equals("jbc")){
								jugador.addAction(Movimientos.BRINCARI);
								Save.gd.setExp(1);
								Save.save();
							}if(resultados[i].equals("exm")){
								// jugador.setRight(false);
								lblCantidadCoins.setVisible(false);
								lblCurrentCoins.setVisible(false);
								lblNivelText.setVisible(false);
								lblNivel.setVisible(false);
								consola.setVisible(false);
								botonConsola.setVisible(false);
								PiercyGame.res.getMusic("bgmusic").stop();
								Save.gd.setCurrentLevel(currentLevel);
								Save.save();
								WorldSelectState nextLvl = new WorldSelectState(game);
								nextLvl.python = python;
								game.setScreen(nextLvl);
							}if(resultados[i].equals("help")){
								// mostrar ayuda
							}if(resultados[i].equals("musicOn")){
								PiercyGame.res.getMusic("bgmusic").setVolume(0.5f);
							}if(resultados[i].equals("musicOff")){
								PiercyGame.res.getMusic("bgmusic").setVolume(0);
							}else{
								// TODO AQUI MARCA LOS ERRORES
								Save.gd.addError(currentLevel);
								Save.save();
							}
						}
			            jugador.setActive(true);
			         }
			      });
			   }
			}).start();
	}

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
		jugador.update(Gdx.graphics.getDeltaTime());
		
		if(jugador.isDead() == true){
			sigNivelLogic();
		}
		
		handleInput();
		if (jugador.colisionMoneda()){
            currentCoins += 1;
            lblCantidadCoins.setText(String.valueOf(currentCoins));
            PiercyGame.res.getSound("coin").play();
            if(currentCoins == totalMonedas){
                sigNivelLogic();
            }
		}
		Save.gd.setCurrentLevel(currentLevel);
		Save.save();
	}

	public void sigNivelLogic(){
        jugador.setFacing(false);
        this.lblCantidadCoins.setVisible(false);
        this.lblCurrentCoins.setVisible(false);
        this.lblNivelText.setVisible(false);
        this.lblNivel.setVisible(false);
        Save.gd.setCurrentLevel(nextLevel);
        Save.save();
        if(jugador.isDead()) {
            PlayState lvl = new PlayState(game, currentLevel);
            lvl.python = this.python;
            game.setScreen(lvl);
        }else{
            PlayState lvl = new PlayState(game, nextLevel);
            lvl.python = this.python;
            game.setScreen(lvl);
        }
	}

	
	public void handleInput(){
		if(GameKey.isPressed(GameKey.ESC)){
			game.setScreen(new MenuScreen(game));
		}
		if(GameKey.isPressed(GameKey.ENTER)){
			//jugador.setRight(true);
		}
		
		
	}
	
	public void draw(){
		batch.setProjectionMatrix(fondoCamera.combined);
		batch.begin();
		batch.draw(bg, 0, 0, 600, 480);
		batch.end();
		batch.setProjectionMatrix(camera.combined);
		renderer.setView(camera);
		renderer.render();

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

	public void resize(int width, int height) {

    }

	public void pause() {
    }

	public void resume() {
    }

	public void hide() { }

	public void dispose() {
		skin.dispose();
		labelSkin.dispose();
	}

	
}
