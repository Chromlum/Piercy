/*
 * GameInput.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Procesador de entradas del juego.
 * 
 */
package com.piercystudio.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter{
	
	public boolean keyDown(int keycode){
		if(keycode == Keys.ENTER)
			GameKey.setKey(GameKey.ENTER, true);
		if(keycode == Keys.ESCAPE)
			GameKey.setKey(GameKey.ESC, true);
		
		return true;
	}
	public boolean keyUp(int keycode){
		if(keycode == Keys.ENTER)
			GameKey.setKey(GameKey.ENTER, false);
		if(keycode == Keys.ESCAPE)
			GameKey.setKey(GameKey.ESC, false);
		
		return true;
	}

}
