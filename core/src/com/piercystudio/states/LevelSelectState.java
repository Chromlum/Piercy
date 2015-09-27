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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.piercystudio.PiercyGame;
import com.piercystudio.handlers.Save;


public class LevelSelectState implements Screen{
    private PiercyGame game;
    private Stage myStage;
    private SpriteBatch batch;
    private Skin skin;
    private TextureRegion logo;

    public LevelSelectState(PiercyGame game){
        create();
        this.game = game;
    }

    public void create(){
        batch = new SpriteBatch();
        myStage = new Stage();
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

        /* Listener de botones */
        ChangeListener levelListener = new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                TextButton sourceButton = (TextButton) event.getTarget();
                String level = sourceButton.getLabel().getText().toString();
                game.setScreen(new LoadState(game, Integer.parseInt(level)));
            }

        };

        /* Creacion de botones */
        final TextButton textButton = new TextButton("1", textButtonStyle);
        textButton.setPosition(200, PiercyGame.HEIGHT / 2 + 50);
        textButton.addListener(levelListener);
        myStage.addActor(textButton);

        final TextButton textButton2 = new TextButton("2", textButtonStyle);
        textButton2.setPosition(400-35, PiercyGame.HEIGHT / 2 + 50);
        textButton2.addListener(levelListener);
        myStage.addActor(textButton2);


        final TextButton textButton3 = new TextButton("3", textButtonStyle);
        textButton3.setPosition(530, PiercyGame.HEIGHT / 2 + 50);
        textButton3.addListener(levelListener);
        myStage.addActor(textButton3);

        final TextButton textButton4 = new TextButton("4", textButtonStyle);
        textButton4.setPosition(200, PiercyGame.HEIGHT / 2 - 70);
        textButton4.addListener(levelListener);
        myStage.addActor(textButton4);

        final TextButton textButton5 = new TextButton("5", textButtonStyle);
        textButton5.setPosition(400-35, PiercyGame.HEIGHT / 2 - 70);
        textButton5.addListener(levelListener);
        myStage.addActor(textButton5);

        final TextButton textButton6 = new TextButton("6", textButtonStyle);
        textButton6.setPosition(530, PiercyGame.HEIGHT / 2 - 70);
        textButton6.addListener(levelListener);
        myStage.addActor(textButton6);


    }

    @Override
    public void show() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.myStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        this.myStage.draw();
        draw();
    }

    private void draw() {
        batch.begin();
        batch.draw(logo, PiercyGame.WIDTH / 2 , PiercyGame.HEIGHT / 2 + 100, 300, 150);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        myStage.dispose();
        logo.getTexture().dispose();
        skin.dispose();
        batch.dispose();
    }
}
