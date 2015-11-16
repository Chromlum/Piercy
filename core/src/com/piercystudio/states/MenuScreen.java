/*
 * MenuScreen.java
 * 
 * @author: G. Brolo
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

import com.badlogic.gdx.graphics.*;
import org.python.util.PythonInterpreter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.piercystudio.PiercyGame;
import com.piercystudio.handlers.Save;

public class MenuScreen implements Screen{
	
	/* Gdx  */
	private PiercyGame game;
	private Stage myStage;
	private SpriteBatch batch;
	private Skin skin;
	private TextureRegion logo, bgImg;
    private Texture bg;
	
    public PythonInterpreter python;
    
	public MenuScreen(PiercyGame game){

		this.game = game;
		batch = game.getBatch();
		myStage = game.getMyStage();
		/* Cargar / crear savefile */
		
		Save.load();
		if(Save.gd.getCurrentLevel() == 0){
			Save.gd.init();
		}

		
		//  PiercyGame.res.getMusic("bgmusic").setLooping(true);
		//  PiercyGame.res.getMusic("bgmusic").play();
		
	}
	
	/* Crea menu con botones */
    
	public void show(){

		Gdx.input.setInputProcessor(myStage);
		
		Texture textura = PiercyGame.res.getImage("logoMenu");
		logo = new TextureRegion(textura, 300, 150);

        bg = PiercyGame.res.getImage("bgMenu");

        textura = PiercyGame.res.getImage("bg");
        bgImg = new TextureRegion(textura, 400, 240);
		
		/* Comienza creacion y colocacion de botones */
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
		
		final TextButton textButton = new TextButton("JUEGO NUEVO", textButtonStyle);
		textButton.setPosition(PiercyGame.WIDTH / 2 + 80, PiercyGame.HEIGHT / 2 + 50);
		myStage.addActor(textButton);
		
		final TextButton textButton2 = new TextButton("CONTINUAR", textButtonStyle);
		textButton2.setPosition(PiercyGame.WIDTH / 2 + 80, PiercyGame.HEIGHT / 2);
		myStage.addActor(textButton2);
		
		final TextButton textButton3 = new TextButton("ESTADISTICAS", textButtonStyle);
		textButton3.setPosition(PiercyGame.WIDTH / 2 + 80, PiercyGame.HEIGHT / 2 - 50);
		myStage.addActor(textButton3);
		
		final TextButton textButton4 = new TextButton("SALIR", textButtonStyle);
		textButton4.setPosition(PiercyGame.WIDTH / 2 + 80, PiercyGame.HEIGHT / 2 - 100);
		myStage.addActor(textButton4);
		
		/* Listeners de botones */
		
		textButton.addListener(new ChangeListener(){
			
			public void changed (ChangeEvent event, Actor actor){
				game.setScreen(new WorldSelectState(game));
			}
			
		});
		
		textButton2.addListener(new ChangeListener(){
			
			public void changed (ChangeEvent event, Actor actor){
				game.setScreen(new LoadState(game, Save.gd.getCurrentLevel()));
			}
			
		});
		
		textButton3.addListener(new ChangeListener(){
			
			public void changed (ChangeEvent event, Actor actor){
				game.setScreen(new StatsScreen(game));
			}
			
		});
		
		textButton4.addListener(new ClickListener(){
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				Gdx.app.exit();
				super.touchUp(event, x, y, pointer, button);
			}
			
		});

	}


	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.myStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		this.myStage.draw();
        batch.begin();
        batch.draw(bgImg, 0, 0 , 800, 480);
        batch.draw(logo, PiercyGame.WIDTH / 2 , PiercyGame.HEIGHT / 2 + 100, 300, 150);
        batch.end();

	}
	
	public void draw(){

	}
	
	public void resize(int width, int height) { }

	public void pause() { }

	public void resume() { }

	public void hide() { }

	public void dispose() {
		skin.dispose();
		myStage.clear();
	}
	
	

}
