/*
 * PiercyObject.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo, J. Rosales
 * Fecha: 16/09/15
 * 
 * Descripccion: Clase abstracta para representar objetos movibles
 * en el juego
 * 
 */
package com.piercystudio.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public abstract class PiercyObject extends Sprite{

    // Atribuos
    protected Vector2 velocidad; // Vector de velocidad
    protected float g; // Aceleracion de la gravedad
    protected float velocidadMax; // Maxima velocidad
    protected Animation animation; // Animacion

    /**
     * Constructor
     * @param sprite Sprite que contiene la textura
     */
    public PiercyObject(Sprite sprite){
        super(sprite);
        velocidad = new Vector2();
        g = 90;
        velocidadMax = 120;
    }

    /**
     * Metodo para dibujar el objeto en pantalla
     * @param batch
     */
    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        if(batch.isDrawing())
            super.draw(batch);
        else{
            batch.begin();
            super.draw(batch);
            batch.end();
        }
    }

    /**
     * Metodo abstracto para realizar los calculos antes de dibujar
     * @param dt tiempo delta
     */
    public abstract void update(float dt);

    /**
     * Metodo para destuir el objeto
     */
    public void dispose(){
        this.getTexture().dispose();
    }

    /**
     * Metodo getter
     * @return Velocidad del objeto
     */
	public Vector2 getVelocidad(){
		return velocidad;
	}

}
