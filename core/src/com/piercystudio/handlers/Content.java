/*
 * Content.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Gestor de contenidos. Carga imagenes y sonidos.
 * 
 */
package com.piercystudio.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Content {

	// un contenido diferente por cada HashMap
	private HashMap<String, Texture> texturas;
	private HashMap<String, Music> musica;
	private HashMap<String, Sound> sfx;

	public Content() {
		texturas = new HashMap<String, Texture>();
		musica = new HashMap<String, Music>();
		sfx = new HashMap<String, Sound>();
	}

	// Contenido: graficos
	public void loadImage(String key, String path) {
		texturas.put(key, new Texture(Gdx.files.internal(path)));
	}

	public Texture getImage(String key) {
		return texturas.get(key);
	}
	
	// Contenido: Musica y SFX
	public void loadMusic(String key, String path){
		musica.put(key, Gdx.audio.newMusic(Gdx.files.internal(path)));
	}
	
	public Music getMusic(String key){
		return musica.get(key);
	}
	
	public void loadSound(String key, String path){
		sfx.put(key, Gdx.audio.newSound(Gdx.files.internal(path)));
	}
	
	public Sound getSound(String key){
		return sfx.get(key);
	}
}

