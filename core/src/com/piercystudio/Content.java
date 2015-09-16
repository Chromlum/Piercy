package com.piercystudio;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Content {

	// un contenido diferente por cada HashMap
	private HashMap<String, Texture> textures;
	private HashMap<String, Music> music;
	private HashMap<String, Sound> sounds;

	public Content() {
		textures = new HashMap<String, Texture>();
		music = new HashMap<String, Music>();
		sounds = new HashMap<String, Sound>();
	}

	// Contenido: graficos
	public void loadImage(String key, String path) {
		textures.put(key, new Texture(Gdx.files.internal(path)));
	}

	public Texture getImage(String key) {
		return textures.get(key);
	}
	
	// Contenido: Musica y SFX
	public void loadMusic(String key, String path){
		music.put(key, Gdx.audio.newMusic(Gdx.files.internal(path)));
	}
	
	public Music getMusic(String key){
		return music.get(key);
	}
	
	public void loadSound(String path, String key){
		sounds.put(key, Gdx.audio.newSound(Gdx.files.internal(path)));
	}
	
	public Sound getSound(String key){
		return sounds.get(key);
	}
}

