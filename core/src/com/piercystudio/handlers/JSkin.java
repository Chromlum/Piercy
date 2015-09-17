package com.piercystudio.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class JSkin extends Skin{
	
	public JSkin(){
		super(Gdx.files.internal("Skin/uiskin.json"), new TextureAtlas(
				Gdx.files.internal("Skin/uiskin.txt")));
	}

}
