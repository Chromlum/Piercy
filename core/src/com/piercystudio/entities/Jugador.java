
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


	public Jugador(Sprite sprite, TiledMap mapa) {
		super(sprite);
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
	}
}
