/*
 * JSkin.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Crea un TextureAtlas a partir de dos archivos, un .json y un .txt para crear un skin de
 * texto a utilizar en el juego.
 * 
 */
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
