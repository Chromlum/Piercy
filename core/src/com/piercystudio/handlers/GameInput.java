package com.piercystudio.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter{
	
	public boolean keyDown(int keycode){
		if(keycode == Keys.ENTER)
			GameKey.setKey(GameKey.ENTER, true);
		
		return true;
	}
	public boolean keyUp(int keycode){
		if(keycode == Keys.ENTER)
			GameKey.setKey(GameKey.ENTER, false);
		
		return true;
	}

}
