/*
 * LevelSelectState.java
 * 
 * @author: Eric Mendoza
 * 16/09/15
 * 
 * Crea un menu para elegir los niveles del juego.
 * Permite elegir el nivel y envia al jugador al mismo.
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.piercystudio.PiercyGame;


public class LevelSelectState implements Screen{
    private PiercyGame game;
    private Stage myStage;
    private SpriteBatch batch;
    private Skin skin, skin2;
    private TextureRegion logo;
    private int world;

    public LevelSelectState(PiercyGame game, int world){
        this.world = world;
        this.game = game;
        batch = game.getBatch();
        myStage = game.getMyStage();
    }



    
    public void show(){

        Gdx.input.setInputProcessor(myStage);

        Texture textura = PiercyGame.res.getImage("logoMenu");
        logo = new TextureRegion(textura, 300, 150);

		/* Inicia m�gia */
        skin = new Skin();

        Pixmap pixmap = new Pixmap(70, 70, Format.RGBA8888);
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


        /* Creacion de botones nivel */
        final TextButton textButton = new TextButton(Integer.toString(world) + ".1", textButtonStyle);
        textButton.setPosition(200, PiercyGame.HEIGHT / 2 + 50);
        textButton.addListener(new levelListener());
        myStage.addActor(textButton);

        final TextButton textButton2 = new TextButton(Integer.toString(world) + ".2", textButtonStyle);
        textButton2.setPosition(400 - 35, PiercyGame.HEIGHT / 2 + 50);
        textButton2.addListener(new levelListener());
        myStage.addActor(textButton2);


        final TextButton textButton3 = new TextButton(Integer.toString(world) + ".3", textButtonStyle);
        textButton3.setPosition(530, PiercyGame.HEIGHT / 2 + 50);
        textButton3.addListener(new levelListener());
        myStage.addActor(textButton3);

        final TextButton textButton4 = new TextButton(Integer.toString(world) + ".4", textButtonStyle);
        textButton4.setPosition(200, PiercyGame.HEIGHT / 2 - 70);
        textButton4.addListener(new levelListener());
        myStage.addActor(textButton4);

        final TextButton textButton5 = new TextButton(Integer.toString(world) + ".5", textButtonStyle);
        textButton5.setPosition(400 - 35, PiercyGame.HEIGHT / 2 - 70);
        textButton5.addListener(new levelListener());
        myStage.addActor(textButton5);

        final TextButton textButton6 = new TextButton(Integer.toString(world)  + ".6", textButtonStyle);
        textButton6.setPosition(530, PiercyGame.HEIGHT / 2 - 70);
        textButton6.addListener(new levelListener());
        myStage.addActor(textButton6);

        /* Boton de regreso */
        skin2 = new Skin();

        Pixmap pixmap2 = new Pixmap(140, 50, Format.RGBA8888);
        pixmap2.setColor(Color.MAROON);
        pixmap2.fill();


        skin2.add("white", new Texture(pixmap2));

        BitmapFont bfont2 = new BitmapFont();
        skin2.add("default", bfont2);

        TextButtonStyle backButtonStyle = new TextButtonStyle();
        backButtonStyle.up = skin2.newDrawable("white", Color.DARK_GRAY);
        backButtonStyle.down = skin2.newDrawable("white", Color.DARK_GRAY);
        backButtonStyle.checked = skin2.newDrawable("white", Color.CLEAR);
        backButtonStyle.over = skin2.newDrawable("white", Color.LIGHT_GRAY);

        backButtonStyle.font = skin2.getFont("default");

        skin2.add("default", backButtonStyle);

        final TextButton backButton = new TextButton("Regresar", backButtonStyle);
        backButton.setPosition(30, 400);
        backButton.addListener(new backListener());
        myStage.addActor(backButton);


    }

    /* Listener de botones */
    private class levelListener extends ChangeListener{
        public void changed(ChangeEvent event, Actor actor) {
            TextButton sourceButton = (TextButton) event.getTarget();
            int level = Integer.parseInt(sourceButton.getLabel().getText().toString().substring(2,3));
            level = (world - 1) * 6 + level;
            game.setScreen(new LoadState(game, level));
        }
    }

    private class backListener extends ChangeListener{
        public void changed(ChangeEvent event, Actor actor) {
            game.setScreen(new WorldSelectState(game));
        }
    }




    public void resume() {

    }

    
    public void resize(int width, int height) {

    }

    
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.myStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        this.myStage.draw();
        batch.begin();
        batch.draw(logo, PiercyGame.WIDTH / 2 , PiercyGame.HEIGHT / 2 + 100, 300, 150);
        batch.end();
    }


    
    public void pause() {

    }

    
    public void hide() {

    }

   
    public void dispose() {
        skin.dispose();
        myStage.clear();
    }
}
