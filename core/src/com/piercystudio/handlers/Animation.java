package com.piercystudio.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	
	/* Atributos */
	private TextureRegion[] frames;
	private float time;
	private float delay;
	private int CurrentFrames;
	
	public Animation(){
		super();
	}
	
	public void setFrames(TextureRegion[] reg, float delay) {
		frames = reg;
		time = CurrentFrames = 0;
		this.delay = delay;
	}

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
