/*
 * Jugador.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Crea al jugador principal.
 * 
 */
package com.piercystudio.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.piercystudio.PiercyGame;

public class Jugador extends PiercyObject{

	public Jugador(TiledMap map) {
		super(map);
		facingRight = true;
		width = height = 26;
		cwidth = cheight = 24;
		fallSpeed = 0.10;
		moveSpeed = 0.08;
		maxSpeed = 1.7;
		maxFallSpeed = 4.0;
		stopSpeed = 0.4;
		JumpStart = 3.6;
		stopJumpSpeed = 0.3;
		// carga texturas (sprites)
		Texture tex = PiercyGame.res.getImage("player");
		TextureRegion[] sprites = new TextureRegion[4];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 32, 0, 32, 32);
		}
		animation.setFrames(sprites, 1 / 5f);
	}
	
	public Jugador(TiledMap map, int wh, int cwh){
		super(map);
		
		facingRight = true;
		width = height = wh;
		cwidth = cheight = cwh;
		fallSpeed = 0.10;
		moveSpeed = 0.08;
		maxSpeed = 1.7;
		maxFallSpeed = 4.0;
		stopSpeed = 0.4;
		JumpStart = 3.6;
		stopJumpSpeed = 0.3;
		// carga texturas (sprites)
		Texture tex = PiercyGame.res.getImage("player");
		TextureRegion[] sprites = new TextureRegion[4];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 32, 0, 32, 32);
		}
		animation.setFrames(sprites, 1 / 5f);
	}
	
	public void getSiguientePosicion() {
		if (Right) {
			facingRight = true;
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else if (Left) {
			facingRight = false;
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}
		if (dx == 0) {
			animation.setDelay(0);
		} else if (dx != 0) {
			animation.setDelay(1 / 5f);
		}
		if (Jumping && !Falling) {
			dy = JumpStart;
			Falling = true;
		}
		if (Falling) {
			dy -= fallSpeed;
		}
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		getSiguientePosicion();
		calcularColisiones();
		setPosition(xtemp, ytemp);
	}

	@Override
	public void draw(SpriteBatch sb) {
		super.draw(sb);
	}

}
