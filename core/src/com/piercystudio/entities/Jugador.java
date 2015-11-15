
/*
 * Jugador.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo
 * 16/09/15
 * 
 * Crea al jugador principal.
 * 
 */
package com.piercystudio.entities;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.piercystudio.PiercyGame;

public class Jugador extends PiercyObject{

    private double distanciaAcumulada;
    private double distanciaPermitida;
    private Queue<Movimientos> actionQueue;
    private boolean actividad;
    private int currentFrame;
    private boolean hasFinished;
    private boolean dead;
    private boolean brincar;
    private TiledMap mapa;
    private TiledMapTileLayer layer;
    float dt;

    public enum Movimientos{
        DERECHA,
        IZQUIERDA,
        BRINCAR,
        DESTRUIR,
        BRINCARD,
        BRINCARI
    }


	public Jugador(Sprite sprite, TiledMap mapa) {
		super(sprite);
        dt = 0;
        this.mapa = mapa;
        layer =(TiledMapTileLayer) mapa.getLayers().get(1);
        // carga texturas (sprites)
        Texture tex = PiercyGame.res.getImage("player");
        TextureRegion[] sprites = new TextureRegion[4];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new TextureRegion(tex, i * 32, 0, 32, 32);
        }

        animation = new Animation(1f/5f, sprites);


        actionQueue = new LinkedList<Movimientos>();
        actividad = false;
        hasFinished = true;
        setSize(50, 50);
	}

	@Override
	public void draw(Batch batch) {
        dt += Gdx.graphics.getDeltaTime();
        setRegion(animation.getKeyFrame(dt, true));
        super.draw(batch);
	}

	@Override
	public void update(float dt) {
        velocidad.y -= g * dt;
        velocidad.x = velocidadMax;
        if(velocidad.y > velocidadMax){
            velocidad.y = velocidadMax;
        }else if(velocidad.y < velocidadMax){
            velocidad.y = -velocidadMax;
        }


        boolean colx = false;
        boolean coly = false;

        currentFrame++;
        if(currentFrame > 3){
            currentFrame = 0;
        }

        float posAx = getX();
        float posAy = getY();

        if(!collidesY())
            setY(getY() + velocidad.y * dt);
        else velocidad.y = 0;

        System.out.println(velocidad.y);
        setX(getX() + velocidad.x * dt);

	}

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = layer.getCell((int) (x / layer.getTileWidth()), (int) (y / layer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    private boolean collidesY(){
        float increment = layer.getTileHeight();
        increment = getHeight() < increment ? getHeight() / 2 : increment / 2;

        for(float step = 0; step <= getWidth(); step += increment)
            if(isCellBlocked(getX() + step, getY()))
                return true;
        return false;
    }

    

    public boolean isActive(){
        return actividad;
    }

    public void addAction(Movimientos s){
        actionQueue.add(s);
    }

    public void setActive(boolean activo){
        actividad = activo;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
