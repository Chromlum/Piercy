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

public class PiercyObject extends Sprite{

    private Vector2 velocidad;
    private float g;
    private float velocidadMax;

    public PiercyObject(Sprite sprite, TiledMap mapa){
        super(sprite);
    }

    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float dt){
        velocidad.y -= g * dt;

        if(velocidad.y > velocidadMax){
            velocidad.y = velocidadMax;
        }else if(velocidad.y < velocidadMax){
            velocidad.y -= velocidadMax;
        }

        setY(getY() + velocidad.y * dt);
        setX(getX() + velocidad.x * dt);
    }

}
