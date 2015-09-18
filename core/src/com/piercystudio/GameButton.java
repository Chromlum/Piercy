/*
 * GameButton.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Crea un boton.
 * 
 */
package com.piercystudio;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameButton {
	
	private float x;
	private float y;
	private int width;
	private int height;
	
	private TextureRegion imagen;
	private boolean reversa;
	
	public GameButton(float x, float y, TextureRegion imagen){
		this.x = x;
		this.y = y;
		this.imagen = imagen;
		width = imagen.getRegionWidth();
		height = imagen.getRegionHeight();
	}
	
	public void setReversa(boolean b) { reversa = b; }
	
	public boolean contiene(float mx, float my){
		return mx >= x - width / 2 &&
				mx <= x + width / 2 &&
				my >= y - height / 2 &&
				my <= y + height / 2;
	}
	
	public void render(SpriteBatch sb){
		if(reversa){
			sb.draw(imagen, x + width / 2, y - height / 2, -width, height);
		} else {
			sb.draw(imagen, x - width / 2, y - height / 2);
		}
	}

}
