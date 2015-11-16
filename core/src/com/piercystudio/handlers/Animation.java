/*
 * Animation.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo, J. Rosales
 * 16/09/15
 * 
 * Corre los frames en un sprite para crear una animacion a partir de una imagen.
 * 
 */
package com.piercystudio.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	
	/* Atributos */
	private TextureRegion[] frames; // Contiene los frames del sprite
	private float time; // tiempo de la animacion
	private float delay; // delay que se aplica a la animacion
	private int CurrentFrames; // frames actuales del sprite en los que se encuentra la animacion
	
	/* Constructor */
	public Animation(){
		super();
	}
	
	/* Coloca los frames, manualmente, del sprite al que se le quiere aplicar una animacion */
	public void setFrames(TextureRegion[] reg, float delay) {
		frames = reg;
		time = CurrentFrames = 0;
		this.delay = delay;
	}

	/* Coloca manualmente el delay de la animacion al sprite */
	public void setDelay(float delay) {
		this.delay = delay;
	}
	
	// update 
	public void update(float dt) {
		if (delay <= 0)
			return;
		time += dt;
		while (time >= delay) {
			time -= delay;
			CurrentFrames++;
			if (CurrentFrames == frames.length) {
				CurrentFrames = 0;
			}
		}
	}

	// obtiene frames de una textura
	public TextureRegion getFrames() {
		return frames[CurrentFrames];
	}

}
