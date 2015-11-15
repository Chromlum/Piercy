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
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public abstract class PiercyObject extends Sprite{

    protected Vector2 velocidad;
    protected float g;
    protected float velocidadMax;
    protected Animation animation;

    public PiercyObject(Sprite sprite){
        super(sprite);
        velocidad = new Vector2();
        g = 90;
        velocidadMax = 120;
    }

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

    public abstract void update(float dt);

    public void dispose(){
        this.getTexture().dispose();
    }

}
