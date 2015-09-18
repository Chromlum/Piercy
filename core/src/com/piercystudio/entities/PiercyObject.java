/*
 * PiercyObject.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Entidad madre de los objetos en el juego.
 * 
 */
package com.piercystudio.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.piercystudio.handlers.Animation;

public class PiercyObject {
	
	/* Atributos Madre & hijo */
	protected int width, height, cwidth, cheight;
	protected double x, y, dx, dy, xdest, ydest, xtemp, ytemp;
	protected boolean topLeft, topRight, bottomLeft, bottomRight, bottomdeadL, bottomdeadR;
	protected boolean Left, Right, Jumping, Falling, facingRight, dead;
	protected double moveSpeed, maxSpeed, stopSpeed, fallSpeed, maxFallSpeed,
	JumpStart, stopJumpSpeed;
	protected TiledMapTileLayer layer;
	protected int tileSize;
	protected TiledMap map;
	protected Animation animation;
	
	public PiercyObject(TiledMap map){
		this.map = map;
		// importante --obtiene layer 1 de cada map cargado
		layer = (TiledMapTileLayer) this.map.getLayers().get(1);
		tileSize = map.getProperties().get("tilewidth", Integer.class);
		animation = new Animation();
	}
	
	/* Calcular esquinas de tiles */
	public void calcularEsquinas(double x, double y){
		
		int leftTile = (int) (x - cwidth / 2) / tileSize;
		int rightTile = (int) (x + cwidth / 2) / tileSize;
		int topTile = (int) (y + cheight / 2) / tileSize;
		int bottomTile = (int) (y - cheight / 2) / tileSize;
		topLeft = layer.getCell(leftTile, topTile).getTile().getProperties()
				.containsKey("blocked");
		topRight = layer.getCell(rightTile, topTile).getTile().getProperties()
				.containsKey("blocked");
		bottomLeft = layer.getCell(leftTile, bottomTile).getTile()
				.getProperties().containsKey("blocked");
		bottomRight = layer.getCell(rightTile, bottomTile).getTile()
				.getProperties().containsKey("blocked");
		bottomdeadL = layer.getCell(leftTile, bottomTile).getTile()
				.getProperties().containsKey("dead");
		bottomdeadR = layer.getCell(rightTile, bottomTile).getTile()
				.getProperties().containsKey("dead");
		
	}
	
	/* Colision */
	public void calcularColisiones(){
		
		xdest = x + dx;
		ydest = y + dy;
		xtemp = x;
		ytemp = y;
		calcularEsquinas(x, ydest);
		if (dy > 0) {
			if (topLeft || topRight) {
				dy = 0;
			} else {
				ytemp += dy;
			}
		}
		if (dy < 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				Falling = false;
			} else if (bottomdeadL || bottomdeadR){
				dy = 0;
				Falling = false;
				dead = true;
			}  else {
				ytemp += dy;
			}
		}
		calcularEsquinas(xdest, y);
		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
			}  else {
				xtemp += dx;
			}
		}
		if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
			}  else {
				xtemp += dx;
			}
		}
		if (!Falling) {
			calcularEsquinas(x, ydest - 1);
			if (!bottomLeft && !bottomRight) {
				Falling = true;
			}
		}
		
	}
	
	public void update(float dt) {
		animation.update(dt);
	}
	
	public void draw(SpriteBatch sb) {
		sb.begin();
		if (facingRight) {
			sb.draw(animation.getFrames(), (int) x - cwidth / 2, (int) y
					- cheight / 2, width, height);
		} else {
			sb.draw(animation.getFrames(), (int) x - cwidth / 2 + width,
					(int) y - cheight / 2, -width, height);
		}
		sb.end();
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/* Getters */
	public boolean isDead() {
		return dead;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public boolean isFalling(){
		return Falling;
	}
	
	/* Setters */
	public void setLeft(boolean left) {
		Left = left;
	}

	public void setRight(boolean right) {
		Right = right;
	}

	public void setJumping(boolean jumping) {
		Jumping = jumping;
	}
	
	public void setFalling(boolean falling){
		Falling = falling;
	}

}
