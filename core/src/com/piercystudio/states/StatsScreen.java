/*
 * MenuScreen.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo, J. Rosales
 * 16/09/15
 * 
 * Crea estado de Menu.
 * 		Menu tiene siguientes opciones:
 * 			1. Nuevo juego
 * 			2. Continuar
 * 			3. Ver progreso 
 * 			4. Salir del juego
 * 
 */
package com.piercystudio.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.piercystudio.PiercyGame;
import com.piercystudio.handlers.JSkin;
import com.piercystudio.handlers.Save;

public class StatsScreen implements Screen{
	
	/* Gdx  */
	private PiercyGame game;
	private Stage myStage;
	private SpriteBatch batch;
	private Skin skin;
	private JSkin lblSkin;
	private TextureRegion logo;
	private Label lvlTitle, lvl, errTitle, err, lblResultado;
	
	public StatsScreen(PiercyGame game){
		
		create();
		this.game = game;
		
	}
	
	/* Crea menu con botones */
	public void create(){
		batch = new SpriteBatch();
		myStage = new Stage();
		Gdx.input.setInputProcessor(myStage);
		
		Texture textura = PiercyGame.res.getImage("logoMenu");
		logo = new TextureRegion(textura, 300, 150);
		
		/* Comienza creacion y colocacion de botones */
		lblSkin = new JSkin();
		skin = new Skin();
		
		Pixmap pixmap = new Pixmap(150, 50, Format.RGBA8888);
		pixmap.setColor(Color.MAROON);
		pixmap.fill();
		
		skin.add("white", new Texture(pixmap));
		
		BitmapFont bfont = new BitmapFont();
		skin.add("default", bfont);
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.CLEAR);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		
		textButtonStyle.font = skin.getFont("default");
		
		skin.add("default", textButtonStyle);
		
		final TextButton textButton = new TextButton("REGRESAR", textButtonStyle);
		textButton.setPosition(PiercyGame.WIDTH / 2 + 80, PiercyGame.HEIGHT / 2 - 100);
		myStage.addActor(textButton);
		
		
		lvlTitle = new Label("Nivel actual: ", lblSkin);
		lvlTitle.setVisible(true);
		
		lvl = new Label(String.valueOf(Save.gd.getCurrentLevel()), lblSkin);
		lvl.setVisible(true);
		
		errTitle = new Label("Errores cometidos: ", lblSkin);
		errTitle.setVisible(true);
		
		err = new Label(String.valueOf(Save.gd.getError()), lblSkin);
		err.setVisible(true);
		
		lblResultado = new Label(Save.gd.getResultado(), lblSkin);
		lblResultado.setVisible(true);

		
		/* Listeners de botones */
		
		textButton.addListener(new ChangeListener(){
			
			public void changed (ChangeEvent event, Actor actor){
				lvlTitle.setVisible(false);
				lvl.setVisible(false);
				errTitle.setVisible(false);
				err.setVisible(false);
				lblResultado.setVisible(false);
				
				game.setScreen(new MenuScreen(game));
			}
			
		});
		

	}

	public void show() { }

	/**
	 * Metodo de renderizado
	 * @param delta tiempo delta
     */
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.myStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		this.myStage.draw();
		draw();

	}

	/**
	 * Metodo de dibujado
	 */
	public void draw(){
		batch.begin();
		batch.draw(logo, PiercyGame.WIDTH / 2 , PiercyGame.HEIGHT / 2 + 100, 300, 150);
		batch.end();
		this.lvlTitle.setX(PiercyGame.WIDTH / 2 - 0 );
		this.lvlTitle.setY(PiercyGame.HEIGHT / 2  + 100);
		this.game.getMyStage().addActor(lvlTitle);
		
		this.lvl.setX(PiercyGame.WIDTH / 2 + 300 );
		this.lvl.setY(PiercyGame.HEIGHT / 2 + 100 );
		this.game.getMyStage().addActor(lvl);
		
		this.errTitle.setX(PiercyGame.WIDTH / 2 - 110 );
		this.errTitle.setY(PiercyGame.HEIGHT / 2  + 30);
		this.game.getMyStage().addActor(errTitle);
		
		this.err.setX(PiercyGame.WIDTH / 2 + 300 );
		this.err.setY(PiercyGame.HEIGHT / 2  + 30);
		this.game.getMyStage().addActor(err);
		
		this.lblResultado.setX(PiercyGame.WIDTH / 2 - 50 );
		this.lblResultado.setY(PiercyGame.HEIGHT / 2  - 20);
		this.game.getMyStage().addActor(lblResultado);
	}
	
	public void resize(int width, int height) { }

	/**
	 * Metodo ejecutado la pausar el juego
	 */
	public void pause() { }

	/**
	 * Metodo ejecutado al resumir el juego
	 */
	public void resume() { }

	/**
	 * Metodo ejecutado al  esconder el juego
	 */
	public void hide() { }

	/**
	 * Metodo para liberar memoria
	 */
	public void dispose() { 
		
		myStage.clear();
		skin.dispose();
		lblSkin.dispose();
	}
	
	

}
