/*
 * LevelSelectState.java
 * 
 * @author: Eric Mendoza
 * 16/09/15
 * 
 * Crea un menu para elegir el capitulo del juego.
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


public class WorldSelectState implements Screen{
    private PiercyGame game;
    private Stage myStage;
    private SpriteBatch batch;
    private Skin skin;
    private TextureRegion logo;

    public WorldSelectState(PiercyGame game){
        create();
        this.game = game;
    }

    public void create(){
        batch = new SpriteBatch();
        myStage = new Stage();
        Gdx.input.setInputProcessor(myStage);

        Texture textura = PiercyGame.res.getImage("logoMenu");
        logo = new TextureRegion(textura, 300, 150);

		/* Inicia m?gia */
        skin = new Skin();

        Pixmap pixmap = new Pixmap(100, 200, Format.RGBA8888);
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
        final TextButton textButton = new TextButton("Introduccion", textButtonStyle);
        textButton.setPosition(134, PiercyGame.HEIGHT / 2 - 75);
        textButton.addListener(new levelListener());
        myStage.addActor(textButton);

        final TextButton textButton2 = new TextButton("Variables", textButtonStyle);
        textButton2.setPosition(278, PiercyGame.HEIGHT / 2 - 75);
        textButton2.addListener(new levelListener());
        myStage.addActor(textButton2);

        final TextButton textButton3 = new TextButton("Condiciones", textButtonStyle);
        textButton3.setPosition(422, PiercyGame.HEIGHT / 2 - 75);
        textButton3.addListener(new levelListener());
        myStage.addActor(textButton3);

        final TextButton textButton4 = new TextButton("Ciclos", textButtonStyle);
        textButton4.setPosition(566, PiercyGame.HEIGHT / 2 - 75);
        textButton4.addListener(new levelListener());
        myStage.addActor(textButton4);
    }

    /* Listener de botones */
    private class levelListener extends ChangeListener{
        public void changed(ChangeEvent event, Actor actor) {
            TextButton sourceButton = (TextButton) event.getTarget();
            String text = sourceButton.getLabel().getText().toString();
            int world;

            if (text.equals("Introduccion")){
                world = 1;
            } else if (text.equals("Variables")){
                world = 2;
            } else if (text.equals("Condiciones")){
                world = 3;
            } else if (text.equals("Ciclos")){
                world = 4;
            } else {
                world = 1;
            }

            game.setScreen(new LevelSelectState(game, world));
        }
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
