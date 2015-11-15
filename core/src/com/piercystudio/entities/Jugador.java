
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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
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
    private ShapeRenderer shapeRenderer;
    private boolean face;
    private float anim;
    float dt;
    Movimientos action;

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
        face = false;
        dt = 0;
        this.mapa = mapa;
        layer =(TiledMapTileLayer) mapa.getLayers().get(1);
        // carga texturas (sprites)
        Texture tex = PiercyGame.res.getImage("player");
        TextureRegion[] sprites = new TextureRegion[4];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new TextureRegion(tex, i * 32 + 4, 0, 20, 32);
        }

        animation = new Animation(1f/4f, sprites);

        shapeRenderer = new ShapeRenderer();

        actionQueue = new LinkedList<Movimientos>();
        actividad = false;
        hasFinished = true;
        setPosition(25, 130);
        setSize(30, 50);
    }

	@Override
	public void draw(Batch batch) {
        setRegion(animation.getKeyFrame(dt, true));
        flip(face, false);
        super.draw(batch);
	}

	@Override
	public void update(float dt) {

        velocidad.y -= g * dt;
        if(velocidad.y > velocidadMax){
            velocidad.y = velocidadMax;
        }else if(velocidad.y < -velocidadMax){
            velocidad.y = -velocidadMax;
        }

        if (actividad){
            if (hasFinished){
                action = actionQueue.poll();
                if (action != null){
                    switch (action){
                        case DERECHA: {
                            face = false;
                            velocidad.x = velocidadMax * 0.5f;
                            distanciaPermitida = 32;
                        }break;
                        case IZQUIERDA: {
                            face = true;
                            velocidad.x = -velocidadMax * 0.5f;
                            distanciaPermitida = 32;
                        }break;
                        case BRINCAR: {
                            velocidad.y = velocidadMax;
                        }break;
                        case BRINCARD:{
                            face = false;
                            velocidad.y = velocidadMax;
                            velocidad.x = velocidadMax * 0.5f;
                        }break;
                        case BRINCARI:{
                            face = true;
                            velocidad.y = velocidadMax;
                            velocidad.x = -velocidadMax * 0.5f;
                        }break;
                        default:
                            break;
                    }
                    hasFinished = false;
                }else{
                    actividad = false;
                    hasFinished = true;
                }
            }

        }

        if(!hasFinished){
            this.dt += dt;
        }

        float xA = getX();
        float yA = getY();
        setX(getX() + velocidad.x * dt);
        setY(getY() + velocidad.y * dt);
        if(collidesXRight() || collidesXLeft()){
            setX(xA);
            if(!hasFinished)
                hasFinished = true;
        }
        if(collidesYBot() || collidesYTop()) {
            setY(yA);
            velocidad.y = 0;
            if(action == Movimientos.BRINCARD || action == Movimientos.BRINCARI || action == Movimientos.BRINCAR) {
                velocidad.x = 0;
                if (!hasFinished)
                    hasFinished = true;
            }
        }
	}

    public boolean getCell(float x, float y){
        Cell celda = layer.getCell((int)(x / layer.getTileHeight()), (int)(y / layer.getTileHeight()) );
        return celda != null && celda.getTile() != null && celda.getTile().getProperties().containsKey("blocked");
    }

	public boolean collidesYBot(){
        for(float particula = 0; particula < getWidth(); particula += 0.1){
            if(getCell(getX() + particula, getY()))
                return true;
        }
        return false;
    }

    public boolean collidesYTop(){
        for(float particula = 0; particula < getWidth(); particula += 0.1){
            if(getCell(getX() + particula, getY() + getHeight()))
                return true;
        }
        return false;
    }

    public boolean collidesXLeft(){
        for(float particula = 2f; particula < getHeight(); particula += 0.1){
            if(getCell(getX(), getY() + particula))
                return true;
        }
        return false;
    }

    public boolean collidesXRight(){
        for(float particula = 2f; particula < getHeight(); particula += 0.1){
            if(getCell(getX() + getWidth(), getY() + particula))
                return true;
        }
        return false;
    }

    public boolean colisionMoneda(){
        Cell celda = layer.getCell((int)(getX() / layer.getTileHeight()), (int)(getY() / layer.getTileHeight()) );
        return celda != null && celda.getTile() != null && celda.getTile().getProperties().containsKey("coin");
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
