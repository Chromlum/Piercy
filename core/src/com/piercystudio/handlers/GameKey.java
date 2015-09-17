/*
 * GameKey.java
 * 
 * @author: G. Brolo
 * 16/09/15
 * 
 * Procesador de teclas del juego.
 * 
 */
package com.piercystudio.handlers;

public class GameKey {
	
	public static boolean[] keys = new boolean[2];
	public static boolean[] pkeys = new boolean[2];
	public static final int ENTER = 0;
	public static final int ESC = 1;
	
	public static void setKey(int key, boolean down) {
		keys[key] = down;
	}

	public static void update() {
		for (int i = 0; i < 2; i++)
			pkeys[i] = keys[i];
	}

	public static boolean isDown(int key) {
		return keys[key];
	}

	public static boolean isPressed(int key) {
		return keys[key] && !pkeys[key];
	}

}
