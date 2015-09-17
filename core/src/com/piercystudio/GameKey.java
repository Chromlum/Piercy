package com.piercystudio;

public class GameKey {
	
	public static boolean[] keys = new boolean[1];
	public static boolean[] pkeys = new boolean[1];
	public static final int ENTER = 0;
	
	public static void setKey(int key, boolean down) {
		keys[key] = down;
	}

	public static void update() {
		for (int i = 0; i < 1; i++)
			pkeys[i] = keys[i];
	}

	public static boolean isDown(int key) {
		return keys[key];
	}

	public static boolean isPressed(int key) {
		return keys[key] && !pkeys[key];
	}

}
