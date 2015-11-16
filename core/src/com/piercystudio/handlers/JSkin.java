/*
 * JSkin.java
 * 
 * @author: E. Mendoza, J. Custodio, G. Brolo, J. Rosales
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
	/* Constructor */
	public JSkin(){
		/* Usa constructor de Skin */
		super(Gdx.files.internal("Skin/uiskin.json"), new TextureAtlas( //Obtiene skin en formato Json
				Gdx.files.internal("Skin/uiskin.txt"))); // Crea un atlas de texturas con el skin del archivo .txt
	}

}
