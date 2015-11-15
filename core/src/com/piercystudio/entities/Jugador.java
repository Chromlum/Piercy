
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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.piercystudio.PiercyGame;

public class Jugador extends PiercyObject{

    private double distanciaAcumulada;
    private double distanciaPermitida;
    private Queue<Movimientos> actionQueue;
    private boolean actividad;
    private boolean hasFinished;

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
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
	}

	@Override
	public void update(float dt) {
        velocidad.y -= g * dt;

        if(velocidad.y > velocidadMax){
            velocidad.y = velocidadMax;
        }else if(velocidad.y < velocidadMax){
            velocidad.y -= velocidadMax;
        }

        setY(getY() + velocidad.y * dt);
        setX(getX() + velocidad.x * dt);
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


}
