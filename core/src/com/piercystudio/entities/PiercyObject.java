/*
 * PiercyObject.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo
 * 16/09/15
 * 
 * Entidad madre de los objetos en el juego.
 * 
 */
package com.piercystudio.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public abstract class PiercyObject extends Sprite{

    protected Vector2 velocidad;
    protected float g;
    protected float velocidadMax;

    public PiercyObject(Sprite sprite){
        super(sprite);
    }

    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public abstract void update(float dt);

}
