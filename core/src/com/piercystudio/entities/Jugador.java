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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.piercystudio.PiercyGame;

public class Jugador extends PiercyObject{

	public enum Movimientos{
		DERECHA,
		IZQUIERDA,
		BRINCAR,
		DESTRUIR,
		BRINCARD,
		BRINCARI
	}
	private double distanciaAcumulada;
	private Queue<Movimientos> actionQueue;
	private boolean actividad;
	private boolean hasFinished;
	public Jugador(TiledMap map) {
		super(map);
		facingRight = true;
		width = height = 42;
		cwidth = cheight = 40;
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
		actionQueue = new LinkedList<Movimientos>();
		actividad = false;
		hasFinished = true;
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
		distanciaAcumulada = 0;
		// carga texturas (sprites)
		Texture tex = PiercyGame.res.getImage("player");
		TextureRegion[] sprites = new TextureRegion[4];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 32, 0, 32, 32);
		}
		animation.setFrames(sprites, 1 / 5f);
	}
	
	public void getSiguientePosicion() {
		if (actividad){
			if (hasFinished){
				Movimientos action = actionQueue.poll();
				if (action != null){
					switch (action){
						case DERECHA: this.setRight(true);break;
						case IZQUIERDA: this.setLeft(true);break;
						case BRINCAR: this.setJumping(true);break;
						case BRINCARD:{
							this.setRight(true);
							this.setJumping(true);
						}break;
						case BRINCARI:{
							this.setLeft(true);
							this.setJumping(true);
						}break;
					}
					hasFinished = false;
				}else{ 
					actividad = false;
					hasFinished = true;
				}
			}
			
		}
		if (Right && !hasFinished) {
			distanciaAcumulada += dx;
			facingRight = true;
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
			if (distanciaAcumulada > 32){
				Right = false;
				hasFinished = true;
				distanciaAcumulada = 0;
			}
		} else if (Left && !hasFinished) {
			facingRight = false;
			distanciaAcumulada += Math.abs(dx);
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
			if (distanciaAcumulada > 32){
				Left = false;
				hasFinished = true;
				distanciaAcumulada = 0;
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
		if (Jumping && !Falling && !hasFinished) {
			dy = JumpStart;
			Falling = true;
		}
		if (Falling) {
			dy -= fallSpeed;
			if (Math.abs(JumpStart + dy) <= 0.001){
				Falling = false;
			}
		}
		if (Jumping && !Falling){
			hasFinished = true;
			Jumping = false;
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
